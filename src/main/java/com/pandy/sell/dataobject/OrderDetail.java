package com.pandy.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 19:50
 * @Version 1.0
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;

    //订单id
    private String orderId;

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //商品数量
    private Integer productQuantity;

    //商品图片
    private String productIcon;

    //创建时间
    //private Date createTime;

    //更新时间
    //private Date updateTime;
}
