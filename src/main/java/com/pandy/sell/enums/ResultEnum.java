package com.pandy.sell.enums;

import lombok.Getter;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:15
 * @Version 1.0
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
