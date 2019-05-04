package com.pandy.sell.service.impl;

import com.pandy.sell.dataobject.OrderDetail;
import com.pandy.sell.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 22:23
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYEROPENID = "110";
    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("测试111");
        orderDTO.setBuyerName("123");
        orderDTO.setBuyerPhone("1234444");
        orderDTO.setBuyerOpenid(BUYEROPENID);

        //购物车
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(1);
        orderDetails.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        System.out.println("orderDTO1 = " + orderDTO1);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}