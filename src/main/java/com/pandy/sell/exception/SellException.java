package com.pandy.sell.exception;

import com.pandy.sell.enums.ResultEnum;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:14
 * @Version 1.0
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
