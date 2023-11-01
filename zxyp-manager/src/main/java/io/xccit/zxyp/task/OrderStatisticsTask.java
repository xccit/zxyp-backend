package io.xccit.zxyp.task;

import cn.hutool.core.date.DateUtil;
import io.xccit.zxyp.mapper.order.OrderInfoMapper;
import io.xccit.zxyp.mapper.order.OrderStatisticsMapper;
import io.xccit.zxyp.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CH_ywx
 * @date 2023/11/1
 * @description 订单统计定时任务
 */
@Slf4j
@Component
public class OrderStatisticsTask {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 每天凌晨2点同步前一天订单数据
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics(){
        //前一天的时间
        String createTime = DateUtil.offsetDay(new Date(), -1)
                .toString(new SimpleDateFormat("yyyy-MM-dd"));
        //TODO 根据创建时间查询前一天订单信息
        OrderStatistics orderStatistics = orderInfoMapper.getOrderStatistics(createTime);
        assert orderStatistics != null;
        //TODO 添加订单统计信息
        orderStatisticsMapper.save(orderStatistics);
    }
}
