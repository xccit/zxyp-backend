package io.xccit.zxyp.service.impl;

import io.xccit.zxyp.mapper.RegionMapper;
import io.xccit.zxyp.mapper.UserAddressMapper;
import io.xccit.zxyp.model.entity.user.UserAddress;
import io.xccit.zxyp.model.vo.h5.BaseAddressVO;
import io.xccit.zxyp.service.IUserAddressService;
import io.xccit.zxyp.utils.AuthContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 用户地址业务层
 */
@Slf4j
@Service
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private RegionMapper regionMapper;

    /**
     * 获取用户地址列表
     *
     * @return
     */
    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.listAddressByUserID(userId);
    }

    /**
     * 用户地址修改
     *
     * @param userAddress
     */
    @Override
    public void updateAddress(UserAddress userAddress) {
        userAddress.setUserId(AuthContextUtil.getUserInfo().getId());
        //TODO 设置完整地址
        userAddress.setFullAddress(getFullAddress(userAddress.getProvinceCode(),
                userAddress.getCityCode(),
                userAddress.getDistrictCode(),
                userAddress.getAddress()));
        userAddressMapper.updateAddressByID(userAddress);
    }

    /**
     * 新增用户地址
     *
     * @param userAddress
     */
    @Override
    public void saveAddress(UserAddress userAddress) {
        userAddress.setUserId(AuthContextUtil.getUserInfo().getId());
        //TODO 设置完整地址
        userAddress.setFullAddress(getFullAddress(userAddress.getProvinceCode(),
                                                userAddress.getCityCode(),
                                                userAddress.getDistrictCode(),
                                                userAddress.getAddress()));
        userAddressMapper.saveAddress(userAddress);
    }

    /**
     * 删除用户地址
     *
     * @param id
     */
    @Override
    public void removeAddress(Long id) {
        userAddressMapper.removeAddressByID(id);
    }


    /**
     * 拼接完整地址
     * @param provinceCode
     * @param cityCode
     * @param districtCode
     * @param address
     * @return
     */
    private String getFullAddress(String provinceCode,String cityCode,String districtCode,String address){
        BaseAddressVO baseAddressVO = regionMapper.getFullAddress(provinceCode,cityCode,districtCode);
        String fullAddress = baseAddressVO.getProvince() +
                baseAddressVO.getCity() +
                baseAddressVO.getDistrict() +
                address;
        return fullAddress;
    }
}
