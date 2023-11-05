package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/5
 * @description 品牌Mapper
 */
@Mapper
public interface BrandMapper {

    /**
     * 查询全部品牌
     * @return
     */
    List<Brand> list();
}
