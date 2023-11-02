package io.xccit.zxyp.utils;

import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.model.entity.system.OperLog;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 封装操作日志实体类
 */
public class LogUtil {
    //操作执行之后调用
    public static void afterHandlLog(Log sysLog, Object proceed,
                                     OperLog operLog, int status ,
                                     String errorMsg) {
        if(sysLog.isSaveResponseData()) {
            operLog.setJsonResult(JSON.toJSONString(proceed));
        }
        operLog.setStatus(status);
        operLog.setErrorMsg(errorMsg);
    }

    //操作执行之前调用
    public static void beforeHandleLog(Log sysLog,
                                       ProceedingJoinPoint joinPoint,
                                       OperLog operLog) {

        // 设置操作模块名称
        operLog.setTitle(sysLog.title());
        operLog.setOperatorType(sysLog.operatorType().name());

        // 获取目标方法信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature() ;
        Method method = methodSignature.getMethod();
        operLog.setMethod(method.getDeclaringClass().getName());

        // 获取请求相关参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        operLog.setRequestMethod(request.getMethod());
        operLog.setOperUrl(request.getRequestURI());
        operLog.setOperIp(request.getRemoteAddr());

        // 设置请求参数
        if(sysLog.isSaveRequestData()) {
            String requestMethod = operLog.getRequestMethod();
            if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                String params = Arrays.toString(joinPoint.getArgs());
                operLog.setOperParam(params);
            }
        }
        operLog.setOperName(AuthContextUtil.getObj().getUserName());
    }
}
