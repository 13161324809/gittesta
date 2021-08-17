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
 * 订单表
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private Integer orderId;

    @ApiModelProperty(value = "订单状态（0：待支付；1：已支付；2：待评价；3：已完成；4：待退款；5：已取消）")
    private String status;

    @ApiModelProperty(value = "车位号")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "所属停车场")
    private Integer parkingLotId;

    @ApiModelProperty(value = "洗车时间")
    private String carWashingTime;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "车牌号")
    private String licensePlateNumber;

    @ApiModelProperty(value = "品牌车型")
    private String model;

    @ApiModelProperty(value = "车辆颜色")
    private String color;

    @ApiModelProperty(value = "是否使用洗车币支付（0：不使用；1：使用）")
    private String isCurrency;

    @ApiModelProperty(value = "是否使用洗车券（0：不使用；1：使用）")
    private String isCoupons;

    @ApiModelProperty(value = "洗车币")
    private String currency;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal price;

    @ApiModelProperty(value = "是否删除（0：正常；1：删除）")
    private String isDelete;

    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completionTime;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Integer createBy;


}
