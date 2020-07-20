package com.l319.eduo2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.ShopCategoryMapper;
import com.l319.eduo2o.pojo.ShopCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public class ShopCategoryMapperTest extends BaseTest{
	@Autowired
	private ShopCategoryMapper shopCategoryMapper;

	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategorieList = shopCategoryMapper.queryShopCategory(null);
		System.out.println(shopCategorieList.size());
//		assertEquals(1, shopCategorieList.size());
//		ShopCategory testShopCategory = new ShopCategory();
//		ShopCategory parentShopCategory = new ShopCategory();
//		parentShopCategory.setShopCategoryId(1L);
//		testShopCategory.setParent(parentShopCategory);
//		List<ShopCategory> queryShopCategory = shopCategoryMapper.queryShopCategory(testShopCategory);
	}
}
