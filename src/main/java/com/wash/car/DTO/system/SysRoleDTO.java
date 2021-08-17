package com.wash.car.DTO.system;

import com.wash.car.entity.system.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDTO extends SysRole {

    List<SysMenuDTO> menuList;

}
