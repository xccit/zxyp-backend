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
        System.out.println("❤甄选优品前台购物车服务启动成功\n" +
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
