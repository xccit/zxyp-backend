package io.xccit.zxyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 网关启动类
 */
@ComponentScan(basePackages = {"io.xccit.zxyp.**"})
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
        System.out.println("❤甄选优品网关组件启动成功O(∩_∩)O~~");
    }
}
