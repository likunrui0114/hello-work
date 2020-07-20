package com.l319.eduo2o.pojo;
/**
 * 商品类别
 * @author Administrator
 *
 */

import java.util.Date;

public class ProductCategory {
	//商品类别ID
	private Long productCategoryId;
	//属于哪个店铺
	private Long shopId;
	//商品类别名称
	private String productCategoryName;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
