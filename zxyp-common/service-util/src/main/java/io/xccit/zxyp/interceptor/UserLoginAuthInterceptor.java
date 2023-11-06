package io.xccit.zxyp.interceptor;

import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.constants.RedisPrefixConstant;
import io.xccit.zxyp.model.entity.user.UserInfo;
import io.xccit.zxyp.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * @author CH_ywx
 * @date 2023/11/06
 * @description 前台用户登录信息校验
 */
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        // 如果token不为空，那么此时验证token的合法性
        String userInfoJSON = redisTemplate.opsForValue().get(RedisPrefixConstant.FRONT_USER_PREFIX + token);
        AuthContextUtil.setUserInfo(JSON.parseObject(userInfoJSON , UserInfo.class));
        return true ;
    }

}