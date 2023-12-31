package io.xccit.zxyp.config;

import io.xccit.zxyp.interceptor.LoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CH_ywx
 * @date 2023/10/13
 * @description WEB配置
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private WebAuthConfigProperties webAuthConfigProperties;

    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true) //允许跨域时访问Cookie
                .allowedHeaders("*") //允许的请求头
                .allowedMethods("*") //允许的方法
                .allowedOriginPatterns("*"); //允许的域规则
    }

    /**
     * 拦截器注册
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns(webAuthConfigProperties.getNoAuthUrls())
                .excludePathPatterns("/doc.html", "swagger-ui.html", "/v3/api-docs/**", "/doc.html",
                        "/webjars/**", "/swagger-resources", "/swagger-ui/**")
                .addPathPatterns("/**");
    }

    /**
     * 静态资源映射,配置doc.html是为了解决Swagger提示未开启js的问题
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
