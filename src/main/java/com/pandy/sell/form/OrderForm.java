package com.pandy.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: Pandy
 * @Date: 2019/5/8 22:52
 * @Version 1.0
 * 专门用于表单验证的实体类
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名为必填项")
    private String name;

    @NotEmpty(message = "手机号为必填项")
    private String phone;

    @NotEmpty(message = "地址为必填项")
    private String address;

    //购物车的信息
    @NotEmpty(message = "购物车不能为空")
    private String items;

    @NotEmpty(message = "openid为必填项")
    private String openid;
}
