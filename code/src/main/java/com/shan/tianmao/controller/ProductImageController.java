package com.shan.tianmao.controller;

import com.shan.tianmao.mapper.CategoryMapper;
import com.shan.tianmao.mapper.ProductImageMapper;
import com.shan.tianmao.mapper.ProductMapper;
import com.shan.tianmao.pojo.Product;
import com.shan.tianmao.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductImageController {
    public static final String type_single = "single"; // 单个图片
    public static final String type_detail = "detail"; // 详情图片

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductImageMapper productImageMapper;

    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(@RequestParam("type") String type,
                                   @PathVariable("pid") int pid) throws Exception {

        Product product = productMapper.get(pid);
        if(ProductImageController.type_single.equals(type)) {
            List<ProductImage> singles = productImageMapper.findByProductAndTypeOrderByIdDesc(product, type);
            return singles;
        }else if(ProductImageController.type_detail.equals(type)) {
            List<ProductImage> details = productImageMapper.findByProductAndTypeOrderByIdDesc(product, type);
            return details;
        }else {
            return new ArrayList<>();
        }
    }

    @PostMapping("/productImages")
    public Object add(@RequestParam("pid")int pid, @RequestParam("type")String type,
                      @RequestParam("id") int id, MultipartFile image, HttpServletRequest request) throws Exception {
        Product product = productMapper.get(pid);
        ProductImage productImage = new ProductImage();
        productImage.setType(type);
        productImage.setProduct(product);
        productImageMapper.add(productImage);

        return null;
    }
}
