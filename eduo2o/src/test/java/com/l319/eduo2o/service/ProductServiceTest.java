package com.l319.eduo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ProductExecution;
import com.l319.eduo2o.enums.ProductStateEnum;
import com.l319.eduo2o.exception.ShopOperationException;
import com.l319.eduo2o.pojo.Product;
import com.l319.eduo2o.pojo.ProductCategory;
import com.l319.eduo2o.pojo.Shop;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;

	@Test
	@Ignore
	public void testAddProduct() throws ShopOperationException, FileNotFoundException {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

		File thumbnailFile = new File("E:/bigo2o/test/xiaolongren.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), is);

		File productImg1 = new File("E:/bigo2o/test/xiaolongren.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("E:/bigo2o/test/0004.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> imageHolderList = new ArrayList<ImageHolder>();
		imageHolderList.add(new ImageHolder(productImg1.getName(), is1));
		imageHolderList.add(new ImageHolder(productImg2.getName(), is2));
		ProductExecution productExecution = productService.addProduct(product, imageHolder, imageHolderList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
	}

	@Test
	public void testModifyProduct() throws ShopOperationException, FileNotFoundException {
		Product product = new Product();
		ProductCategory productCategory = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		productCategory.setProductCategoryId(1L);
		product.setProductCategory(productCategory);
		product.setProductId(5L);
		product.setShop(shop);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1");
		File thumbnail = new File("E:/bigo2o/test/0004.jpg");
		InputStream inputStream = new FileInputStream(thumbnail);
		ImageHolder imageHolder = new ImageHolder(thumbnail.getName(), inputStream);

		File thumbnail1 = new File("E:/bigo2o/test/0004.png");
		InputStream inputStream1 = new FileInputStream(thumbnail1);
		File thumbnail2 = new File("E:/bigo2o/test/xiaolongren.jpg");
		InputStream inputStream2 = new FileInputStream(thumbnail2);
		List<ImageHolder> imageHolderList = new ArrayList<ImageHolder>();
		imageHolderList.add(new ImageHolder(thumbnail1.getName(), inputStream1));
		imageHolderList.add(new ImageHolder(thumbnail2.getName(), inputStream2));
		ProductExecution modifyProduct = productService.modifyProduct(product, imageHolder, imageHolderList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), modifyProduct.getState());
	}
}
