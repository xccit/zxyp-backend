package io.xccit.zxyp.service.product;

import io.xccit.zxyp.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description
 */
public interface ICategoryService {
    /**
     * 分类信息列表
     * @param id
     * @return
     */
    List<Category> listCategory(Long id);

    /**
     * 分类数据导出
     * @param response
     */
    void export(HttpServletResponse response);
}
