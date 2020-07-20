package com.l319.eduo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ShopExecution;
import com.l319.eduo2o.enums.ShopStateEnum;
import com.l319.eduo2o.exception.ShopOperationException;
import com.l319.eduo2o.pojo.Area;
import com.l319.eduo2o.pojo.PersonInfo;
import com.l319.eduo2o.pojo.Shop;
import com.l319.eduo2o.pojo.ShopCategory;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	@Ignore
	public void testGetShopList() {
		Shop shop = new Shop();
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(2L);
		shop.setShopCategory(shopCategory);

		ShopExecution queryShopList = shopService.getShopList(shop, 2, 3);
		System.out.println("店铺列表数" + queryShopList.getShopList().size());

	}

	@Test
	@Ignore
	public void testModifyShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(16L);
		shop.setShopName("修改后的店名称");
		File shopImg = new File("E:/bigo2o/test/xiaolongren.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder iamgHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution shopExecution = shopService.modifyShop(shop, iamgHolder);
		System.out.println("新的图片地址：" + shopExecution.getShop().getShopImg());

	}

	@Test
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(2L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺4");
		shop.setShopAddr("test");
		shop.setShopDesc("test");
		shop.setPhone("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("E:/bigo2o/test/xiaolongren.jpg");
		InputStream inputStream = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), inputStream);
		ShopExecution se = shopService.addShop(shop, imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
	
	
}
