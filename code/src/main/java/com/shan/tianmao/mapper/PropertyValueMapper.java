package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.Product;
import com.shan.tianmao.pojo.Property;
import com.shan.tianmao.pojo.PropertyValue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PropertyValueMapper {
    @Insert("insert into propertyvalue (pid, ptid, value) values (#{product.id}, #{property.id}, #{value})")
    int add(PropertyValue propertyValue);

    @Delete("delete from propertyvalue where id=#{id}")
    void delete(int id);

    @Update("update propertyvalue set pid=#{product.id}, ptid=#{property.id}, value=#{value} where id=#{id}")
    int update(PropertyValue propertyValue);

    @Select("select * from propertyvalue where id=#{id}")
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get")),
            @Result(property = "property", column = "ptid",
                    one = @One(select = "com.shan.tianmao.mapper.PropertyMapper.get"))
    })
    PropertyValue get(int id);

    @Select("select * from propertyvalue where pid=#{id}")
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get")),
            @Result(property = "property", column = "ptid",
                    one = @One(select = "com.shan.tianmao.mapper.PropertyMapper.get"))
    })
    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    @Select("select * from propertyvalue where pid=#{product.id} and ptid=#{property.id}")
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get")),
            @Result(property = "property", column = "ptid",
                    one = @One(select = "com.shan.tianmao.mapper.PropertyMapper.get"))
    })
    PropertyValue getByPropertyAndProduct(@Param("property") Property property,
                                          @Param("product")Product product);
}
