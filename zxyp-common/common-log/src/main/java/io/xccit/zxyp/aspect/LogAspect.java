package io.xccit.zxyp.aspect;

import io.xccit.zxyp.annotation.Log;
import io.xccit.zxyp.enums.OperatorType;
import io.xccit.zxyp.model.entity.system.OperLog;
import io.xccit.zxyp.service.IOperLogService;
import io.xccit.zxyp.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description 日志切面,由Spring管理
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private IOperLogService operLogService;

    /**
     * 环绕通知,根据加了@Log注解的方法记录相关日志
     * @param joinPoint 调用业务方法,获取方法信息
     * @param sysLog 标识注解
     * @return
     */
    @Around("@annotation(sysLog)")
    public Object aroundAdviceLog(ProceedingJoinPoint joinPoint, Log sysLog){
        OperLog operLog = new OperLog();
        LogUtil.beforeHandleLog(sysLog,joinPoint,operLog);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            //TODO 无异常
            LogUtil.afterHandlLog(sysLog,proceed,operLog,0,null);
        } catch (Throwable e) {
            //TODO 有异常
            LogUtil.afterHandlLog(sysLog,proceed,operLog,1,e.getCause().getMessage());
            throw new RuntimeException(e);
        }
        //TODO 保存日志
        operLogService.saveOperLog(operLog);
        return proceed;
    }
}
