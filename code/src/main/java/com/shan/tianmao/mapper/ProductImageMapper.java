package com.shan.tianmao.mapper;

import com.shan.tianmao.pojo.Product;
import com.shan.tianmao.pojo.ProductImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductImageMapper {


    @Insert("insert into productimage (pid, type) values (#{product.id}, #{type})")
    int add(ProductImage productImage);

    @Delete("delete from productimage where id = #{id}")
    void delete(int id);

    @Select("select * from productimage where id = #{id}")
    @Results({
            @Result(property = "product", column = "pid",
            one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get"))
    })
    ProductImage get(int id);

    @Select("select * from productimage where pid=#{product.id} and type=#{type}")
    @Results({
            @Result(property = "product", column = "pid",
                    one = @One(select = "com.shan.tianmao.mapper.ProductMapper.get"))
    })
    List<ProductImage> findByProductAndTypeOrderByIdDesc(@Param("product") Product product, @Param("type") String type);
}
