package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Order;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from order_")
    @Results({
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.shan.tianmao.mapper.UserMapper.get"))
    })
    List<Order> findAll();

    @Insert(" insert into order_ ( orderCode, address, post, receiver, mobile, userMessage, createDate, payDate, deliveryDate, confirmDate, uid, status)" +
            " values (#{orderCode}, #{address}, #{post}, #{receiver}, #{mobile}, #{userMessage}, #{createDate}, #{payDate}, #{deliveryDate}, #{confirmDate}, #{uid}, #{status}) ")
    int save(Order order);

    @Delete("delete from order_ where id= #{id} ")
    void delete(int id);

    @Select("select * from order_ where id=#{id} ")
    @Results({
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.shan.tianmao.mapper.UserMapper.get"))
    })
    Order get(int id);

    @Update("update order_ set orderCode=#{orderCode},address=#{address},post=#{post},receiver=#{receiver},mobile=#{mobile},userMessage=#{userMessage},createDate=#{createDate},payDate=#{payDate}, deliveryDate=#{deliveryDate}, confirmDate=#{confirmDate}, uid=#{user.id},status=#{status} where id=#{id} ")
    int update(Order order);
}
