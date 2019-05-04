package com.pandy.sell.dao;

import com.pandy.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 20:02
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao masterDao;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1");
        orderMaster.setBuyerName("李月月");
        orderMaster.setBuyerPhone("15229908443");
        orderMaster.setBuyerAddress("北吴");
        orderMaster.setBuyerOpenid("110");
        orderMaster.setOrderAmount(new BigDecimal(25));
        //orderMaster.setCreateTime(new Date());
        //orderMaster.setUpdateTime(new Date());
        OrderMaster save = masterDao.save(orderMaster);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByBuyerOpenId() {

        PageRequest request = PageRequest.of(0,2);
        Page<OrderMaster> byBuyerOpenid = masterDao.findByBuyerOpenid("110", request);
        System.out.println(byBuyerOpenid.getTotalElements());
    }
}