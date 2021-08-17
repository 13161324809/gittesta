package com.wash.car.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author washcar
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleMenu对象", description="角色菜单")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限编号")
    private String roleId;

    @ApiModelProperty(value = "菜单编号")
    private String menuId;


}
