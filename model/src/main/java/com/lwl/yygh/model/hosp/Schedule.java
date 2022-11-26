package com.lwl.yygh.model.hosp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lwl.yygh.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
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
@Document("Schedule")
public class Schedule extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	@Indexed //普通索引
	private String hoscode;

	@ApiModelProperty(value = "科室编号")
	@Indexed //普通索引
	private String depcode;

	@ApiModelProperty(value = "职称")
	private String title;

	@ApiModelProperty(value = "医生名称")
	private String docname;

	@ApiModelProperty(value = "擅长技能")
	private String skill;

	@ApiModelProperty(value = "排班日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date workDate;

	@ApiModelProperty(value = "排班时间（0：上午 1：下午）")
	private Integer workTime;

	@ApiModelProperty(value = "可预约数")
	private Integer reservedNumber;

	@ApiModelProperty(value = "剩余预约数")
	private Integer availableNumber;

	@ApiModelProperty(value = "挂号费")
	private BigDecimal amount;

	@ApiModelProperty(value = "排班状态（-1：停诊 0：停约 1：可约）")
	private Integer status;

	@ApiModelProperty(value = "排班编号（医院自己的排班主键）")
	@Indexed //普通索引
	private String hosScheduleId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Schedule schedule = (Schedule) o;
		return Objects.equals(getHoscode(), schedule.getHoscode()) && Objects.equals(getDepcode(), schedule.getDepcode()) && Objects.equals(getTitle(), schedule.getTitle()) && Objects.equals(getDocname(), schedule.getDocname()) && Objects.equals(getSkill(), schedule.getSkill()) && Objects.equals(getWorkDate(), schedule.getWorkDate()) && Objects.equals(getWorkTime(), schedule.getWorkTime()) && Objects.equals(getReservedNumber(), schedule.getReservedNumber()) && Objects.equals(getAvailableNumber(), schedule.getAvailableNumber()) && Objects.equals(getAmount(), schedule.getAmount()) && Objects.equals(getStatus(), schedule.getStatus()) && Objects.equals(getHosScheduleId(), schedule.getHosScheduleId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHoscode(), getDepcode(), getTitle(), getDocname(), getSkill(), getWorkDate(), getWorkTime(), getReservedNumber(), getAvailableNumber(), getAmount(), getStatus(), getHosScheduleId());
	}
}

