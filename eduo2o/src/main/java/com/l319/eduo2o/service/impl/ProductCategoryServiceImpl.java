package com.l319.eduo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l319.eduo2o.dto.ProductCategoryExecution;
import com.l319.eduo2o.enums.ProductCategoryStateEnum;
import com.l319.eduo2o.exception.ProductCategoryOperationException;
import com.l319.eduo2o.mapper.ProductCategoryMapper;
import com.l319.eduo2o.mapper.ProductMapper;
import com.l319.eduo2o.pojo.ProductCategory;
import com.l319.eduo2o.service.ProductCategoryService;

/**
 *
 * @author likunrui
 * @version 1.0
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryMapper.queryProductCategoryList(shopId);
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productcategoryList)
			throws ProductCategoryOperationException {
		if (null != productcategoryList && productcategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryMapper.batchInsertProductCategory(productcategoryList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("店铺类别创建失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("bathAddProductCategory error" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO 将此类别下商品id置为空
		try {
			int effectedNum = productMapper.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
		}
		try {
			int effectefNum = productCategoryMapper.deleteProductCategory(productCategoryId, shopId);
			if (effectefNum <= 0) {
				throw new ProductCategoryOperationException("店铺类别删除失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
		}
	}
}
