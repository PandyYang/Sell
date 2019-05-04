package com.pandy.sell.service.impl;

import com.pandy.sell.dao.ProductInfoDao;
import com.pandy.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 9:08
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findOne() {
        Optional<ProductInfo> byId = productInfoDao.findById("123");
        //Assert.assertEquals("123",byId.get());
        System.out.println(byId);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> all = productInfoDao.findAll();
        System.out.println(all);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> all = productInfoDao.findAll(request);
        System.out.println("all = " + all.getTotalElements());
    }

    @Test
    public void save() {
    }
}