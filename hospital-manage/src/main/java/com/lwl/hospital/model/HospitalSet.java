package com.lwl.hospital.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * HospitalSet
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "HospitalSet")
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	private String hoscode;

	@ApiModelProperty(value = "签名秘钥")
	private String signKey;

	@ApiModelProperty(value = "api基础路径")
	private String apiUrl;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		HospitalSet that = (HospitalSet) o;
		return Objects.equals(getHoscode(), that.getHoscode()) && Objects.equals(getSignKey(), that.getSignKey()) && Objects.equals(getApiUrl(), that.getApiUrl());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHoscode(), getSignKey(), getApiUrl());
	}
}

