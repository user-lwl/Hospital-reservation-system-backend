package com.lwl.yygh.common.exception;

import com.lwl.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * 自定义全局异常类
 *
 * @author user-lwl
 */
@Data
@ApiModel(value = "自定义全局异常类")
public class YyghException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private final Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message 错误信息
     * @param code 状态码
     */
    public YyghException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum 结果状态码
     */
    public YyghException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YyghException that = (YyghException) o;
        return Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
