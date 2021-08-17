package com.wash.car.DTO.system;

import com.wash.car.entity.system.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuDTO extends SysMenu {

    List<SysMenuDTO> childMenu;

}
