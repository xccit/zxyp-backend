package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.feign.cart.ICartClients;
import io.xccit.zxyp.mapper.OrderInfoMapper;
import io.xccit.zxyp.model.entity.h5.CartInfo;
import io.xccit.zxyp.model.entity.order.OrderItem;
import io.xccit.zxyp.model.vo.h5.TradeVo;
import io.xccit.zxyp.service.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 订单业务层
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private ICartClients cartClients;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 确认下单
     *
     * @return
     */
    @Override
    public TradeVo trade() {
        //TODO 获取选中的商品列表
        List<CartInfo> cartInfoList = cartClients.getAllChecked();
        //TODO 获取订单项(商品集合遍历)
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }
        //TODO 计算总金额
        BigDecimal totalAmount = new BigDecimal(0);
        //遍历订单项,获取每一个订单项的单价,再与订单项的数量相乘,加上每个订单项的总价返回
        for(OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        //TODO 封装
        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);
        return tradeVo;
    }
}
