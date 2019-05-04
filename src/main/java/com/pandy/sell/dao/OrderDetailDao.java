package com.pandy.sell.dao;

import com.pandy.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 20:00
 * @Version 1.0
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
