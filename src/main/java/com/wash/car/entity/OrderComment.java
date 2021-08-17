package com.wash.car.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单评价
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderComment对象", description="订单评价")
public class OrderComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private Integer orderId;

    @ApiModelProperty(value = "洗车工")
    private Integer carWasher;

    @ApiModelProperty(value = "所属停车场")
    private Integer parkingLotId;

    @ApiModelProperty(value = "满意度")
    private String satisfaction;

    @ApiModelProperty(value = "准时度")
    private String punctuality;

    @ApiModelProperty(value = "清洁度")
    private String cleanliness;

    @ApiModelProperty(value = "礼貌度")
    private String politeness;

    @ApiModelProperty(value = "综合评价")
    private String comprehensiveEvaluation;

    @ApiModelProperty(value = "评价时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "评价人")
    private Integer createBy;


}
