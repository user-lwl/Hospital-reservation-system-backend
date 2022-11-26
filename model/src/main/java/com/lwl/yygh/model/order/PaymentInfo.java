package com.lwl.yygh.model.order;

import com.baomidou.mybatisplus.annotation.*;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 支付信息表
 * @TableName payment_info
 */
@TableName(value ="payment_info")
@Data
@ApiModel("支付信息")
public class PaymentInfo extends BaseEntity implements Serializable {
    /**
     * 对外业务编号
     */
    @ApiModelProperty(value = "对外业务编号")
    @TableField("out_trade_no")
    private String outTradeNo;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单编号")
    @TableField("order_id")
    private Long orderId;

    /**
     * 支付类型（微信 支付宝）
     */
    @ApiModelProperty(value = "支付类型（微信 支付宝）")
    @TableField("payment_type")
    private Integer paymentType;

    /**
     * 交易编号
     */
    @ApiModelProperty(value = "交易编号")
    @TableField("trade_no")
    private String tradeNo;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    @ApiModelProperty(value = "交易内容")
    @TableField("subject")
    private String subject;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    @TableField("payment_status")
    private Integer paymentStatus;

    /**
     * 回调时间
     */
    @ApiModelProperty(value = "回调时间")
    @TableField("callback_time")
    private Date callbackTime;

    /**
     * 回调信息
     */
    @ApiModelProperty(value = "回调信息")
    @TableField("callback_content")
    private String callbackContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaymentInfo that = (PaymentInfo) o;
        return Objects.equals(getOutTradeNo(), that.getOutTradeNo()) && Objects.equals(getOrderId(), that.getOrderId()) && Objects.equals(getPaymentType(), that.getPaymentType()) && Objects.equals(getTradeNo(), that.getTradeNo()) && Objects.equals(getTotalAmount(), that.getTotalAmount()) && Objects.equals(getSubject(), that.getSubject()) && Objects.equals(getPaymentStatus(), that.getPaymentStatus()) && Objects.equals(getCallbackTime(), that.getCallbackTime()) && Objects.equals(getCallbackContent(), that.getCallbackContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOutTradeNo(), getOrderId(), getPaymentType(), getTradeNo(), getTotalAmount(), getSubject(), getPaymentStatus(), getCallbackTime(), getCallbackContent());
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "outTradeNo='" + outTradeNo + '\'' +
                ", orderId=" + orderId +
                ", paymentType=" + paymentType +
                ", tradeNo='" + tradeNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", subject='" + subject + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", callbackTime=" + callbackTime +
                ", callbackContent='" + callbackContent + '\'' +
                '}';
    }
}