package io.xccit.zxyp.feign;

import io.xccit.zxyp.model.entity.h5.CartInfo;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 购物车远程调用接口
 */
@FeignClient("service-cart")
public interface ICartClients {
    @GetMapping("/api/order/cart/auth/deleteChecked")
    public AjaxResult deleteChecked();

    @GetMapping( "/api/order/cart/auth/getAllChecked")
    public List<CartInfo> getAllChecked();
}