package com.shan.tianmao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.tianmao.mapper.UserMapper;
import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.pojo.Property;
import com.shan.tianmao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/users")
    public PageInfo<User> list(@RequestParam(value = "start", defaultValue = "0")int start,
                               @RequestParam(value = "size", defaultValue = "5")int size ) throws Exception {
        PageHelper.startPage(start, size, "id desc");
        List<User> list = userMapper.findAll();
        PageInfo<User> page = new PageInfo<>(list);

        return page;
    }
}
