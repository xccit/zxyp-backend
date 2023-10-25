package io.xccit.zxyp.controller;

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
    @GetMapping
    public String index(){
        return "redirect:/doc.html";
    }
}
