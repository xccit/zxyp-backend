package io.xccit.zxyp.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CH_ywx
 * @date 2023/10/13
 * @description 跨域配置
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true) //允许跨域时访问Cookie
                .allowedHeaders("*") //允许的请求头
                .allowedMethods("*") //允许的方法
                .allowedOriginPatterns("*"); //允许的域规则
    }
}
