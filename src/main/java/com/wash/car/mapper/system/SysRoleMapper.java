package com.wash.car.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wash.car.DTO.system.SysRoleDTO;
import com.wash.car.entity.system.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleDTO> findListByUserId(@Param(value="id") int id);

}
