package io.xccit.zxyp.service;

import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.model.dto.h5.UserLoginDto;
import io.xccit.zxyp.model.dto.h5.UserRegisterDto;
import io.xccit.zxyp.model.entity.user.UserInfo;
import io.xccit.zxyp.model.vo.h5.UserBrowseHistoryVo;
import io.xccit.zxyp.model.vo.h5.UserCollectVo;
import io.xccit.zxyp.model.vo.h5.UserInfoVo;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 前台用户业务层
 */
public interface IUserService {

    /**
     * 用户注册
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    String login(UserLoginDto userLoginDto);

    /**
     * 获取当前登录用户信息
     * @param token
     * @return
     */
    UserInfoVo getCurrentUser(String token);

    /**
     * 用户是否收藏商品接口
     * @param skuId
     * @return
     */
    boolean isCollect(Long skuId);

    /**
     * 用户收藏信息分页列表
     * @param page
     * @param limit
     * @return
     */
    PageInfo<UserCollectVo> listCollectPage(Integer page, Integer limit);

    /**
     * 用户浏览历史分页列表
     * @param page
     * @param limit
     * @return
     */
    PageInfo<UserBrowseHistoryVo> listUserBrowseHistoryPage(Integer page, Integer limit);

    /**
     * 收藏商品
     * @param skuId
     * @return
     */
    boolean collectSku(Long skuId);
}
