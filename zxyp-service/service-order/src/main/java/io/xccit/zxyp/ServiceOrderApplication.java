package io.xccit.zxyp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
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
@EnableFeignClients(basePackages = {"io.xccit.zxyp.client.product","io.xccit.zxyp.client.cart","io.xccit.zxyp.client.user"})
@EnableUserWebMvcConfiguration
@SpringBootApplication
@EnableEncryptableProperties //数据源加密
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class,args);
        System.out.println("❤甄选优品前台订单模块启动成功O(∩_∩)O~~");
    }
}
