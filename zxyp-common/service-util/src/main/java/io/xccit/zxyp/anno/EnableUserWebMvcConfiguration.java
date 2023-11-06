package io.xccit.zxyp.anno;

import io.xccit.zxyp.config.UserWebMvcConfiguration;
import io.xccit.zxyp.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author CH_ywx
 * @date 2023/11/06
 * @description 自定义开启前台拦截器注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserLoginAuthInterceptor.class , UserWebMvcConfiguration.class})
public @interface EnableUserWebMvcConfiguration {

}