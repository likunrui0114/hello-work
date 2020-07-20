/**
 * 英才汇硕信息科技有限公司 (c) 版权所有  2020-2023
 */
package com.l319.eduo2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.l319.eduo2o.pojo.ShopCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public interface ShopCategoryMapper {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
