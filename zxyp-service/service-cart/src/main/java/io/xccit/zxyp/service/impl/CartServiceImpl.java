package io.xccit.zxyp.service.impl;

import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.client.product.IProductClients;
import io.xccit.zxyp.model.entity.h5.CartInfo;
import io.xccit.zxyp.model.entity.product.ProductSku;
import io.xccit.zxyp.service.ICartService;
import io.xccit.zxyp.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 购物车业务层
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private IProductClients productClients;

    /**
     * 内部方法,获取购物车Redis Key前缀
     *
     * @param userId
     * @return
     */
    private String getCartKey(Long userId) {
        return "user:cart:" + userId;
    }

    /**
     * 清空购物车
     */
    @Override
    public void clearCart() {
        //TODO 构建查询的redis里面key值，根据当前userId
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //TODO 根据key删除redis里面数据
        redisTemplate.delete(cartKey);
    }

    /**
     * 更新购物车商品全部选中状态
     *
     * @param isChecked
     */
    @Override
    public void allCheckCart(Integer isChecked) {
        //TODO 构建查询的redis里面key值，根据当前userId
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //TODO 根据key获取购物车所有value值
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        //TODO 判断不为空
        if (!CollectionUtils.isEmpty(objectList)) {
            //List<Object>  --  List<CartInfo>
            List<CartInfo> cartInfoList =
                    objectList.stream().map(object ->
                                    JSON.parseObject(object.toString(), CartInfo.class))
                            .collect(Collectors.toList());
            //TODO 把每个商品isChecked进行更新
            cartInfoList.forEach(cartInfo -> {
                cartInfo.setIsChecked(isChecked);
                redisTemplate.opsForHash().put(cartKey, String.valueOf(cartInfo.getSkuId()),
                        JSON.toJSONString(cartInfo));
            });
        }
    }

    /**
     * 更新购物车商品选中状态
     *
     * @param skuId
     * @param isChecked
     */
    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        //TODO 构建查询的redis里面key值，根据当前userId
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //TODO 判断key是否包含filed
        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if (hasKey) {
            //TODO 根据key + field把value获取出来
            String cartInfoString =
                    redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            //TODO 更新value里面选中状态
            CartInfo cartInfo = JSON.parseObject(cartInfoString, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            //TODO 放回到redis的hash类型里面
            redisTemplate.opsForHash().put(cartKey,
                    String.valueOf(skuId),
                    JSON.toJSONString(cartInfo));
        }
    }

    /**
     * 删除购物车商品
     *
     * @param skuId
     */
    @Override
    public void deleteCart(Long skuId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        redisTemplate.opsForHash().delete(cartKey,String.valueOf(skuId));
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @Override
    public List<CartInfo> getCartList() {
        //TODO 构建查询的redis里面key值，根据当前userId
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //TODO 根据key从redis里面hash类型获取所有value值 cartInfo
        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);
        //TODO List<Object> -- List<CartInfo>
        if (!CollectionUtils.isEmpty(valueList)) {
            List<CartInfo> cartInfoList = valueList.stream().map(cartInfoObj ->
                            JSON.parseObject(cartInfoObj.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();
    }

    /**
     * 添加购物车
     *
     * @param skuId
     * @param skuNum
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //TODO 必须登录状态，获取当前登录用户id（作为redis的hash类型的key值）
        //从ThreadLocal获取用户信息就可以了
        Long userId = AuthContextUtil.getUserInfo().getId();
        //构建hash类型key名称
        String cartKey = this.getCartKey(userId);
        //TODO 因为购物车放到redis里面
        //hash类型   key：userId   field：skuId   value：sku信息CartInfo
        //从redis里面获取购物车数据，根据用户id + skuId获取（hash类型key + field）
        Object cartInfoObj =
                redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        //TODO 如果购物车存在添加商品，把商品数量相加
        CartInfo cartInfo = null;
        if (cartInfoObj != null) { //添加到购物车商品已经存在的，把商品数量相加
            //cartInfoObj -- CartInfo
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            //数量相加
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            //设置属性,购物车商品选中状态
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {
            //TODO 如果购物车没有添加商品，直接商品添加购物车（添加到redis里面）
            //远程调用实现：通过nacos + openFeign实现 根据skuId获取商品sku信息
            cartInfo = new CartInfo();
            //远程调用实现:根据skuId获取商品sku信息
            ProductSku productSku = productClients.getProductSkuByID(skuId);
            //设置相关数据到cartInfo对象里面
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }
        //添加到redis里面
        redisTemplate.opsForHash().put(cartKey,
                String.valueOf(skuId),
                JSON.toJSONString(cartInfo));
    }

    /**
     * 选中的购物车
     *
     * @return
     */
    @Override
    public List<CartInfo> getAllChecked() {
        //获取userid，构建key
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        //根据key获取购物车所有商品
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);

        if (!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(object ->
                            JSON.parseObject(object.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)//选中
                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();
    }

    /**
     * 远程调用：删除生成订单的购物车商品
     */
    @Override
    public void deleteChecked() {
        //获取userid，构建key
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        //根据key获取redis所有value值
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);

        //删除选中商品
        objectList.stream().map(object -> JSON.parseObject(object.toString(), CartInfo.class))
                .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey,
                        String.valueOf(cartInfo.getSkuId())));
    }
}
