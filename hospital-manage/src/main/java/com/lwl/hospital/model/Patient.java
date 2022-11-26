package com.lwl.hospital.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * Patient
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "Patient")
@TableName("patient")
public class Patient extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private Long userId;

	@ApiModelProperty(value = "姓名")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "证件类型")
	@TableField("certificates_type")
	private String certificatesType;

	@ApiModelProperty(value = "证件编号")
	@TableField("certificates_no")
	private String certificatesNo;

	@ApiModelProperty(value = "性别")
	@TableField("sex")
	private Integer sex;

	@ApiModelProperty(value = "出生年月")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("birthdate")
	private String birthdate;

	@ApiModelProperty(value = "手机")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "是否结婚")
	@TableField("is_marry")
	private Integer isMarry;

	@ApiModelProperty(value = "省code")
	@TableField("province_code")
	private String provinceCode;

	@ApiModelProperty(value = "市code")
	@TableField("city_code")
	private String cityCode;

	@ApiModelProperty(value = "区code")
	@TableField("district_code")
	private String districtCode;

	@ApiModelProperty(value = "详情地址")
	@TableField("address")
	private String address;

	@ApiModelProperty(value = "联系人姓名")
	@TableField("contacts_name")
	private String contactsName;

	@ApiModelProperty(value = "联系人证件类型")
	@TableField("contacts_certificates_type")
	private String contactsCertificatesType;

	@ApiModelProperty(value = "联系人证件号")
	@TableField("contacts_certificates_no")
	private String contactsCertificatesNo;

	@ApiModelProperty(value = "联系人手机")
	@TableField("contacts_phone")
	private String contactsPhone;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Patient patient = (Patient) o;
		return Objects.equals(getUserId(), patient.getUserId()) && Objects.equals(getName(), patient.getName()) && Objects.equals(getCertificatesType(), patient.getCertificatesType()) && Objects.equals(getCertificatesNo(), patient.getCertificatesNo()) && Objects.equals(getSex(), patient.getSex()) && Objects.equals(getBirthdate(), patient.getBirthdate()) && Objects.equals(getPhone(), patient.getPhone()) && Objects.equals(getIsMarry(), patient.getIsMarry()) && Objects.equals(getProvinceCode(), patient.getProvinceCode()) && Objects.equals(getCityCode(), patient.getCityCode()) && Objects.equals(getDistrictCode(), patient.getDistrictCode()) && Objects.equals(getAddress(), patient.getAddress()) && Objects.equals(getContactsName(), patient.getContactsName()) && Objects.equals(getContactsCertificatesType(), patient.getContactsCertificatesType()) && Objects.equals(getContactsCertificatesNo(), patient.getContactsCertificatesNo()) && Objects.equals(getContactsPhone(), patient.getContactsPhone());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getUserId(), getName(), getCertificatesType(), getCertificatesNo(), getSex(), getBirthdate(), getPhone(), getIsMarry(), getProvinceCode(), getCityCode(), getDistrictCode(), getAddress(), getContactsName(), getContactsCertificatesType(), getContactsCertificatesNo(), getContactsPhone());
	}
}

