package com.shan.tianmao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.tianmao.mapper.CategoryMapper;
import com.shan.tianmao.mapper.PropertyMapper;
import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyController {
    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/categories/{cid}/properties")
    public PageInfo<Property> list(
            @PathVariable("cid") int id,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
        PageHelper.startPage(start, size, "id desc");
        Category category = categoryMapper.get(id);
        List<Property> list = propertyMapper.findByCategory(category);
//        List<Property> list = propertyMapper.findAll();
        PageInfo<Property> page = new PageInfo<>(list);


        return page;
    }

    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id) {
        Property property = propertyMapper.get(id);
        return property;
    }
    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id") int id) {
        propertyMapper.delete(id);
        return null;
    }

    @PostMapping("/properties")
    public Object add(@RequestBody Property bean) {
        System.out.println();
        propertyMapper.add(bean);
        return bean;
    }

    @PutMapping("/properties")
    public Object update(@RequestBody Property property) throws Exception{
        propertyMapper.update(property);
        return property;
    }
}
