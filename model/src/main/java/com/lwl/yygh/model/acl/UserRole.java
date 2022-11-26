package com.lwl.yygh.model.acl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "用户角色")
@TableName("acl_user_role")
public class UserRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "角色id")
	@TableField("role_id")
	private Long roleId;

	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private Long userId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		UserRole userRole = (UserRole) o;
		return Objects.equals(roleId, userRole.roleId) && Objects.equals(userId, userRole.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), roleId, userId);
	}
}

