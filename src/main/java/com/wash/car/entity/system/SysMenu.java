package com.wash.car.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author washcar
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysMenu对象", description="菜单")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名")
    private String menuName;

    @ApiModelProperty(value = "菜单链接")
    private String menuUrl;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单标识")
    private String menuCode;

    @ApiModelProperty(value = "菜单类型（1：菜单；2：按钮）")
    private String menuType;

    @ApiModelProperty(value = "菜单排序")
    private String menuSort;

    @ApiModelProperty(value = "上级菜单")
    private String parentMenuId;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否删除（0：正常；1：删除）")
    private String isDelete;


}
