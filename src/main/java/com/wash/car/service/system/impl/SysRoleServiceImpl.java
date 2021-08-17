package com.wash.car.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wash.car.DTO.system.SysRoleDTO;
import com.wash.car.entity.system.SysRole;
import com.wash.car.mapper.system.SysRoleMapper;
import com.wash.car.service.system.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author washcar
 * @since 2021-06-16
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleDTO> findListByUserId(int id) throws Exception{
        return sysRoleMapper.findListByUserId(id);
    }

}
