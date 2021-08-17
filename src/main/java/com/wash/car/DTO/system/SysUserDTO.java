package com.wash.car.DTO.system;

import com.wash.car.entity.system.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDTO extends SysUser {

    List<SysRoleDTO> roleList;

}
