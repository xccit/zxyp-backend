package io.xccit.zxyp.annotation;

import io.xccit.zxyp.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 引入日志切面,manager模块引入log依赖,开启日志功能
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({LogAspect.class})
public @interface EnableLogAspect {
}
