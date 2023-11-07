package io.xccit.zxyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 订单启动类
 */
@SpringBootApplication
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class,args);
        System.out.println("❤甄选优品前台订单模块启动成功O(∩_∩)O~~");
    }
}
