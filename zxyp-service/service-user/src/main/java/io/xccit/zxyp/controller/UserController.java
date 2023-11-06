package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.h5.UserLoginDto;
import io.xccit.zxyp.model.dto.h5.UserRegisterDto;
import io.xccit.zxyp.model.entity.user.UserInfo;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDto userLoginDto){
        String token = userService.login(userLoginDto);
        return AjaxResult.build(token,ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("auth/getCurrentUserInfo")
    public AjaxResult getCurrentUser(HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfo userInfo = userService.getCurrentUser(token);
        return AjaxResult.build(userInfo,ResultCodeEnum.SUCCESS);
    }
}
