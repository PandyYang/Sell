package com.pandy.sell.dao;

import com.pandy.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author: Pandy
 * @Date: 2019/5/3 17:28
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao repository;

    @Test
    public void findTest(){
        Optional<ProductCategory> byId = repository.findById(1);
        System.out.println(byId.toString());
    }

/*    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("周");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }*/
}