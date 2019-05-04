package com.pandy.sell.enums;

import lombok.Getter;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 19:38
 * @Version 1.0
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新的订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
