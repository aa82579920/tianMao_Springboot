package com.shan.tianmao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.tianmao.mapper.OrderItemMapper;
import com.shan.tianmao.mapper.OrderMapper;
import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Order;
import com.shan.tianmao.pojo.OrderItem;
import com.shan.tianmao.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";


    @GetMapping("/orders")
    public PageInfo<Order> list(
            @RequestParam(value = "start", defaultValue = "0")int start,
            @RequestParam(value = "size", defaultValue = "5")int size) throws Exception {
        //根据start size 进行分页，并按照id倒序
        PageHelper.startPage(start, size, "id desc");
        List<Order> list = orderMapper.findAll();
        fillOrderByItem(list);
        removeOrderFromOrderItems(list);
        PageInfo<Order> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 发送订单
     * @param oid
     * @return
     * @throws IOException
     */
    @PutMapping("/deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        Order order = orderMapper.get(oid);
        Date deliveryDate = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr= sdf.format(deliveryDate);
        order.setDeliveryDate(dateStr);
        order.setStatus(OrderController.waitConfirm);
        orderMapper.update(order);
        return Result.success();
    }
    /**
     * 填充一个订单的订单项(orderItems)
     * @param orders
     */
    public void fillOrderByItem(List<Order> orders) {
        for(Order order: orders) {
            List<OrderItem> orderItems = orderItemMapper.findByOrder(order);
            float total = 0;
            int totalNumber = 0;
            for(OrderItem oi: orderItems) {
                total += oi.getNumber()*oi.getProduct().getPromotePrice();
                totalNumber += oi.getNumber();
            }
            order.setOrderItems(orderItems);
            order.setTotal(total);
            order.setTotalNumber(totalNumber);
        }
    }


    public void removeOrderFromOrderItems(List<Order> orders) {
        for(Order order: orders) {
            List<OrderItem> items = order.getOrderItems();
            for(OrderItem item: items) {
                item.setOrder(null);
            }
        }

    }
}
