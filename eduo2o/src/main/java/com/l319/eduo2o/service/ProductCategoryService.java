package com.l319.eduo2o.service;

import java.util.List;

import com.l319.eduo2o.dto.ProductCategoryExecution;
import com.l319.eduo2o.exception.ProductCategoryOperationException;
import com.l319.eduo2o.pojo.ProductCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public interface ProductCategoryService {
	/**
	 * 查询shopId店铺商品类别
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * 批量添加商品类别
	 * 
	 * @param productcategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productcategoryList)
			throws ProductCategoryOperationException;

	/**
	 * 将此类别下的商品里的类别id置为空，删除该商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
