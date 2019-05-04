package com.pandy.sell.enums;

import lombok.Getter;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 19:46
 * @Version 1.0
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功")
    ;

   private Integer code;

   private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
