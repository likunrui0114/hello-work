/**
 * 英才汇硕信息科技有限公司 (c) 版权所有  2020-2023
 */
package com.l319.eduo2o.service;

import java.util.List;

import com.l319.eduo2o.pojo.ShopCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public interface ShopCategoryService {
	public static final String SCLISTKEY = "shopcategorylist";

	/**
	 * 获取商店列表
	 * 
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategorieList(ShopCategory shopCategoryCondition);
}
