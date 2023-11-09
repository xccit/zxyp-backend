package io.xccit.zxyp.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.h5.OrderInfoDto;
import io.xccit.zxyp.model.entity.order.OrderInfo;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.h5.TradeVo;
import io.xccit.zxyp.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 订单控制器
 */
@Tag(name = "前台订单接口")
@RestController
@RequestMapping("/api/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 确认下单
     *
     * @return
     */
    @Operation(summary = "确认下单")
    @GetMapping("/auth/trade")
    public AjaxResult<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.trade();
        return AjaxResult.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 提交订单
     *
     * @return
     */
    @Operation(summary = "提交订单")
    @PostMapping("/auth/submitOrder")
    public AjaxResult submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return AjaxResult.build(orderId, ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据订单ID获取订单信息
     *
     * @param orderId 订单ID
     * @return
     */
    @Operation(summary = "根据订单ID获取订单信息")
    @GetMapping("/auth/{orderId}")
    public AjaxResult getOrderInfo(@PathVariable Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return AjaxResult.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 立即购买
     *
     * @param skuId
     * @return
     */
    @Operation(summary = "立即购买")
    @GetMapping("/auth/buy/{skuId}")
    public AjaxResult buy(@PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return AjaxResult.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取订单分页列表
     *
     * @param page
     * @param limit
     * @param orderStatus
     * @return
     */
    @Operation(summary = "用户订单分页列表")
    @GetMapping("auth/{page}/{limit}")
    public AjaxResult<PageInfo<OrderInfo>> list(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Integer page,
            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Integer limit,
            @Parameter(name = "orderStatus", description = "订单状态", required = false)
            @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.listUserOrderPage(page, limit, orderStatus);
        return AjaxResult.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
