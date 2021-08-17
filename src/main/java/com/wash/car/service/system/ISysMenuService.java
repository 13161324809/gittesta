package com.wash.car.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wash.car.DTO.system.SysMenuDTO;
import com.wash.car.entity.system.SysMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenuDTO> findListByUserId(int id) throws Exception;

    List<SysMenuDTO> findList() throws Exception;

}
