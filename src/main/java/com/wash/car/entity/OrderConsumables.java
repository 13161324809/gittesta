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
 * 耗材订单
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderConsumables对象", description="耗材订单")
public class OrderConsumables implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "耗材名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "总价")
    private BigDecimal price;

    @ApiModelProperty(value = "审核状态（0：待审核；1：已审核；2：审核拒绝）")
    private String status;

    @ApiModelProperty(value = "审核拒绝原因")
    private String refuseReason;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预订人")
    private Integer createBy;

    @ApiModelProperty(value = "审核时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "审核人")
    private Integer auditBy;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private Integer updateBy;


}
