package io.xccit.zxyp.controller.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.xccit.zxyp.service.product.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 品牌控制器
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;
}
