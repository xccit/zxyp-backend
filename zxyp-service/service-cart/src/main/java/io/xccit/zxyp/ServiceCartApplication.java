package io.xccit.zxyp;


import io.xccit.zxyp.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description 购物车模块启动类
 */
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {"io.xccit.zxyp.client.product"}) //开启远程调用
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServiceCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCartApplication.class,args);
        System.out.println("❤甄选优品前台购物车服务启动成功");
    }
}
