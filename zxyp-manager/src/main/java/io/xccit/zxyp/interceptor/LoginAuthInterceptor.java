package io.xccit.zxyp.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.constants.RedisPrefixConstant;
import io.xccit.zxyp.entity.system.SysUser;
import io.xccit.zxyp.utils.AuthContextUtil;
import io.xccit.zxyp.vo.common.AjaxResult;
import io.xccit.zxyp.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


/**
 * @author CH_ywx
 * @date 2023/10/17
 * @description 用户登录信息校验
 */
@Slf4j
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 返回未登录信息
     *
     * @param response
     */
    private void responseNoLoginInfo(HttpServletResponse response) {
        AjaxResult<Object> result = AjaxResult.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(result).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 处理请求之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
            1.获取请求方式,如果请求方式为options预检请求,直接放行
            2.不是预检请求,获取token,token为空返回错误信息
            3.从redis中通过token查询登录用户
            4.Redis没有登录用户则返回错误信息,有的话将登录用户封装到ThreadLocal中,更新Redis中登录用户过期时间
            5.放行请求
         */
        //请求方式判断
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true; //放行请求
        }
        String token = request.getHeader("token");
        //TOKEN为空判断
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response); //返回用户未登录
            return false;
        }
        //Redis查询结果为空
        String loginUserForRedis = (String) redisTemplate.opsForValue().get(RedisPrefixConstant.LOGIN_USER_PREFIX + token);
        if (StrUtil.isEmpty(loginUserForRedis)){
            responseNoLoginInfo(response); //返回用户未登录
            return false;
        }
        //存入ThreadLocal
        SysUser loginUser = JSON.parseObject(loginUserForRedis, SysUser.class);
        assert loginUser != null;
        AuthContextUtil.set(loginUser);
        //Redis更新TOKEN过期时间
        redisTemplate.expire(RedisPrefixConstant.LOGIN_USER_PREFIX + token,60, TimeUnit.MINUTES);
        //放行
        return true;
    }

    /**
     * 操作完成之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除线程共享常量中的登录信息
        AuthContextUtil.removeObj();
    }
}
