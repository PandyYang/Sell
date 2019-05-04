package com.pandy.sell.dao;

import com.pandy.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 19:56
 * @Version 1.0
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    //分页查询买家的订单
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}