package com.lwl.yygh.model.order;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表
 * @TableName order_info
 */
@TableName(value ="order_info")
@Data
@ApiModel(description = "订单")
public class OrderInfo extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "userId")
    @TableField("user_id")
    private Long userId;

    /**
     * 订单交易号
     */
    @ApiModelProperty(value = "订单交易号")
    @TableField("out_trade_no")
    private String outTradeNo;

    /**
     * 医院编号
     */
    @ApiModelProperty(value = "医院编号")
    @TableField("hoscode")
    private String hoscode;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    @TableField("hosname")
    private String hosname;

    /**
     * 科室编号
     */
    @ApiModelProperty(value = "科室编号")
    @TableField("depcode")
    private String depcode;

    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    @TableField("depname")
    private String depname;

    /**
     * 医生职称
     */
    @ApiModelProperty(value = "医生职称")
    @TableField("title")
    private String title;

    /**
     * 排班编号（医院自己的排班主键）
     */
    @ApiModelProperty(value = "排班id")
    @TableField("hos_schedule_id")
    private String hosScheduleId;

    /**
     * 安排日期
     */
    @ApiModelProperty(value = "安排日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("reserve_date")
    private Date reserveDate;

    /**
     * 安排时间（0：上午 1：下午）
     */
    @ApiModelProperty(value = "安排时间（0：上午 1：下午）")
    @TableField("reserve_time")
    private Integer reserveTime;

    /**
     * 就诊人id
     */
    @ApiModelProperty(value = "就诊人id")
    @TableField("patient_id")
    private Long patientId;

    /**
     * 就诊人名称
     */
    @ApiModelProperty(value = "就诊人名称")
    @TableField("patient_name")
    private String patientName;

    /**
     * 就诊人手机
     */
    @ApiModelProperty(value = "就诊人手机")
    @TableField("patient_phone")
    private String patientPhone;

    /**
     * 预约记录唯一标识（医院预约记录主键）
     */
    @ApiModelProperty(value = "预约记录唯一标识（医院预约记录主键）")
    @TableField("hos_record_id")
    private String hosRecordId;

    /**
     * 预约号序
     */
    @ApiModelProperty(value = "预约号序")
    @TableField("number")
    private Integer number;

    /**
     * 建议取号时间
     */
    @ApiModelProperty(value = "建议取号时间")
    @TableField("fetch_time")
    private String fetchTime;

    /**
     * 取号地点
     */
    @ApiModelProperty(value = "取号地点")
    @TableField("fetch_address")
    private String fetchAddress;

    /**
     * 医事服务费
     */
    @ApiModelProperty(value = "医事服务费")
    @TableField("amount")
    private Integer amount;

    /**
     * 退号时间
     */
    @ApiModelProperty(value = "退号时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("quit_time")
    private Date quitTime;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    @TableField("order_status")
    private Integer orderStatus;

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
        OrderInfo other = (OrderInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getHoscode() == null ? other.getHoscode() == null : this.getHoscode().equals(other.getHoscode()))
            && (this.getHosname() == null ? other.getHosname() == null : this.getHosname().equals(other.getHosname()))
            && (this.getDepcode() == null ? other.getDepcode() == null : this.getDepcode().equals(other.getDepcode()))
            && (this.getDepname() == null ? other.getDepname() == null : this.getDepname().equals(other.getDepname()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getHosScheduleId() == null ? other.getHosScheduleId() == null : this.getHosScheduleId().equals(other.getHosScheduleId()))
            && (this.getReserveDate() == null ? other.getReserveDate() == null : this.getReserveDate().equals(other.getReserveDate()))
            && (this.getReserveTime() == null ? other.getReserveTime() == null : this.getReserveTime().equals(other.getReserveTime()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getPatientPhone() == null ? other.getPatientPhone() == null : this.getPatientPhone().equals(other.getPatientPhone()))
            && (this.getHosRecordId() == null ? other.getHosRecordId() == null : this.getHosRecordId().equals(other.getHosRecordId()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getFetchTime() == null ? other.getFetchTime() == null : this.getFetchTime().equals(other.getFetchTime()))
            && (this.getFetchAddress() == null ? other.getFetchAddress() == null : this.getFetchAddress().equals(other.getFetchAddress()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getQuitTime() == null ? other.getQuitTime() == null : this.getQuitTime().equals(other.getQuitTime()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getHoscode() == null) ? 0 : getHoscode().hashCode());
        result = prime * result + ((getHosname() == null) ? 0 : getHosname().hashCode());
        result = prime * result + ((getDepcode() == null) ? 0 : getDepcode().hashCode());
        result = prime * result + ((getDepname() == null) ? 0 : getDepname().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getHosScheduleId() == null) ? 0 : getHosScheduleId().hashCode());
        result = prime * result + ((getReserveDate() == null) ? 0 : getReserveDate().hashCode());
        result = prime * result + ((getReserveTime() == null) ? 0 : getReserveTime().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getPatientPhone() == null) ? 0 : getPatientPhone().hashCode());
        result = prime * result + ((getHosRecordId() == null) ? 0 : getHosRecordId().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getFetchTime() == null) ? 0 : getFetchTime().hashCode());
        result = prime * result + ((getFetchAddress() == null) ? 0 : getFetchAddress().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getQuitTime() == null) ? 0 : getQuitTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userId=" + userId +
                ", outTradeNo=" + outTradeNo +
                ", hoscode=" + hoscode +
                ", hosname=" + hosname +
                ", depcode=" + depcode +
                ", depname=" + depname +
                ", title=" + title +
                ", hosScheduleId=" + hosScheduleId +
                ", reserveDate=" + reserveDate +
                ", reserveTime=" + reserveTime +
                ", patientId=" + patientId +
                ", patientName=" + patientName +
                ", patientPhone=" + patientPhone +
                ", hosRecordId=" + hosRecordId +
                ", number=" + number +
                ", fetchTime=" + fetchTime +
                ", fetchAddress=" + fetchAddress +
                ", amount=" + amount +
                ", quitTime=" + quitTime +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}