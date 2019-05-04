package com.pandy.sell.enums;

import lombok.Getter;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 9:00
 * @Version 1.0
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"上架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }


}
