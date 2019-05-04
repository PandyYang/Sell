package com.pandy.sell.dto;

import lombok.Data;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:40
 * @Version 1.0
 * 购物车
 */
@Data
public class CartDTO {

    //商品id
    private String productId;

    //数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
