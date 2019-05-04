package com.pandy.sell.dao;

import com.pandy.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 20:43
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao detailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123");
        orderDetail.setProductId("111");
        orderDetail.setProductName("测试");
        orderDetail.setProductPrice(new BigDecimal(12));
        orderDetail.setProductQuantity(12);
        orderDetail.setProductIcon("123");
        detailDao.save(orderDetail);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = detailDao.findByOrderId("123");
        System.out.println("byOrderId = " + byOrderId);
    }
}