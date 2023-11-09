package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

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
}
