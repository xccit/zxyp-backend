package io.xccit.zxyp.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.xccit.zxyp.mapper.product.CategoryBrandMapper;
import io.xccit.zxyp.model.dto.product.CategoryBrandDto;
import io.xccit.zxyp.model.entity.product.CategoryBrand;
import io.xccit.zxyp.service.product.ICategoryBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/31
 * @description
 */
@Slf4j
@Service
public class CategoryBrandServiceImpl implements ICategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    /**
     * 品牌分类分页条件列表
     *
     * @param current
     * @param pageSize
     * @param categoryBrandDto
     * @return
     */
    @Override
    public PageInfo<CategoryBrand> listCategoryBrandPage(Integer current, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(current,pageSize);
        List<CategoryBrand> categoryBrandList = categoryBrandMapper.listCategoryBrandPage(categoryBrandDto);
        PageInfo<CategoryBrand> categoryBrandPageInfo = new PageInfo<>(categoryBrandList);
        return categoryBrandPageInfo;
    }
}