package io.xccit.zxyp.feign.cart;

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

    /**
     * 购物车清除选中
     * @return
     */
    @GetMapping("/api/order/cart/auth/deleteChecked")
    AjaxResult deleteChecked();

    /**
     * 购物车所有选中
     * @return
     */
    @GetMapping( "/api/order/cart/auth/getAllChecked")
    List<CartInfo> getAllChecked();
}