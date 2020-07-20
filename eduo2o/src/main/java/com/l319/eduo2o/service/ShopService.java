package com.l319.eduo2o.service;

import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ShopExecution;
import com.l319.eduo2o.exception.ShopOperationException;
import com.l319.eduo2o.pojo.Shop;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回相应的列表
	 * 
	 * @param shopCondition
	 * @param pageIndx
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

	/**
	 * 通过shopId获取店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);

	/**
	 * 更新店铺信息，包括对图片的处理
	 * 
	 * @param shop
	 * @param shopInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

	/**
	 * 注册店铺信息，包括图片处理
	 * 
	 * @param shop
	 * @param shopInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
