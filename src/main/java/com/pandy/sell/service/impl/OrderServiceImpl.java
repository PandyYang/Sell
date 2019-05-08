package com.pandy.sell.service.impl;

import com.pandy.sell.converter.OrderMaster2OrderDTO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:07
 * @Version 1.0
 */
@Service
@Slf4j
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
        OrderMaster orderMaster = masterDao.findById(orderId).get();
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_ERROR);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = masterDao.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
            //必须是新下的订单才能取消 完结或者取消过的无法再次进行取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单] orderId ={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster order = masterDao.save(orderMaster);
        if (order == null){
            log.error("[取消订单] 更新失败,orderMaster = ",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单] 订单中无商品,orderSTO = {}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DEFAULT_EMPTY_ERROR);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //已支付退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {

        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            System.err.println("[完结订单]订单状态不正确,orderId  orderStatus=" + orderDTO.getOrderId() + orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
            //2.修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = masterDao.save(orderMaster);
        if (updateResult == null){
            System.err.println("[完结订单]更新失败 orderMaster = " + orderMaster);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            System.err.println("[订单支付完成]订单状态不正确,orderId  orderStatus=" + orderDTO.getOrderId() + orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付完成]订单支付状态不正确, orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAID_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = masterDao.save(orderMaster);
        if (updateResult == null){
            System.err.println("[订单支付成功]更新失败 orderMaster = " + orderMaster);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        return orderDTO;

    }
}
