package com.lwl.yygh.model.user;

import com.baomidou.mybatisplus.annotation.*;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
@ApiModel("用户信息")
public class UserInfo extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    @TableField("openid")
    private String openid;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    @TableField("name")
    private String name;

    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")
    @TableField("certificates_type")
    private String certificatesType;

    /**
     * 证件编号
     */
    @ApiModelProperty(value = "证件编号")
    @TableField("certificates_no")
    private String certificatesNo;

    /**
     * 证件路径
     */
    @ApiModelProperty(value = "证件路径")
    @TableField("certificates_url")
    private String certificatesUrl;

    /**
     * 认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）
     */
    @ApiModelProperty(value = "认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）")
    @TableField("auth_status")
    private Integer authStatus;

    /**
     * 状态（0：锁定 1：正常）
     */
    @ApiModelProperty(value = "状态（0：锁定 1：正常）")
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
    @ApiModelProperty("更新时间")
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
        UserInfo other = (UserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCertificatesType() == null ? other.getCertificatesType() == null : this.getCertificatesType().equals(other.getCertificatesType()))
            && (this.getCertificatesNo() == null ? other.getCertificatesNo() == null : this.getCertificatesNo().equals(other.getCertificatesNo()))
            && (this.getCertificatesUrl() == null ? other.getCertificatesUrl() == null : this.getCertificatesUrl().equals(other.getCertificatesUrl()))
            && (this.getAuthStatus() == null ? other.getAuthStatus() == null : this.getAuthStatus().equals(other.getAuthStatus()))
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
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCertificatesType() == null) ? 0 : getCertificatesType().hashCode());
        result = prime * result + ((getCertificatesNo() == null) ? 0 : getCertificatesNo().hashCode());
        result = prime * result + ((getCertificatesUrl() == null) ? 0 : getCertificatesUrl().hashCode());
        result = prime * result + ((getAuthStatus() == null) ? 0 : getAuthStatus().hashCode());
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
        sb.append(", openid=").append(openid);
        sb.append(", nickName=").append(nickName);
        sb.append(", phone=").append(phone);
        sb.append(", name=").append(name);
        sb.append(", certificatesType=").append(certificatesType);
        sb.append(", certificatesNo=").append(certificatesNo);
        sb.append(", certificatesUrl=").append(certificatesUrl);
        sb.append(", authStatus=").append(authStatus);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}