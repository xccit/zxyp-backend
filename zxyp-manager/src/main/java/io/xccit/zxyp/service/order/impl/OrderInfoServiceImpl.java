package io.xccit.zxyp.service.order.impl;

import cn.hutool.core.date.DateUtil;
import io.xccit.zxyp.mapper.order.OrderStatisticsMapper;
import io.xccit.zxyp.model.dto.order.OrderStatisticsDto;
import io.xccit.zxyp.model.entity.order.OrderStatistics;
import io.xccit.zxyp.model.vo.order.OrderStatisticsVo;
import io.xccit.zxyp.service.order.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CH_ywx
 * @date 2023/11/1
 * @description
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    /**
     * 获取订单统计数据
     *
     * @param orderStatisticsDto
     * @return
     */
    @Override
    public OrderStatisticsVo getOrderStatistics(OrderStatisticsDto orderStatisticsDto) {
        // 查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.get(orderStatisticsDto) ;
        //日期列表
        List<String> dateList = orderStatisticsList.stream().
                map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());
        //统计金额列表
        List<BigDecimal> amountList = orderStatisticsList.stream()
                .map(OrderStatistics::getTotalAmount).collect(Collectors.toList());
        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo() ;
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        // 返回数据
        return orderStatisticsVo;
    }
}
