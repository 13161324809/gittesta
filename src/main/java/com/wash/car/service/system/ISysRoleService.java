package com.wash.car.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wash.car.DTO.system.SysRoleDTO;
import com.wash.car.entity.system.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRoleDTO> findListByUserId(int id) throws Exception;

}
