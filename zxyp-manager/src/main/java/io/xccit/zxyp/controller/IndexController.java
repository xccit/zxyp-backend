package io.xccit.zxyp.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.dto.system.LoginDto;
import io.xccit.zxyp.entity.system.SysUser;
import io.xccit.zxyp.service.ISysUserService;
import io.xccit.zxyp.service.ValidateCodeService;
import io.xccit.zxyp.utils.AuthContextUtil;
import io.xccit.zxyp.vo.common.AjaxResult;
import io.xccit.zxyp.vo.common.ResultCodeEnum;
import io.xccit.zxyp.vo.system.LoginVo;
import io.xccit.zxyp.vo.system.ValidateCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 登录接口
 */
@Slf4j
@Tag(name = "管理员/用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 登录
     * @param loginDto 登录参数
     * @return 登录结果
     */
    @Operation(summary = "用户/管理员登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDto loginDto){
        log.info(JSON.toJSONString(loginDto).toString());
        LoginVo loginVo = sysUserService.login(loginDto);
        if (loginVo != null){
            return AjaxResult.build(loginVo, ResultCodeEnum.LOGIN_SUCCESS);
        }else{
            return AjaxResult.build(null,ResultCodeEnum.LOGIN_ERROR);
        }
    }

    /**
     * 验证码获取接口
     * @return 验证码信息
     */
    @GetMapping("/generateValidateCode")
    public AjaxResult<ValidateCodeVo> getValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return AjaxResult.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }


    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/userinfo")
    public AjaxResult userInfo(@RequestHeader(value = "token") String token){
        log.info("service token:"+token);
        SysUser sysUser = sysUserService.userInfo(token);
        return AjaxResult.build(sysUser,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/logout")
    public AjaxResult logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return AjaxResult.build("用户登出",ResultCodeEnum.SUCCESS);
    }
}
