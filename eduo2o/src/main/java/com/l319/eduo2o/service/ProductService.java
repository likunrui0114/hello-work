package com.l319.eduo2o.service;

import java.util.List;

import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ProductExecution;
import com.l319.eduo2o.exception.ProductOperationException;
import com.l319.eduo2o.pojo.Product;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public interface ProductService {
	/**
	 * 添加商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param imageHolderList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList)
			throws ProductOperationException;

	/**
	 * 通过id查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);

	/**
	 * 修改商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param imageHolderList
	 * @return
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList)
			throws ProductOperationException;

	/**
	 * 查询商品列表分页，模糊查询，商品状态，店铺Id，店铺类别
	 * 
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
