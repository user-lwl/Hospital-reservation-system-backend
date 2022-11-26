package com.lwl.yygh.model.hosp;

import com.alibaba.fastjson.JSONObject;
import com.lwl.yygh.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * <p>
 * Hospital
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "Hospital")
@Document("Hospital")
public class Hospital extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "医院编号")
	@Indexed(unique = true) //唯一索引
	private String hoscode;

	@ApiModelProperty(value = "医院名称")
	@Indexed //普通索引
	private String hosname;

	@ApiModelProperty(value = "医院类型")
	private String hostype;

	@ApiModelProperty(value = "省code")
	private String provinceCode;

	@ApiModelProperty(value = "市code")
	private String cityCode;

	@ApiModelProperty(value = "区code")
	private String districtCode;

	@ApiModelProperty(value = "详情地址")
	private String address;

	@ApiModelProperty(value = "医院logo")
	private String logoData;

	@ApiModelProperty(value = "医院简介")
	private String intro;

	@ApiModelProperty(value = "坐车路线")
	private String route;

	@ApiModelProperty(value = "状态 0：未上线 1：已上线")
	private Integer status;

	//预约规则
	@ApiModelProperty(value = "预约规则")
	private BookingRule bookingRule;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Hospital hospital = (Hospital) o;
		return Objects.equals(getHoscode(), hospital.getHoscode()) && Objects.equals(getHosname(), hospital.getHosname()) && Objects.equals(getHostype(), hospital.getHostype()) && Objects.equals(getProvinceCode(), hospital.getProvinceCode()) && Objects.equals(getCityCode(), hospital.getCityCode()) && Objects.equals(getDistrictCode(), hospital.getDistrictCode()) && Objects.equals(getAddress(), hospital.getAddress()) && Objects.equals(getLogoData(), hospital.getLogoData()) && Objects.equals(getIntro(), hospital.getIntro()) && Objects.equals(getRoute(), hospital.getRoute()) && Objects.equals(getStatus(), hospital.getStatus()) && Objects.equals(getBookingRule(), hospital.getBookingRule());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHoscode(), getHosname(), getHostype(), getProvinceCode(), getCityCode(), getDistrictCode(), getAddress(), getLogoData(), getIntro(), getRoute(), getStatus(), getBookingRule());
	}
}

