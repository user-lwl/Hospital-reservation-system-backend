package com.lwl.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.*;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 医院设置表
 * @TableName hospital_set
 */
@TableName(value ="hospital_set")
@Data
@ApiModel(description = "医院设置")
public class HospitalSet extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    @TableField("hosname")
    private String hosname;

    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    @TableField("hoscode")
    private String hoscode;

    /**
     * api基础路径
     */
    @ApiModelProperty(value = "api基础路径")
    @TableField("api_url")
    private String apiUrl;

    /**
     * 签名秘钥
     */
    @ApiModelProperty(value = "签名秘钥")
    @TableField("sign_key")
    private String signKey;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人姓名")
    @TableField("contacts_name")
    private String contactsName;

    /**
     * 联系人手机
     */
    @ApiModelProperty(value = "联系人手机")
    @TableField("contacts_phone")
    private String contactsPhone;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HospitalSet other = (HospitalSet) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getHosname() == null ? other.getHosname() == null : this.getHosname().equals(other.getHosname()))
            && (this.getHoscode() == null ? other.getHoscode() == null : this.getHoscode().equals(other.getHoscode()))
            && (this.getApiUrl() == null ? other.getApiUrl() == null : this.getApiUrl().equals(other.getApiUrl()))
            && (this.getSignKey() == null ? other.getSignKey() == null : this.getSignKey().equals(other.getSignKey()))
            && (this.getContactsName() == null ? other.getContactsName() == null : this.getContactsName().equals(other.getContactsName()))
            && (this.getContactsPhone() == null ? other.getContactsPhone() == null : this.getContactsPhone().equals(other.getContactsPhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getHosname() == null) ? 0 : getHosname().hashCode());
        result = prime * result + ((getHoscode() == null) ? 0 : getHoscode().hashCode());
        result = prime * result + ((getApiUrl() == null) ? 0 : getApiUrl().hashCode());
        result = prime * result + ((getSignKey() == null) ? 0 : getSignKey().hashCode());
        result = prime * result + ((getContactsName() == null) ? 0 : getContactsName().hashCode());
        result = prime * result + ((getContactsPhone() == null) ? 0 : getContactsPhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hosname=").append(hosname);
        sb.append(", hoscode=").append(hoscode);
        sb.append(", apiUrl=").append(apiUrl);
        sb.append(", signKey=").append(signKey);
        sb.append(", contactsName=").append(contactsName);
        sb.append(", contactsPhone=").append(contactsPhone);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}