package com.lwl.hospital.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * Schedule
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "Schedule")
@TableName("schedule")
public class Schedule extends BaseNoAutoEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	@TableField("hoscode")
	private String hoscode;

	@ApiModelProperty(value = "科室编号")
	@TableField("depcode")
	private String depcode;

	@ApiModelProperty(value = "职称")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "医生名称")
	@TableField("docname")
	private String docname;

	@ApiModelProperty(value = "擅长技能")
	@TableField("skill")
	private String skill;

	@ApiModelProperty(value = "安排日期")
	@TableField("work_date")
	private String workDate;

	@ApiModelProperty(value = "安排时间（0：上午 1：下午）")
	@TableField("work_time")
	private Integer workTime;

	@ApiModelProperty(value = "可预约数")
	@TableField("reserved_number")
	private Integer reservedNumber;

	@ApiModelProperty(value = "剩余预约数")
	@TableField("available_number")
	private Integer availableNumber;

	@ApiModelProperty(value = "挂号费")
	@TableField("amount")
	private String amount;

	@ApiModelProperty(value = "排班状态（-1：停诊 0：停约 1：可约）")
	@TableField("status")
	private Integer status;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Schedule schedule = (Schedule) o;
		return Objects.equals(getHoscode(), schedule.getHoscode()) && Objects.equals(getDepcode(), schedule.getDepcode()) && Objects.equals(getTitle(), schedule.getTitle()) && Objects.equals(getDocname(), schedule.getDocname()) && Objects.equals(getSkill(), schedule.getSkill()) && Objects.equals(getWorkDate(), schedule.getWorkDate()) && Objects.equals(getWorkTime(), schedule.getWorkTime()) && Objects.equals(getReservedNumber(), schedule.getReservedNumber()) && Objects.equals(getAvailableNumber(), schedule.getAvailableNumber()) && Objects.equals(getAmount(), schedule.getAmount()) && Objects.equals(getStatus(), schedule.getStatus());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHoscode(), getDepcode(), getTitle(), getDocname(), getSkill(), getWorkDate(), getWorkTime(), getReservedNumber(), getAvailableNumber(), getAmount(), getStatus());
	}
}

