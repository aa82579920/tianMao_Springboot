package com.shan.tianmao.controller;

import com.shan.tianmao.mapper.CategoryMapper;
import com.shan.tianmao.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryMapper categoryMapper;

    // Restful风格 查询全部
    @GetMapping("/categories")
    public List<Category> list() throws Exception {
        return categoryMapper.findAll();
    }


}
