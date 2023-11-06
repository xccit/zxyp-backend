package io.xccit.zxyp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CH_ywx
 * @date 2023/11/6
 * @description
 */
@ComponentScan(basePackages = {"io.xccit.zxyp.**"})
@EnableCaching
@EnableEncryptableProperties //开启数据源密码加密
@MapperScan(basePackages = {"io.xccit.zxyp.mapper"})
@SpringBootApplication
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class,args);
        System.out.println("❤甄选优品前台用户服务启动成功\n" +
                "/*\n" +
                " *                        _oo0oo_\n" +
                " *                       o8888888o\n" +
                " *                       88\" . \"88\n" +
                " *                       (| -_- |)\n" +
                " *                       0\\  =  /0\n" +
                " *                     ___/`---'\\___\n" +
                " *                   .' \\\\|     |// '.\n" +
                " *                  / \\\\|||  :  |||// \\\n" +
                " *                 / _||||| -:- |||||- \\\n" +
                " *                |   | \\\\\\  - /// |   |\n" +
                " *                | \\_|  ''\\---/''  |_/ |\n" +
                " *                \\  .-\\__  '-'  ___/-. /\n" +
                " *              ___'. .'  /--.--\\  `. .'___\n" +
                " *           .\"\" '<  `.___\\_<|>_/___.' >' \"\".\n" +
                " *          | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                " *          \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /\n" +
                " *      =====`-.____`.___ \\_____/___.-`___.-'=====\n" +
                " *                        `=---='\n" +
                " *\n" +
                " *\n" +
                " *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                " *\n" +
                " *            佛祖保佑       永不宕机     永无BUG");
    }
}
