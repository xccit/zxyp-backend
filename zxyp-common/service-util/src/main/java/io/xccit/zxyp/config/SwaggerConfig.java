package io.xccit.zxyp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CH_ywx
 * @date 2023/10/11
 * @description Swagger配置
 */
@Configuration
public class SwaggerConfig {

    /**
     * 后台用户接口
     * @return
     */
    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("后台用户接口")
                .pathsToMatch("/admin/system/index/**")
                .build();
    }

    /**
     * 通用接口
     * @return
     */
    @Bean
    public GroupedOpenApi commonApi(){
        return GroupedOpenApi.builder()
                .group("通用接口")
                .pathsToMatch("/admin/system/upload/**")
                .build();
    }

    /**
     * @return 系统接口
     */
    @Bean
    public GroupedOpenApi systemApi(){
        return GroupedOpenApi.builder()
                .group("系统接口")
                .pathsToMatch("/admin/system/**")
                .pathsToExclude("/admin/system/index/**","/admin/system/upload/**")
                .build();
    }

    /**
     * @return 订单接口
     */
    @Bean
    public GroupedOpenApi orderApi(){
        return GroupedOpenApi.builder()
                .group("订单接口")
                .pathsToMatch("/admin/order/**")
                .pathsToExclude("/admin/system/index/**","/admin/system/upload/**")
                .build();
    }
    /**
     * @return 操作日志接口
     */
    @Bean
    public GroupedOpenApi operLogApi(){
        return GroupedOpenApi.builder()
                .group("操作日志接口")
                .pathsToMatch("/admin/log/oper/**")
                .pathsToExclude("/admin/system/index/**","/admin/system/upload/**")
                .build();
    }

    /**
     * @return 商品接口
     */
    @Bean
    public GroupedOpenApi productApi(){
        return GroupedOpenApi.builder()
                .group("商品接口")
                .pathsToMatch("/admin/product/**")
                .build();
    }

    /**
     * @return 前台商品相关接口
     */
    @Bean
    public GroupedOpenApi frontProductApi(){
        return GroupedOpenApi.builder()
                .group("前台商品相关接口")
                .pathsToMatch("/api/product/**")
                .build();
    }

    /**
     * @return 文档信息
     */
    @Bean
    public OpenAPI customApiInfo(){
        return new OpenAPI().info(
                new Info()
                        .title("甄选优品API")
                        .version("v1.0")
                        .description("甄选优品API文档,除后台用户接口之外的所有接口测试需要传token到请求头,可以在成功登录之后复制token到全局参数列表" +
                                ",放入headers,key为token,值为获取的token值,登录的用户信息存在Redis中,有效时间一小时,一小时内无任何操作需要重新登录并" +
                                "更新token值")
                        .contact(new Contact().name("xccit").email("darkhorse_1209@outlook.com"))
        );
    }
}
