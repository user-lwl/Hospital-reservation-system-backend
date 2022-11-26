package com.lwl.yygh.model.hosp;

import com.lwl.yygh.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * <p>
 * Department
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "Department")
@Document("Department")
public class Department extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	@Indexed //普通索引
	private String hoscode;

	@ApiModelProperty(value = "科室编号")
	@Indexed(unique = true) //唯一索引
	private String depcode;

	@ApiModelProperty(value = "科室名称")
	private String depname;

	@ApiModelProperty(value = "科室描述")
	private String intro;

	@ApiModelProperty(value = "大科室编号")
	private String bigcode;

	@ApiModelProperty(value = "大科室名称")
	private String bigname;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Department that = (Department) o;
		return Objects.equals(getHoscode(), that.getHoscode()) && Objects.equals(getDepcode(), that.getDepcode()) && Objects.equals(getDepname(), that.getDepname()) && Objects.equals(getIntro(), that.getIntro()) && Objects.equals(getBigcode(), that.getBigcode()) && Objects.equals(getBigname(), that.getBigname());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHoscode(), getDepcode(), getDepname(), getIntro(), getBigcode(), getBigname());
	}
}

