package io.xccit.zxyp.annotation;

import io.xccit.zxyp.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 日志标识注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    // 模块名称
    String title();
    // 操作人类别
    OperatorType operatorType() default OperatorType.MANAGE;
    // 业务类型（0其它 1新增 2删除 3修改 4查询）
    int businessType();
    // 是否保存请求的参数
    boolean isSaveRequestData() default true;
    // 是否保存响应的参数
    boolean isSaveResponseData() default true;
}
