package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.h5.TradeVo;
import io.xccit.zxyp.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @Operation(summary = "确认下单")
    @GetMapping("/auth/trade")
    public AjaxResult<TradeVo> trade(){
        TradeVo tradeVo = orderInfoService.trade();
        return AjaxResult.build(tradeVo, ResultCodeEnum.SUCCESS);
    }
}
