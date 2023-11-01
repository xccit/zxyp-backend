package io.xccit.zxyp.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.order.OrderStatisticsDto;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.order.OrderStatisticsVo;
import io.xccit.zxyp.service.order.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH_ywx
 * @date 2023/11/1
 * @description 订单信息控制器
 */
@Tag(name = "订单接口")
@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @Operation(summary = "获取订单统计数据")
    @GetMapping("/getOrderStatistics")
    public AjaxResult<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatistics(orderStatisticsDto) ;
        return AjaxResult.build(orderStatisticsVo , ResultCodeEnum.SUCCESS) ;
    }
}
