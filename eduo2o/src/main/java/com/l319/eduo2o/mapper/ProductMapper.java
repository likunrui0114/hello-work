package com.l319.eduo2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.l319.eduo2o.pojo.Product;

/**
 * 
 * @author likunrui
 * @version 1.0
 */
public interface ProductMapper {
	/**
	 * 分页查询，可输入的条件有：商品名 模糊查询，商品状态，店铺Id，商品级别
	 * 
	 * @param prodctCondition
	 * @param beginIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition,
			@Param("beginIndex") int beginIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询商品数目
	 * 
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);

	/**
	 * 插入商品
	 * 
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);

	/**
	 * 通过id查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product queryprProductById(long productId);

	/**
	 * 修改商品信息
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * 删除商品类别时，将此类别下的商品类别ID置为空
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);

}
