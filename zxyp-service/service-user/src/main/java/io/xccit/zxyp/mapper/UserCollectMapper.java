package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.vo.h5.UserCollectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/11/7
 * @description 用户收藏Mapper
 */
@Mapper
public interface UserCollectMapper {

    /**
     * 用户是否收藏商品
     * @param skuId
     * @param userId
     * @return
     */
    int isCollect(Long userId,Long skuId);

    /**
     * 用户收藏信息分页列表
     * @return
     */
    List<UserCollectVo> listUserCollect(Long userId);

    /**
     * 收藏商品
     * @param userId
     * @param skuId
     * @return
     */
    int collectSku(Long userId, Long skuId);
}
