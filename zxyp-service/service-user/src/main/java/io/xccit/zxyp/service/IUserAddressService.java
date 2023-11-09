package io.xccit.zxyp.service;

import io.xccit.zxyp.model.entity.user.UserAddress;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description
 */
public interface IUserAddressService {

    /**
     * 获取用户地址列表
     * @return
     */
    List<UserAddress> listUserAddress();

    /**
     * 用户地址修改
     * @param userAddress
     */
    void updateAddress(UserAddress userAddress);

    /**
     * 新增用户地址
     * @param userAddress
     */
    void saveAddress(UserAddress userAddress);

    /**
     * 删除用户地址
     * @param id
     */
    void removeAddress(Long id);

    /**
     * 根据ID获取地址信息
     * @param id
     * @return
     */
    UserAddress getAddressByID(Long id);
}
