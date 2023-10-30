package io.xccit.zxyp.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.system.LoginDto;
import io.xccit.zxyp.model.vo.system.SysMenuVo;
import io.xccit.zxyp.service.system.ISysUserService;
import io.xccit.zxyp.service.system.ValidateCodeService;
import io.xccit.zxyp.utils.AuthContextUtil;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.system.LoginVo;
import io.xccit.zxyp.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/12
 * @description 后台用户接口
 */
@Tag(name = "后台用户接口",description = "管理员登录")
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
    @Operation(summary = "验证码获取接口")
    @GetMapping("/generateValidateCode")
    public AjaxResult<ValidateCodeVo> getValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return AjaxResult.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }


    /**
     * 获取用户信息
     * @return
     */
    @Operation(summary = "获取用户信息")
    @GetMapping("/userinfo")
    public AjaxResult userInfo(){
        return AjaxResult.build(AuthContextUtil.getObj(),ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "用户登出")
    @GetMapping("/logout")
    public AjaxResult logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return AjaxResult.build("用户登出",ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户登录后根据用户ID获取角色信息,根据角色信息获取菜单列表并封装返回
     * @return
     */
    @Operation(summary = "用户动态菜单接口",description = "用户登录后根据用户ID获取角色信息,根据角色信息获取菜单列表并封装返回")
    @GetMapping("/menus")
    public AjaxResult getMenus(){
        List<SysMenuVo> result = sysUserService.getMenus();
        return AjaxResult.build(result,ResultCodeEnum.SUCCESS);
    }
}
