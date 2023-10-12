package io.xccit.zxyp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.dto.system.LoginDto;
import io.xccit.zxyp.service.ISysUserService;
import io.xccit.zxyp.vo.common.AjaxResult;
import io.xccit.zxyp.vo.common.ResultCodeEnum;
import io.xccit.zxyp.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 登录接口
 */
@Tag(name = "管理员/用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     * @param loginDto 登录参数
     * @return 登录结果
     */
    @Operation(summary = "用户/管理员登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        if (loginVo != null){
            return AjaxResult.build(loginVo, ResultCodeEnum.LOGIN_SUCCESS);
        }else{
            return AjaxResult.build(null,ResultCodeEnum.LOGIN_ERROR);
        }
    }
}
