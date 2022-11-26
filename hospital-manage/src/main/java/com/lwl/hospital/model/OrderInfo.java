package com.lwl.hospital.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * OrderInfo
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "OrderInfo")
@TableName("order_info")
public class OrderInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "排班id")
	@TableField("schedule_id")
	private Long scheduleId;

	@ApiModelProperty(value = "就诊人id")
	@TableField("patient_id")
	private Long patientId;

	@ApiModelProperty(value = "预约号序")
	@TableField("number")
	private Integer number;

	@ApiModelProperty(value = "建议取号时间")
	@TableField("fetch_time")
	private String fetchTime;

	@ApiModelProperty(value = "取号地点")
	@TableField("fetch_address")
	private String fetchAddress;

	@ApiModelProperty(value = "医事服务费")
	@TableField("amount")
	private BigDecimal amount;

	@ApiModelProperty(value = "支付时间")
	@TableField("pay_time")
	private Date payTime;

	@ApiModelProperty(value = "退号时间")
	@TableField("quit_time")
	private Date quitTime;

	@ApiModelProperty(value = "订单状态")
	@TableField("order_status")
	private Integer orderStatus;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		OrderInfo orderInfo = (OrderInfo) o;
		return Objects.equals(getScheduleId(), orderInfo.getScheduleId()) && Objects.equals(getPatientId(), orderInfo.getPatientId()) && Objects.equals(getNumber(), orderInfo.getNumber()) && Objects.equals(getFetchTime(), orderInfo.getFetchTime()) && Objects.equals(getFetchAddress(), orderInfo.getFetchAddress()) && Objects.equals(getAmount(), orderInfo.getAmount()) && Objects.equals(getPayTime(), orderInfo.getPayTime()) && Objects.equals(getQuitTime(), orderInfo.getQuitTime()) && Objects.equals(getOrderStatus(), orderInfo.getOrderStatus());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getScheduleId(), getPatientId(), getNumber(), getFetchTime(), getFetchAddress(), getAmount(), getPayTime(), getQuitTime(), getOrderStatus());
	}
}

