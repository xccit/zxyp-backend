package io.xccit.zxyp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CH_ywx
 * @date 2023/10/11
 * @description 管理端启动类
 */
@MapperScan(basePackages = {"io.xccit.zxyp.**.mapper"})
@SpringBootApplication
public class ServiceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class,args);
        System.out.println("❤甄选优品后台管理端API启动成功(≧∇≦)ﾉ");
    }
}
