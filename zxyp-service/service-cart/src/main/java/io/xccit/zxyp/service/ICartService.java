package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description
 */
public interface ICartService {

    /**
     * 清空购物车
     */
    void clearCart();

    /**
     * 更新购物车商品全部选中状态
     * @param isChecked
     */
    void allCheckCart(Integer isChecked);

    /**
     * 更新购物车商品选中状态
     * @param skuId
     * @param isChecked
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * 删除购物车商品
     * @param skuId
     */
    void deleteCart(Long skuId);

    /**
     * 查询购物车
     * @return
     */
    List<CartInfo> getCartList();

    /**
     * 添加购物车
     * @param skuId
     * @param skuNum
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * 选中的购物车
     * @return
     */
    List<CartInfo> getAllChecked();

    /**
     * 远程调用：删除生成订单的购物车商品
     */
    void deleteChecked();
}
