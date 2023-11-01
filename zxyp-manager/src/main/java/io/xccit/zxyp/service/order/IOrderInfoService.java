package io.xccit.zxyp.service.order;

import io.xccit.zxyp.model.dto.order.OrderStatisticsDto;
import io.xccit.zxyp.model.vo.order.OrderStatisticsVo;

/**
 * @author CH_ywx
 * @date 2023/11/1
 * @description
 */
public interface IOrderInfoService {

    /**
     * 获取订单统计数据
     * @param orderStatisticsDto
     * @return
     */
    OrderStatisticsVo getOrderStatistics(OrderStatisticsDto orderStatisticsDto);
}
