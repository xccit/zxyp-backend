package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.entity.user.UserAddress;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 用户地址控制器
 */
@Tag(name = "前台用户地址接口")
@RestController
@RequestMapping("/api/user/userAddress")
public class UserAddressController {

    @Autowired
    private IUserAddressService userAddressService;

    /**
     *
     * @return
     */
    @Operation(summary = "根据ID获取地址信息")
    @GetMapping("/getUserAddress/{id}")
    public UserAddress getAddressById(@PathVariable Long id){
        UserAddress userAddress = userAddressService.getAddressByID(id);
        return userAddress;
    }

    /**
     * 获取用户地址列表
     * @return
     */
    @Operation(summary = "获取用户地址列表")
    @GetMapping("auth/findUserAddressList")
    public AjaxResult<List<UserAddress>> findUserAddressList() {
        List<UserAddress> list = userAddressService.listUserAddress();
        return AjaxResult.build(list , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 用户地址修改
     * @param userAddress
     * @return
     */
    @Operation(summary = "用户地址修改")
    @PutMapping("/auth/updateById")
    public AjaxResult updateAddress(@RequestBody UserAddress userAddress){
        userAddressService.updateAddress(userAddress);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 新增用户地址
     * @return
     */
    @Operation(summary = "新增用户地址")
    @PostMapping("/auth/save")
    public AjaxResult saveAddress(@RequestBody UserAddress userAddress){
        userAddressService.saveAddress(userAddress);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除用户地址
     * @param id
     * @return
     */
    @Operation(summary = "删除用户地址")
    @DeleteMapping("/auth/removeById/{id}")
    public AjaxResult removeAddress(@PathVariable Long id){
        userAddressService.removeAddress(id);
        return AjaxResult.build(null,ResultCodeEnum.SUCCESS);
    }
}
