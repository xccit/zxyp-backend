package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/8
 * @description 订单操作日志Mapper
 */
@Mapper
public interface OrderLogMapper {

    /**
     * 添加订单日志
     * @param orderLog
     */
    void save(OrderLog orderLog);
}
