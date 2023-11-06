package io.xccit.zxyp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.xccit.zxyp.model.entity.h5.CartInfo;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/06
 * @description 购物车控制器
 */
@RestController
@RequestMapping("/api/order/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    /**
     * 清空购物车
     * @return
     */
    @Operation(summary="清空购物车")
    @GetMapping("/auth/clearCart")
    public AjaxResult clearCart(){
        cartService.clearCart();
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新购物车商品全部选中状态
     * @param isChecked
     * @return
     */
    @Operation(summary="更新购物车商品全部选中状态")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public AjaxResult allCheckCart(@Parameter(name = "isChecked", description = "是否选中 1:选中 0:取消选中", required = true) @PathVariable(value = "isChecked") Integer isChecked){
        cartService.allCheckCart(isChecked);
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新购物车商品选中状态
     * @param skuId
     * @param isChecked
     * @return
     */
    @Operation(summary="更新购物车商品选中状态")
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public AjaxResult checkCart(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable(value = "skuId") Long skuId,
                            @Parameter(name = "isChecked", description = "是否选中 1:选中 0:取消选中", required = true) @PathVariable(value = "isChecked") Integer isChecked) {
        cartService.checkCart(skuId,isChecked);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除购物车商品
     * @param skuId
     * @return
     */
    @Operation(summary = "删除购物车商品")
    @DeleteMapping("auth/deleteCart/{skuId}")
    public AjaxResult deleteCart(@PathVariable("skuId") Long skuId) {
        cartService.deleteCart(skuId);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询购物车
     * @return
     */
    @Operation(summary = "查询购物车")
    @GetMapping("auth/cartList")
    public AjaxResult cartList() {
        List<CartInfo> cartInfoList = cartService.getCartList();
        return AjaxResult.build(cartInfoList,ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加购物车
     * @param skuId 商品sku的id值
     * @param skuNum 商品数量
     * @return
     */
    @Operation(summary = "添加购物车")
    @GetMapping("auth/addToCart/{skuId}/{skuNum}")
    public AjaxResult addToCart(@PathVariable Long skuId,
                            @PathVariable Integer skuNum) {
        cartService.addToCart(skuId,skuNum);
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 远程调用：订单结算使用，获取购物车选中商品列表
     * @return
     */
    @Operation(summary="选中的购物车")
    @GetMapping(value = "/auth/getAllCkecked")
    public List<CartInfo> getAllChecked() {
        List<CartInfo> list = cartService.getAllChecked();
        return list;
    }

    /**
     * 远程调用：删除生成订单的购物车商品
     * @return
     */
    @GetMapping("/auth/deleteChecked")
    public AjaxResult deleteChecked() {
        cartService.deleteChecked();
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }

}
