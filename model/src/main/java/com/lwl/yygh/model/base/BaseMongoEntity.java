package com.lwl.yygh.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class BaseMongoEntity implements Serializable {

    @ApiModelProperty(value = "id")
    @Id
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他参数")
    @Transient //被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性
    private Map<String,Object> param = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseMongoEntity that = (BaseMongoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(isDeleted, that.isDeleted) && Objects.equals(param, that.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, isDeleted, param);
    }
}
