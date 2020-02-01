package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Property;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PropertyMapper {

    @Select("select * from property")
    @Results({
            @Result(property = "category", column = "cid",
                    one = @One(select = "com.shan.tianmao.mapper.CategoryMapper.get"))
    })
    List<Property> findAll();

    @Insert("insert into property (cid, name) values (#{category.id}, #{name})")
    int add(Property property);

    @Delete("delete from property where id = #{id}")
    void delete(int id);

    @Update("update property set name=#{name}, cid=#{category.id} where id=#{id}")
    int update(Property property);

    @Select("select * from property where id=#{id}")
    @Results({
            @Result(property = "category", column = "cid",
                    one = @One(select = "com.shan.tianmao.mapper.CategoryMapper.get"))
    })
    Property get(int id);

    @Select("select * from property where cid=#{category.id}")
    @Results({
            @Result(property = "category", column = "cid",
                    one = @One(select = "com.shan.tianmao.mapper.CategoryMapper.get"))
    })
    List<Property> findByCategory(@Param("category") Category category);
}
