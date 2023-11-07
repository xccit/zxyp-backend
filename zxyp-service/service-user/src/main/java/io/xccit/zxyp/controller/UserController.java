package io.xccit.zxyp.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.model.dto.h5.UserLoginDto;
import io.xccit.zxyp.model.dto.h5.UserRegisterDto;
import io.xccit.zxyp.model.entity.user.UserInfo;
import io.xccit.zxyp.model.vo.common.AjaxResult;
import io.xccit.zxyp.model.vo.common.ResultCodeEnum;
import io.xccit.zxyp.model.vo.h5.UserBrowseHistoryVo;
import io.xccit.zxyp.model.vo.h5.UserCollectVo;
import io.xccit.zxyp.model.vo.h5.UserInfoVo;
import io.xccit.zxyp.service.IUserService;
import io.xccit.zxyp.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
     *
     * @param userRegisterDto
     * @return
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return AjaxResult.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户登录
     *
     * @param userLoginDto
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDto userLoginDto) {
        String token = userService.login(userLoginDto);
        return AjaxResult.build(token, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取当前登录用户信息
     *
     * @param request
     * @return
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("auth/getCurrentUserInfo")
    public AjaxResult<UserInfoVo> getCurrentUser(HttpServletRequest request) {
//        UserInfoVo userInfo = userService.getCurrentUser(request.getHeader("token"));
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return AjaxResult.build(userInfoVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户是否收藏商品接口
     *
     * @param skuId
     * @return
     */
    @Operation(summary = "用户是否收藏商品接口")
    @GetMapping("/isCollect/{skuId}")
    public AjaxResult<Boolean> isCollect(@PathVariable Long skuId) {
        boolean isCollect = userService.isCollect(skuId);
        return AjaxResult.build(isCollect, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户收藏信息分页列表
     * @param page
     * @param limit
     * @return
     */
    @Operation(summary = "用户收藏信息分页列表")
    @GetMapping("/auth/findUserCollectPage/{page}/{limit}")
    public AjaxResult<PageInfo<UserCollectVo>> listCollectPage(@PathVariable Integer page, @PathVariable Integer limit){
        PageInfo<UserCollectVo> userCollectVoPageInfo = userService.listCollectPage(page,limit);
        return AjaxResult.build(userCollectVoPageInfo,ResultCodeEnum.SUCCESS);
    }

    /**
     * 收藏商品
     * @param skuId
     * @return
     */
    @Operation(summary = "收藏商品")
    @GetMapping("/auth/collect/{skuId}")
    public AjaxResult<Boolean> collectSku(@PathVariable Long skuId){
        boolean isCollect = userService.collectSku(skuId);
        return AjaxResult.build(isCollect,ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户浏览历史分页列表
     * @param page
     * @param limit
     * @return
     */
    @Operation(summary = "用户浏览历史分页列表")
    @GetMapping("/auth/findUserBrowseHistoryPage/{page}/{limit}")
    public AjaxResult<PageInfo<UserBrowseHistoryVo>> listHistoryBrowse(@PathVariable Integer page,
                                                                       @PathVariable Integer limit){
        PageInfo<UserBrowseHistoryVo> historyVoPageInfo = userService.listUserBrowseHistoryPage(page,limit);
        return AjaxResult.build(historyVoPageInfo,ResultCodeEnum.SUCCESS);
    }
}
