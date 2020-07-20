package com.l319.eduo2o.dto;

import java.util.List;

import com.l319.eduo2o.enums.ProductCategoryStateEnum;
import com.l319.eduo2o.pojo.ProductCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public class ProductCategoryExecution {
	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;

	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
		super();
	}

	// 操作失败时使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
		super();
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
	}

	// 成功时使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,
			List<ProductCategory> productCategoryList) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
