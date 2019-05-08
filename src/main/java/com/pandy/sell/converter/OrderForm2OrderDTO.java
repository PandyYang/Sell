package com.pandy.sell.converter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.pandy.sell.dataobject.OrderDetail;
import com.pandy.sell.dto.OrderDTO;
import com.pandy.sell.enums.ResultEnum;
import com.pandy.sell.exception.SellException;
import com.pandy.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Pandy
 * @Date: 2019/5/8 23:02
 * @Version 1.0
 * 将表单数据转换成orderDTO 就是在各个模块中的传输数据
 */
@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        //对应实体类中的属性名不一致 是不能使用beanUtils进行相应的转换的
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("[对象转换]错误 String = {}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
