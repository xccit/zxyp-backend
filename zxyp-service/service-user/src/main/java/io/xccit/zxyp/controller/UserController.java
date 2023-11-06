package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.h5.UserRegisterDto;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 前台用户控制器
 */
@Tag(name = "前台用户接口")
@RestController
@RequestMapping("/api/user/userInfo")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     * @param userRegisterDto
     * @return
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody UserRegisterDto userRegisterDto){
        userService.register(userRegisterDto);
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }
}
