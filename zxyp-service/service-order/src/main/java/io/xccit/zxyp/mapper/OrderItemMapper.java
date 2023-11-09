package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/8
 * @description 订单项Mapper
 */
@Mapper
public interface OrderItemMapper {

    /**
     * 添加订单项
     * @param orderItem
     */
    void save(OrderItem orderItem);
}
