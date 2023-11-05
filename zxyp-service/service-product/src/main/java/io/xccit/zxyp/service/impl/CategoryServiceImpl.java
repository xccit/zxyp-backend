package io.xccit.zxyp.service.impl;

import com.alibaba.fastjson.JSON;
import io.xccit.zxyp.mapper.CategoryMapper;
import io.xccit.zxyp.model.entity.product.Category;
import io.xccit.zxyp.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author CH_ywx
 * @date 2023/11/2
 * @description
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 首页一级分类
     *
     * @return
     */
    @Override
    public List<Category> listOneCategory() {
        //TODO Redis中查
        String categoryListJSON = redisTemplate.opsForValue().get("category:one");
        if (StringUtils.hasText(categoryListJSON)){
            log.info("[listOneCategory]:Redis中的一级分类:"+categoryListJSON);
            List<Category> categoryList = JSON.parseArray(categoryListJSON, Category.class);
            return categoryList;
        }
        //TODO 数据库中查
        List<Category> categoryList = categoryMapper.listOneCategory();
        log.info("[listOneCategory]:从MySQL查询到的一级分类");
        redisTemplate.opsForValue().set("category:one",JSON.toJSONString(categoryList),7, TimeUnit.DAYS);
        return categoryList;
    }

    /**
     * 获取分类树形数据
     *
     * @return
     */
    @Cacheable(value = "category",key = "'list'")
    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryMapper.list();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            //二级分类
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream()
                        .filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue())
                        .collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);
                //三级分类
                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream()
                                .filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
