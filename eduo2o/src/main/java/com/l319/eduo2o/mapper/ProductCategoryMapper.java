package com.l319.eduo2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.l319.eduo2o.pojo.ProductCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public interface ProductCategoryMapper {
	/**
	 * 通过shopId查询店铺商品类别
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);

	/**
	 * 批量新增商品类别
	 * 
	 * @param productcategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productcategoryList);

	/**
	 * 删除指定的商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
