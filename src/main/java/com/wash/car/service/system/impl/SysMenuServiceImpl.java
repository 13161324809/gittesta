package com.wash.car.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wash.car.DTO.system.SysMenuDTO;
import com.wash.car.entity.system.SysMenu;
import com.wash.car.mapper.system.SysMenuMapper;
import com.wash.car.service.system.ISysMenuService;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuDTO> findListByUserId(int id) throws Exception{
        return sysMenuMapper.findListByUserId(id);
    }

    public List<SysMenuDTO> findList() throws Exception{
        return sysMenuMapper.findList();
    }

}
