package io.xccit.zxyp.mapper.product;

import io.xccit.zxyp.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/30
 * @description 品牌Mapper
 */
@Mapper
public interface BrandMapper {

    /**
     * 品牌列表
     * @return
     */
    List<Brand> list();

    /**
     * 品牌添加
     * @param brand
     */
    void save(Brand brand);

    /**
     * 品牌修改
     * @param brand
     */
    void update(Brand brand);

    /**
     * 品牌删除,根据ID列表
     * @param ids
     */
    void remove(List<Long> ids);
}
