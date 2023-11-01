package io.xccit.zxyp.mapper.order;

import io.xccit.zxyp.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CH_ywx
 * @date 2023/11/1
 * @description 订单信息Mapper
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 根据创建时间查询订单信息并封装成订单统计VO
     * @param createTime
     * @return
     */
    OrderStatistics getOrderStatistics(String createTime);
}
