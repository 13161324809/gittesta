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
 * 银行卡
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BankAccount对象", description="银行卡")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "银行卡号")
    private String bankNumber;

    @ApiModelProperty(value = "银行卡类型")
    private String bankType;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
