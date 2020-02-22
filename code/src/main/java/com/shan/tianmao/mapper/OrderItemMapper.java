package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Order;
import com.shan.tianmao.pojo.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    @Select("select * from orderitem where oid = #{id}")
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get")),
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.shan.tianmao.mapper.UserMapper.get")),
            @Result(property = "order", column = "oid",
                    one = @One(select = "com.shan.tianmao.mapper.OrderMapper.get"))
    })
    List<OrderItem> findByOrder(Order order);

    @Insert(" insert into orderitem ( pid, oid, uid, number ) " +
            "values (#{product.id}, #{order.id}, #{user.id}, #{number}) ")
    int save(OrderItem orderItem);

    @Delete("delete from orderitem where id= #{id} ")
    void delete(int id);

    @Select("select * from orderitem where id=#{id} ")
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get")),
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.shan.tianmao.mapper.UserMapper.get")),
            @Result(property = "order", column = "oid",
                    one = @One(select = "com.shan.tianmao.mapper.OrderMapper.get"))
    })
    OrderItem get(int id);

    @Update("update orderitem set pid=#{product.id}, oid=#{order.id}, uid=#{user.id}, number=#{number} where id=#{id} ")
    int update(OrderItem orderItem);
}
