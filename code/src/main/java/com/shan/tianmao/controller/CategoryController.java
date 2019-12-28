package com.shan.tianmao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.tianmao.mapper.CategoryMapper;
import com.shan.tianmao.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    // 自动装配mapper
    @Autowired
    CategoryMapper categoryMapper;

    // Restful风格 查询全部
    @GetMapping("/categories")
    public PageInfo<Category> list(
            @RequestParam(value = "start", defaultValue = "0")int start,
            @RequestParam(value = "size", defaultValue = "5")int size) throws Exception {
        //根据start size 进行分页，并按照id倒序
        PageHelper.startPage(start, size, "id desc");
        List<Category> list = categoryMapper.findAll();
        PageInfo<Category> page = new PageInfo<>(list);

        return page;
    }


}
