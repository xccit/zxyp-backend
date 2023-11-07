package io.xccit.zxyp.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 远程调用处理器
 * <p>由于远程调用会丢失请求头,所以需要重新获取token并加入请求头,实现RequestInterceptor接口即可</p>
 */
public class UserTokenFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        requestTemplate.header("token" , token) ;
    }

}