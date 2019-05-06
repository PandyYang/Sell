package com.pandy.sell.dto;

import com.pandy.sell.dataobject.OrderDetail;
import com.pandy.sell.enums.OrderStatusEnum;
import com.pandy.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:02
 * @Version 1.0
 */
@Data
public class OrderDTO {

    //订单id
    private String orderId;

    //买家名字
    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    //买家微信的openid
    private String buyerOpenid;

    //订单的总金额
    private BigDecimal orderAmount;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //订单状态
    private Integer orderStatus;

    //支付状态
    private Integer payStatus;

    //一个订单对应多个商品
    List<OrderDetail> orderDetailList;

}
