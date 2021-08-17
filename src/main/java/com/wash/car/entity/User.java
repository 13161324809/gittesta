package com.wash.car.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "微信 open id")
    private String wxOpenId;

    @ApiModelProperty(value = "微信 union id")
    private String wxUnionId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "身份证正面")
    private String idCardPositive;

    @ApiModelProperty(value = "身份证背面")
    private String idCardBack;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户类型（1：业主；2：洗车工；3：车主）")
    private String status;

    @ApiModelProperty(value = "洗车券")
    private Integer coupons;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "所属停车场")
    private Integer parkingLotId;

    @ApiModelProperty(value = "上次登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "上次登录IP")
    private String lastIp;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private Integer updateBy;

    @ApiModelProperty(value = "洗车币")
    private Integer currency;

    @ApiModelProperty(value = "是否删除（0：正常；1：删除）")
    private String isDelete;

}
