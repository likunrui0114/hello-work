package com.l319.eduo2o.dto;

import java.util.List;

import com.l319.eduo2o.enums.ProductStateEnum;
import com.l319.eduo2o.pojo.Product;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public class ProductExecution {
	private int state;
	private String stateInfo;
	private int count;
	private Product product;
	private List<Product> productList;

	public ProductExecution() {
		super();
	}
	public ProductExecution(ProductStateEnum productStateEnum) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
	}
	public ProductExecution(ProductStateEnum productStateEnum,Product product) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
		this.product = product;
	}
	public ProductExecution(ProductStateEnum productStateEnum,List<Product> productList) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
		this.productList = productList;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
}
