package io.xccit.zxyp.service;

import io.xccit.zxyp.model.dto.h5.OrderInfoDto;
import io.xccit.zxyp.model.entity.order.OrderInfo;
import io.xccit.zxyp.model.vo.h5.TradeVo;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description
 */
public interface IOrderInfoService {

    /**
     * 确认下单
     * @return
     */
    TradeVo trade();

    /**
     * 提交订单
     * @param orderInfoDto
     * @return
     */
    Long submitOrder(OrderInfoDto orderInfoDto);

    /**
     * 根据订单ID获取订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfo(Long orderId);

    /**
     * 立即购买
     * @return
     */
    TradeVo buy(Long skuId);
}
