package com.lwl.yygh.model.acl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "权限")
@TableName("acl_permission")
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "所属上级")
	@TableField("pid")
	private Long pid;

	@ApiModelProperty(value = "名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "类型(1:菜单,2:按钮)")
	@TableField("type")
	private Integer type;

	@ApiModelProperty(value = "权限值")
	@TableField("permission_value")
	private String permissionValue;

	@ApiModelProperty(value = "路径")
	@TableField("path")
	private String path;

	@ApiModelProperty(value = "component")
	@TableField("component")
	private String component;

	@ApiModelProperty(value = "图标")
	@TableField("icon")
	private String icon;

	@ApiModelProperty(value = "状态(0:禁止,1:正常)")
	@TableField("status")
	private Integer status;

	@ApiModelProperty(value = "层级")
	@TableField(exist = false)
	private Integer level;

	@ApiModelProperty(value = "下级")
	@TableField(exist = false)
	private List<Permission> children;

	@ApiModelProperty(value = "是否选中")
	@TableField(exist = false)
	private boolean isSelect;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Permission that = (Permission) o;
		return isSelect == that.isSelect && Objects.equals(pid, that.pid) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(permissionValue, that.permissionValue) && Objects.equals(path, that.path) && Objects.equals(component, that.component) && Objects.equals(icon, that.icon) && Objects.equals(status, that.status) && Objects.equals(level, that.level) && Objects.equals(children, that.children);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), pid, name, type, permissionValue, path, component, icon, status, level, children, isSelect);
	}
}

