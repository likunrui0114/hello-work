package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.ProductCategoryMapper;
import com.l319.eduo2o.pojo.ProductCategory;

/**
 *
 * @author likunrui
 * @version 1.0
 */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //顺序
public class ProductCategoryMapperTest extends BaseTest {
	@Autowired
	private ProductCategoryMapper productCategoryMapper;

	@Test
	@Ignore
	public void testBQueryByShopId() throws Exception {
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryMapper.queryProductCategoryList(shopId);
		System.out.println("集合大小：" + productCategoryList.size());
		assertEquals(3, productCategoryList.size());
	}

	@Test
	@Ignore
	public void testABathInsertProductCategory() {
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("商品类别1");
		productCategory1.setCreateTime(new Date());
		productCategory1.setPriority(2);
		productCategory1.setShopId(1L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setCreateTime(new Date());
		productCategory2.setPriority(1);
		productCategory2.setShopId(1L);
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
		productCategories.add(productCategory1);
		productCategories.add(productCategory2);
		int num = productCategoryMapper.batchInsertProductCategory(productCategories);
		assertEquals(2, num);
	}

	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryMapper.queryProductCategoryList(shopId);
		for (ProductCategory productCategory : productCategoryList) {
			if ("商品类别3".equals(productCategory.getProductCategoryName())
					|| "商品类别4".equals(productCategory.getProductCategoryName())) {
				int effectedNum = productCategoryMapper.deleteProductCategory(productCategory.getProductCategoryId(),
						shopId);
				assertEquals(1, effectedNum);
			}
		}
	}
}
