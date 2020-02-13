package com.shan.tianmao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.tianmao.mapper.CategoryMapper;
import com.shan.tianmao.mapper.ProductMapper;
import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/categories/{cid}/products")
    public PageInfo<Product> list(
            @PathVariable("cid") int cid,
            @RequestParam(value = "start", defaultValue = "0")int start,
            @RequestParam(value = "size", defaultValue = "5")int size
    )throws Exception{
        PageHelper.startPage(start, size, "id desc");
        Category category = categoryMapper.get(cid);
        List<Product> list = productMapper.findByCategory(category);
        PageInfo<Product> page = new PageInfo<>(list);
        return page;
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id")int id) throws Exception{
        Product product = productMapper.get(id);
        return product;
    }

    @PostMapping("/products")
    public Object add(@RequestBody Product product)throws Exception {
        product.setCreateDate(new Date());
        productMapper.add(product);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        productMapper.delete(id);
        return null;
    }

    @PutMapping("/products")
    public Object update(@RequestBody Product product) throws Exception{
        productMapper.update(product);
        return product;
    }
}
