package com.pandy.sell.dao;

import com.pandy.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 8:35
 * @Version 1.0
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
