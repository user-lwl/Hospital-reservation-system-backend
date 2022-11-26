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
 * 用户
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "用户")
@TableName("acl_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名")
	@TableField("username")
	private String username;

	@ApiModelProperty(value = "密码")
	@TableField("password")
	private String password;

	@ApiModelProperty(value = "昵称")
	@TableField("nick_name")
	private String nickName;

	@ApiModelProperty(value = "用户头像")
	@TableField("salt")
	private String salt;

	@ApiModelProperty(value = "用户签名")
	@TableField("token")
	private String token;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		User user = (User) o;
		return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(nickName, user.nickName) && Objects.equals(salt, user.salt) && Objects.equals(token, user.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), username, password, nickName, salt, token);
	}
}



