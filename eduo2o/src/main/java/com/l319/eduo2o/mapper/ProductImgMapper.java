package com.l319.eduo2o.mapper;

import java.util.List;

import com.l319.eduo2o.pojo.ProductImg;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public interface ProductImgMapper {
	/**
	 * 批量添加商品详情图片
	 * 
	 * @param productImgList
	 * @return
	 */
	int bathInsertProductImg(List<ProductImg> productImgList);

	/**
	 * 删除指定商品下的所有详情图片
	 * 
	 * @param ProductId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);

	/**
	 * 查询某个商品下的所有详情图片
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);
}
