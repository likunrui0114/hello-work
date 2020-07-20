package com.l319.eduo2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ShopExecution;
import com.l319.eduo2o.enums.ShopStateEnum;
import com.l319.eduo2o.exception.ShopOperationException;
import com.l319.eduo2o.mapper.ShopMapper;
import com.l319.eduo2o.pojo.Shop;
import com.l319.eduo2o.service.ShopService;
import com.l319.eduo2o.util.ImageUtil;
import com.l319.eduo2o.util.PageCalulator;
import com.l319.eduo2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);//-1 不可用 0审核中 1可用
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopMapper.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");// RuntimeException出错的话 事务会回滚
			} else {// 应用数据类型传递的是地址，基本数据类型传递的是数据的拷贝
				if (thumbnail.getImage() != null) {
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					effectedNum = shopMapper.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	/**
	 * 
	 * @param shop    店铺实体
	 * @param shopImg 店铺图片
	 */
	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// 获取shop图片目录的相对路径
		//根据shopId在相对路径shopId目录下生成图片
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getByShopId(long shopId) {
		return shopMapper.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		if (null == shop || null == shop.getShopId()) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			// 判断是否需要处理图片
			try {
				if (null != thumbnail.getImage() && null != thumbnail.getImageName() && !"".equals(thumbnail.getImageName())) {
					Shop tempShop = shopMapper.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				// 更新店铺信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopMapper.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopMapper.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("modifyShop error:" + e.getMessage());
			}
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalulator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopMapper.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopMapper.queryShopCount(shopCondition);
		ShopExecution shopExecution = new ShopExecution();
		if (null != shopList) {
			shopExecution.setShopList(shopList);
			shopExecution.setCount(count);
		} else {
			shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return shopExecution;
	}
}
