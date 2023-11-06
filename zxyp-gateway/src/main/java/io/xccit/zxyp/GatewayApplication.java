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
        System.out.println("❤甄选优品网关组件启动成功O(∩_∩)O~~\n" +
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
