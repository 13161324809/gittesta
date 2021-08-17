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
 * 用户
 * </p>
 *
 * @author washcar
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String userName;

    @ApiModelProperty(value = "中文名")
    private String chineseName;

    @ApiModelProperty(value = "英文名")
    private String englishName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "是否管理员")
    private String isSystem;

    @ApiModelProperty(value = "密码过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date passwordInvalidDate;

    @ApiModelProperty(value = "是否电商")
    private String isOnlineRetailers;

    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    @ApiModelProperty(value = "上次登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

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


}
