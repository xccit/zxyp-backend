package io.xccit.zxyp.model.vo.h5;


import io.swagger.v3.oas.annotations.media.Schema;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.model.entity.product.ProductSku;
import lombok.Data;

import java.util.List;

/**
 *
 * @author China_ywx
 * @date 2023/11/02
 * @description 首页数据
 */
@Schema(title = "首页数据封装")
@Data
public class IndexVo {

    private List<Category> categoryList ;       // 一级分类的类别数据
    private List<ProductSku> productSkuList ;   // 畅销商品列表数据

}