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
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_STATUS_ERROR(12,"订单不存在"),
    ORDER_DETAIL_ERROR(13,"订单详情不存在"),
    ORDER_UPDATE_FAILED(15,"更新失败"),
    ORDER_DEFAULT_EMPTY_ERROR(16,"订单为空异常"),
    ORDER_PAID_STATUS_ERROR(17,"订单支付状态不正确"),
    PARAM_ERROR(1,"参数不正确"),
    CART_MUST_NOTEMPTY(18,"购物车不能为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
