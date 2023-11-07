package io.xccit.zxyp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.xccit.zxyp.annotation.EnableLogAspect;
import io.xccit.zxyp.config.WebAuthConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author CH_ywx
 * @date 2023/10/11
 * @description 管理端启动类
 */
@EnableAsync //异步操作
@EnableLogAspect //开启日志记录
@EnableScheduling //开启定时任务
@EnableEncryptableProperties //开启数据源密码加密
@MapperScan(basePackages = {"io.xccit.zxyp.**.mapper"})
@ComponentScan(basePackages = {"io.xccit.zxyp.**"})
@EnableConfigurationProperties({WebAuthConfigProperties.class})
@ServletComponentScan(basePackages = {"io.xccit.zxyp.config"})
@SpringBootApplication
public class ServiceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class,args);
        System.out.println("❤甄选优品后台管理端API启动成功(≧∇≦)ﾉ\n");
    }
}
