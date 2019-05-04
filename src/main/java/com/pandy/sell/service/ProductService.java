package com.pandy.sell.service;

import com.pandy.sell.dataobject.ProductInfo;
import com.pandy.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 8:55
 * @Version 1.0
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    //查询全部上架的商品
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
