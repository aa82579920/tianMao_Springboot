package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product (name, subTitle, originalPrice, promotePrice, stock, createDate, cid)" +
            "values (#{name}, #{subTitle}, #{originalPrice}. #{promotePrice}, #{stock}, #{createDate}, #{category.id})")
    int add(Product product);

    @Delete("delete from product where id = #{id}")
    void delete(int id);

    @Update("update product set name=#{name}, subTitle=#{subTitle}, originalPrice=#{originalPrice},promotePrice=#{promotePrice},stock=#{stock},createDate=#{createDate},cid=#{category.id}" +
            "where id=#{id}")
    int update(Product product);

    @Select("select * from product where id = #{id}")
    @Results({
            @Result(property = "category", column = "cid",
                    one = @One(select = "com.shan.tianmao.mapper.CategoryMapper.get"))
    })
    Product get(int id);

    @Select("select * from product where cid=#{category.id}")
    @Results({
            @Result(property = "category", column = "cid",
                    one = @One(select = "com.shan.tianmao.mapper.CategoryMapper.get"))
    })
    List<Product> findByCategory(@Param("category") Category category);
}
