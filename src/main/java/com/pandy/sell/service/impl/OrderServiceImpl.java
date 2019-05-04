package com.pandy.sell.service.impl;

import com.pandy.sell.dao.OrderDetailDao;
import com.pandy.sell.dao.OrderMasterDao;
import com.pandy.sell.dataobject.OrderDetail;
import com.pandy.sell.dataobject.OrderMaster;
import com.pandy.sell.dataobject.ProductInfo;
import com.pandy.sell.dto.CartDTO;
import com.pandy.sell.dto.OrderDTO;
import com.pandy.sell.enums.OrderStatusEnum;
import com.pandy.sell.enums.PayStatusEnum;
import com.pandy.sell.enums.ResultEnum;
import com.pandy.sell.exception.SellException;
import com.pandy.sell.service.OrderService;
import com.pandy.sell.service.ProductService;
import com.pandy.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:07
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao masterDao;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        //初始总价格为0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品 数量 价格
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算总金额
            orderAmount =  productInfo.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity()).add(orderAmount));
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);


            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);

            //如果不使用lambda表达式 那么会是
            //List<CartDTO> cartDTOList = new ArrayList<>();
            //CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            //cartDTOList.add(cartDTO);

        }
        //3.写入订单数据库 ordermaster  orderdetail
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        masterDao.save(orderMaster);
        //4.下单成功 扣除库存
            //使用lambda直接进行函数式编程
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
