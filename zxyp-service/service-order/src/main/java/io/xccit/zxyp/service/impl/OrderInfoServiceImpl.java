package io.xccit.zxyp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.client.user.IUserClients;
import io.xccit.zxyp.client.product.IProductClients;
import io.xccit.zxyp.exception.OrderException;
import io.xccit.zxyp.client.cart.ICartClients;
import io.xccit.zxyp.mapper.OrderInfoMapper;
import io.xccit.zxyp.mapper.OrderItemMapper;
import io.xccit.zxyp.mapper.OrderLogMapper;
import io.xccit.zxyp.model.dto.h5.OrderInfoDto;
import io.xccit.zxyp.model.entity.h5.CartInfo;
import io.xccit.zxyp.model.entity.order.OrderInfo;
import io.xccit.zxyp.model.entity.order.OrderItem;
import io.xccit.zxyp.model.entity.order.OrderLog;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.model.entity.user.UserAddress;
import io.xccit.zxyp.model.entity.user.UserInfo;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.h5.TradeVo;
import io.xccit.zxyp.service.IOrderInfoService;
import io.xccit.zxyp.utils.AuthContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private IProductClients productClients;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Autowired
    private IUserClients userClients;

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

    /**
     * 提交订单
     *
     * @param orderInfoDto
     * @return
     */
    @Transactional
    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        //获取当前登录的用户信息
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        //获取订单项列表,校验订单项列表是否为空,是则抛异常
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        if (CollectionUtils.isEmpty(orderItemList)){
            throw new OrderException(ResultCodeEnum.DATA_ERROR);
        }
        //遍历订单项列表,判断订单项库存是否充足
        for (OrderItem orderItem : orderItemList) {
            //远程调用获取Sku信息,判断stock_num(库存)
            ProductSku productSku = productClients.getProductSkuByID(orderItem.getSkuId());
            //未查询到Sku信息,抛异常
            if (productSku == null){
                throw new OrderException(ResultCodeEnum.DATA_ERROR);
            }
            //库存不足,抛异常
            if (orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()){
                throw new OrderException(ResultCodeEnum.STOCK_LESS);
            }
        }
        //封装订单信息,添加到order_info
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setNickName(userInfo.getNickName());
        // 使用当前系统时间戳作为订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //TODO 封装收货地址信息(远程调用)
        UserAddress userAddress = userClients.getAddressById(orderInfoDto.getUserAddressId());
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFreightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);
        orderInfoMapper.save(orderInfo);
        //封装订单项,添加到order_item
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }
        //封装订单日志,添加到order_log
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setOperateUser(userInfo.getNickName());
        orderLog.setNote("订单提交");
        orderLogMapper.save(orderLog);
        //TODO 把订单项中的商品从购物车删除(Redis,远程调用)
        cartClients.deleteChecked();
        //返回订单ID
        return orderInfo.getId();
    }

    /**
     * 根据订单ID获取订单信息
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.getOrderInfoByID(orderId);
    }

    /**
     * 立即购买
     *
     * @return
     */
    @Override
    public TradeVo buy(Long skuId) {
        TradeVo tradeVo = new TradeVo();
        List<OrderItem> orderItemList = new ArrayList<>();
        ProductSku productSku = productClients.getProductSkuByID(skuId);
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuNum(1);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItemList.add(orderItem);
        //封装结果
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(productSku.getSalePrice());
        return tradeVo;
    }

    /**
     * 用户订单分页列表
     *
     * @param page
     * @param limit
     * @param orderStatus
     * @return
     */
    @Override
    public PageInfo<OrderInfo> listUserOrderPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page, limit);
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.listUserOrderPage(userId, orderStatus);

        orderInfoList.forEach(orderInfo -> {
            List<OrderItem> orderItem = orderItemMapper.listItemByOrderID(orderInfo.getId());
            orderInfo.setOrderItemList(orderItem);
        });

        return new PageInfo<>(orderInfoList);
    }
}
