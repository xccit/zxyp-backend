package io.xccit.zxyp;

import io.xccit.zxyp.anno.EnableUserTokenFeignInterceptor;
import io.xccit.zxyp.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 订单启动类
 */
@EnableUserTokenFeignInterceptor //开启远程调用处理器
@EnableFeignClients(basePackages = {"io.xccit.zxyp.feign.cart"})
@EnableUserWebMvcConfiguration
@SpringBootApplication
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class,args);
        System.out.println("❤甄选优品前台订单模块启动成功O(∩_∩)O~~");
    }
}
