package io.xccit.zxyp.utils;

import io.xccit.zxyp.entity.system.SysUser;

/**
 * @author CH_ywx
 * @date 2023/10/17
 * @description ThreadLocal操作工具类
 * 应用场景:redis存储的用户登录信息只有60分钟,在60分钟之间没有发起任何请求,则redis存储的登录信息失效
 *        60分钟之间发起了请求,通过interceptor调用此工具类,拿到登录信息后操作Redis延迟登录信息过期时间
 */
public class AuthContextUtil {

    private static  ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    /**
     * 设置登录的用户
     * @param sysUser
     */
    public static void set(SysUser sysUser){
        threadLocal.set(sysUser);
    }

    /**
     * 获取登录的用户
     * @return
     */
    public static SysUser get(){
        return threadLocal.get();
    }

    /**
     * 删除登录的用户
     */
    public static void removeObj(){
        threadLocal.remove();
    }
}
