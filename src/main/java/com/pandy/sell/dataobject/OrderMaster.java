package com.pandy.sell.dataobject;

import com.pandy.sell.enums.OrderStatusEnum;
import com.pandy.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 19:29
 * @Version 1.0
 * 订单
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    //订单id
    @Id
    private String orderId;

    //买家名字
    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    //买家微信的openid
    private String buyerOpenid;

    //订单的总金额
    private BigDecimal orderAmount;

    //订单状态 默认为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态  默认是0未支付
    private Integer payStatus =PayStatusEnum.WAIT.getCode();

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;


    //一个订单对应多个商品
    /*@Transient
    private List<OrderDetail> orderDetailList;*/

}
