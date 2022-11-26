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
 * 退款信息表
 * @TableName refund_info
 */
@TableName(value ="refund_info")
@Data
@ApiModel("退款信息")
public class RefundInfo extends BaseEntity implements Serializable {
    /**
     * 对外业务编号
     */
    @ApiModelProperty(value = "对外业务编号")
    @TableField("out_trade_no")
    private String outTradeNo;

    /**
     * 订单编号
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
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    @ApiModelProperty(value = "交易内容")
    @TableField("subject")
    private String subject;

    /**
     * 退款状态
     */
    @ApiModelProperty(value = "退款状态")
    @TableField("refund_status")
    private Integer refundStatus;

    /**
     * 回调信息
     */
    @ApiModelProperty(value = "回调时间")
    @TableField("callback_time")
    private String callbackContent;

    /**
     * 回调信息
     */
    @ApiModelProperty(value = "回调信息")
    @TableField("callback_content")
    private Date callbackTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RefundInfo that = (RefundInfo) o;
        return Objects.equals(getOutTradeNo(), that.getOutTradeNo()) && Objects.equals(getOrderId(), that.getOrderId()) && Objects.equals(getPaymentType(), that.getPaymentType()) && Objects.equals(getTradeNo(), that.getTradeNo()) && Objects.equals(getTotalAmount(), that.getTotalAmount()) && Objects.equals(getSubject(), that.getSubject()) && Objects.equals(getRefundStatus(), that.getRefundStatus()) && Objects.equals(getCallbackContent(), that.getCallbackContent()) && Objects.equals(getCallbackTime(), that.getCallbackTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOutTradeNo(), getOrderId(), getPaymentType(), getTradeNo(), getTotalAmount(), getSubject(), getRefundStatus(), getCallbackContent(), getCallbackTime());
    }

    @Override
    public String toString() {
        return "RefundInfo{" +
                "outTradeNo='" + outTradeNo + '\'' +
                ", orderId=" + orderId +
                ", paymentType=" + paymentType +
                ", tradeNo='" + tradeNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", subject='" + subject + '\'' +
                ", refundStatus=" + refundStatus +
                ", callbackContent='" + callbackContent + '\'' +
                ", callbackTime=" + callbackTime +
                '}';
    }
}