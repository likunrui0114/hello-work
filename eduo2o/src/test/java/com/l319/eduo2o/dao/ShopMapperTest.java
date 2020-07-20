package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.ShopMapper;
import com.l319.eduo2o.pojo.Area;
import com.l319.eduo2o.pojo.PersonInfo;
import com.l319.eduo2o.pojo.Shop;
import com.l319.eduo2o.pojo.ShopCategory;

public class ShopMapperTest extends BaseTest {
	@Autowired
	private ShopMapper shopMapper;

	@Test
	public void testQueryShopList() {
		Shop shop = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		childCategory.setParent(parentCategory);
		shop.setShopCategory(childCategory);
		
//		PersonInfo owner = new PersonInfo();
//		owner.setUserId(1L);
//		shop.setOwner(owner);
		List<Shop> queryShopList = shopMapper.queryShopList(shop, 0, 12);
		int count = shopMapper.queryShopCount(shop);
		System.out.println("店铺列表的大小" + queryShopList.size());
		System.out.println("店铺总数" + count);
//		ShopCategory shopCategory = new ShopCategory();
//		shopCategory.setShopCategoryId(1L);
//		shop.setShopCategory(shopCategory);
//		queryShopList = shopMapper.queryShopList(shop, 0, 3);
//		System.out.println("店铺列表的大小" + queryShopList.size());
//		count = shopMapper.queryShopCount(shop);
//		System.out.println("店铺总数" + count);
	}

	@Test
	@Ignore
	public void queryByShopId() {
		long shopId = 1;
		Shop shop = shopMapper.queryByShopId(shopId);
		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getShopCategory().getShopCategoryId());
	}

	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺");
		shop.setShopAddr("test");
		shop.setShopDesc("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int row = shopMapper.insertShop(shop);
		assertEquals(1, row);
	}

	@Test
	@Ignore
	public void testUpdateInsertShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);

		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int row = shopMapper.updateShop(shop);
		assertEquals(1, row);
	}

}
