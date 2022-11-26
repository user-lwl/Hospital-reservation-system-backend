package com.lwl.yygh.model.cms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lwl.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 首页Banner实体
 * </p>
 *
 * @author user-lwl
 */
@Data
@ApiModel(description = "首页Banner实体")
@TableName("banner")
public class Banner extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "标题")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "图片地址")
	@TableField("image_url")
	private String imageUrl;

	@ApiModelProperty(value = "链接地址")
	@TableField("link_url")
	private String linkUrl;

	@ApiModelProperty(value = "排序")
	@TableField("sort")
	private Integer sort;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Banner banner = (Banner) o;
		return Objects.equals(getTitle(), banner.getTitle()) && Objects.equals(getImageUrl(), banner.getImageUrl()) && Objects.equals(getLinkUrl(), banner.getLinkUrl()) && Objects.equals(getSort(), banner.getSort());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getTitle(), getImageUrl(), getLinkUrl(), getSort());
	}
}

