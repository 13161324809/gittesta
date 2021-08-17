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
 * 停车场
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ParkingLot对象", description="停车场")
public class ParkingLot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "位置")
    private String title;

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

    @ApiModelProperty(value = "是否删除（0：正常；1：删除）")
    private String isDelete;

}
