package io.xccit.zxyp.config;

import com.github.xiaoymin.knife4j.core.model.OpenAPIInfo;
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
     * @return 管理员接口
     */
    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("管理员API")
                .pathsToMatch("/admin/**")
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
                        .version("1.0")
                        .description("甄选优品API文档")
                        .contact(new Contact().name("xccit").email("darkhorse_1209@outlook.com"))
        );
    }
}
