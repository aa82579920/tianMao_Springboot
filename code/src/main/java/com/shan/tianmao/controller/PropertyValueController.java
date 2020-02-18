package com.shan.tianmao.controller;

import com.shan.tianmao.mapper.ProductMapper;
import com.shan.tianmao.mapper.PropertyMapper;
import com.shan.tianmao.mapper.PropertyValueMapper;
import com.shan.tianmao.pojo.Product;
import com.shan.tianmao.pojo.Property;
import com.shan.tianmao.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    PropertyMapper propertyMapper;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception {
        Product product = productMapper.get(pid);
        List<Property> properties = propertyMapper.findByCategory(product.getCategory());
        for (Property property :
                properties) {
            PropertyValue propertyValue = propertyValueMapper.getByPropertyAndProduct(property,product);
            if(null == propertyValue) {
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueMapper.add(propertyValue);
            }
        }
        List<PropertyValue> list = propertyValueMapper.findByProductOrderByIdDesc(product);
        return list;
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue propertyValue) throws Exception {
        propertyValueMapper.update(propertyValue);
        return propertyValue;
    }
}
