package io.xccit.zxyp.mapper.order;

import io.xccit.zxyp.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/1
 * @description 订单统计Mapper
 */
@Mapper
public interface OrderStatisticsMapper {
    /**
     * 添加订单统计信息
     * @param orderStatistics
     */
    void save(OrderStatistics orderStatistics);
}
