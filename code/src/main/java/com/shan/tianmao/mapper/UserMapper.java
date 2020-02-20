package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Insert(" insert into user ( name, password, salt ) values (#{name}, #{password}, #{salt}) ")
    int save(User user);

    @Delete("delete from user where id= #{id} ")
    void delete(int id);

    @Select("select * from user where id=#{id} ")
    User get(int id);

    @Update("update user set name=#{name}, password=#{password}, salt=#{salt} where id=#{id} ")
    int update(User user);
}
