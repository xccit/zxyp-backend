package io.xccit.zxyp.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description
 */
@Controller
@RequestMapping("/admin/system/minio")
public class MinIOController {

    @GetMapping
    public String index(){
        return "redirect:localhost:4523";
    }
}
