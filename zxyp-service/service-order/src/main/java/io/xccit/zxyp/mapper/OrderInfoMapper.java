package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 订单Mapper
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 订单信息添加
     * @param orderInfo
     */
    void save(OrderInfo orderInfo);

    /**
     * 根据订单ID获取订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfoByID(Long orderId);

    /**
     * 用户订单分页列表
     * @param userId
     * @param orderStatus
     * @return
     */
    List<OrderInfo> listUserOrderPage(Long userId, Integer orderStatus);
}
