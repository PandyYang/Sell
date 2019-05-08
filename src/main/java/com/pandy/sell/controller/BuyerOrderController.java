package com.pandy.sell.controller;

import com.pandy.VO.ResultVO;
import com.pandy.sell.converter.OrderForm2OrderDTO;
import com.pandy.sell.dto.OrderDTO;
import com.pandy.sell.enums.ResultEnum;
import com.pandy.sell.exception.SellException;
import com.pandy.sell.form.OrderForm;
import com.pandy.sell.service.OrderService;
import com.pandy.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Pandy
 * @Date: 2019/5/8 22:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;


    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult
                                               ) {
        if (bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车不能为空");
            throw new SellException(ResultEnum.CART_MUST_NOTEMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);

    }
    //订单列表

    //订单详情

    //取消订单
}
