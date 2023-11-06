package io.xccit.zxyp.utils;

import io.xccit.zxyp.model.entity.system.SysUser;
import io.xccit.zxyp.model.entity.user.UserInfo;

/**
 * @author CH_ywx
 * @date 2023/10/17
 * @description ThreadLocal操作工具类
 * 应用场景:redis存储的用户登录信息只有60分钟,在60分钟之间没有发起任何请求,则redis存储的登录信息失效
 *        60分钟之间发起了请求,通过interceptor调用此工具类,拿到登录信息后操作Redis延迟登录信息过期时间
 */
public class AuthContextUtil {

    //后台
    private static  ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();
    //前台
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;

    /**
     * 设置前台登录用户
     * @param userInfo
     */
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    /**
     * 获取前台登录用户
     * @return
     */
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    /**
     * 删除前台登录用户
     */
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }

    /**
     * 设置登录的用户
     * @param sysUser
     */
    public static void setObj(SysUser sysUser){
        threadLocal.set(sysUser);
    }

    /**
     * 获取登录的用户
     * @return
     */
    public static SysUser getObj(){
        return threadLocal.get();
    }

    /**
     * 删除登录的用户
     */
    public static void removeObj(){
        threadLocal.remove();
    }
}
