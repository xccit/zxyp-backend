package io.xccit.zxyp.controller.system;

import io.xccit.zxyp.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CH_ywx
 * @date 2023/10/20
 * @description
 */
@Controller
@RequestMapping("admin/system/swagger")
public class SwaggerController {
    @Log(title = "后台接口:接口测试",businessType = 0)
    @GetMapping
    public String index(){
        return "redirect:/doc.html";
    }
}
