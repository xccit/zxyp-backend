package io.xccit.zxyp.mapper;

import io.xccit.zxyp.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CH_ywx
 * @date 2023/10/28
 * @description 菜单Mapper
 */
@Mapper
public interface SysMenuMapper {
    /**
     * 查询所有菜单
     * @return
     */
    List<SysMenu>  listAll();
}
