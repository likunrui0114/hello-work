package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.ProductMapper;
import com.l319.eduo2o.pojo.Product;
import com.l319.eduo2o.pojo.ProductCategory;
import com.l319.eduo2o.pojo.Shop;

/**
 *
 * @author likunrui
 * @version 1.0
 */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //顺序
public class ProductMapperTest extends BaseTest {
	@Autowired
	private ProductMapper productMapper;

	@Test
	@Ignore
	public void testAInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryId(1L);

		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试1");
		product1.setImgAddr("test1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(productCategory1);
		Product product2 = new Product();
		product2.setProductName("测试2");
		product2.setProductDesc("测试2");
		product2.setImgAddr("test2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(productCategory1);
		Product product3 = new Product();
		product3.setProductName("测试3");
		product3.setProductDesc("测试3");
		product3.setImgAddr("test3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(productCategory1);
		int effectedNum = productMapper.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productMapper.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productMapper.insertProduct(product3);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testQueryProductByProductId() throws Exception {
		long productId = 6;
		Product product = productMapper.queryprProductById(productId);
		assertEquals(2, product.getProductImgList().size());
	}

	@Test
	@Ignore
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		ProductCategory productCategory = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		productCategory.setProductCategoryId(2L);
		product.setProductCategory(productCategory);
		product.setProductId(6L);
		product.setShop(shop);
		product.setProductName("第二个商品");
		int effectedNum = productMapper.updateProduct(product);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testQueryProductList() throws Exception {
		Product product = new Product();
		List<Product> productList = productMapper.queryProductList(product, 0, 3);
		for (Product product1 : productList) {
			System.out.println(product1.toString());
		}
//		int count = productMapper.queryProductCount(product);
//		assertEquals(6, count);

		product.setProductName("商品");
		productList = productMapper.queryProductList(product, 0, 3);
		for (Product product1 : productList) {
			System.out.println(product1.toString());
		}
//		count = productMapper.queryProductCount(product);
//		assertEquals(4, count);
	}
}
