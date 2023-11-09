package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 用户地址Mapper
 */
@Mapper
public interface UserAddressMapper {

    /**
     * 根据用户ID获取用户地址列表
     * @param userId
     * @return
     */
    List<UserAddress> listAddressByUserID(Long userId);

    /**
     * 用户地址修改
     * @param userAddress
     */
    void updateAddressByID(UserAddress userAddress);

    /**
     * 新增用户地址
     * @param userAddress
     */
    void saveAddress(UserAddress userAddress);

    /**
     * 根据ID删除用户地址
     * @param id
     */
    void removeAddressByID(Long id);

    /**
     * 根据ID获取地址信息
     * @param id
     * @return
     */
    UserAddress getAddressByID(Long id);
}
