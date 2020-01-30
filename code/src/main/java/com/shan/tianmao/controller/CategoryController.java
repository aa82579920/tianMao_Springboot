package com.shan.tianmao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.tianmao.mapper.CategoryMapper;
import com.shan.tianmao.pojo.Category;
import com.shan.tianmao.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    @PostMapping("/categories")
    public Object add(Category category, MultipartFile image,
                      HttpServletRequest request)throws Exception {
        categoryMapper.save(category);
        saveOrUpdateImageFile(category, image, request);
        return category;
    }
    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        System.out.println(file.getPath());
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        ImageIO.write(img, "jpg", file);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        categoryMapper.delete(id);
        File folder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(folder, id+".jpg");
        if(file != null) {
            file.delete();
        }
        return null;
    }
}
