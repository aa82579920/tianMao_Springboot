package com.shan.tianmao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 后台页面跳转Controller
@Controller
public class AdminPageController {
    @GetMapping(value = "/admin")
    public String admin() {
        return "redirect:admin_category_list";
    }

    @GetMapping(value = "/admin_category_list")
    public String listCategory() {
        return "admin/listCategory";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "admin/hello";
    }
}
