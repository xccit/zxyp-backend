package io.xccit.zxyp.anno;

import io.xccit.zxyp.interceptor.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 远程调用处理器开启注解
 * <p>引入远程调用处理器,在需要远程调用并且丢失请求头后业务无法进行的的模块启动类上添加此注解即可</p>
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {
}