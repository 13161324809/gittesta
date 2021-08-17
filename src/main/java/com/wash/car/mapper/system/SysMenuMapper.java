package com.wash.car.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wash.car.DTO.system.SysMenuDTO;
import com.wash.car.entity.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenuDTO> findListByUserId(@Param(value="id") int id);

    List<SysMenuDTO> findList();

}
