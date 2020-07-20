package com.l319.eduo2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ProductExecution;
import com.l319.eduo2o.enums.ProductStateEnum;
import com.l319.eduo2o.exception.ProductOperationException;
import com.l319.eduo2o.exception.ShopOperationException;
import com.l319.eduo2o.mapper.ProductImgMapper;
import com.l319.eduo2o.mapper.ProductMapper;
import com.l319.eduo2o.pojo.Product;
import com.l319.eduo2o.pojo.ProductImg;
import com.l319.eduo2o.service.ProductService;
import com.l319.eduo2o.util.ImageUtil;
import com.l319.eduo2o.util.PageCalulator;
import com.l319.eduo2o.util.PathUtil;

/**
 *
 * @author likunrui
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductImgMapper productImgMapper;

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList)
			throws ProductOperationException {
		if (null != product && null != product.getShop() && null != product.getShop().getShopId()) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);// -1不可用 0下架 1在线展示
			if (null != imageHolder) {
				addThumbnail(product, imageHolder);
			}
			try {
				int effectedNum = productMapper.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建店铺失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建店铺失败" + e.toString());
			}
			if (null != imageHolderList && imageHolderList.size() >= 0) {
				addProductImgList(product, imageHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 添加缩略图
	 * 
	 * @param product
	 * @param imageHolder
	 */
	private void addThumbnail(Product product, ImageHolder imageHolder) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(imageHolder, dest);
		product.setImgAddr(thumbnailAddr);
	}

	private void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		for (ImageHolder imageHolder : imageHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		if (productImgList.size() >= 0) {
			try {
				int effectedNum = productImgMapper.bathInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败" + e.toString());
			}
		}
	}

	@Override
	public Product getProductById(long productId) {
		return productMapper.queryprProductById(productId);
	}

	@Override
	@Transactional
	// 涉及图片（物理层和数据库）删除添加
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList)
			throws ProductOperationException {
		if (null != product && null != product.getShop() && null != product.getShop().getShopId()) {
			product.setLastEditTime(new Date());
			// 商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加。物理层
			if (null != thumbnail) {
				Product temProduct = productMapper.queryprProductById(product.getProductId());
				if (null != temProduct.getImgAddr()) {
					ImageUtil.deleteFileOrPath(temProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			// 详情图片操作，将原来的删除，添加新的
			if (null != imageHolderList && imageHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, imageHolderList);
			}
			try {
				// 更新
				int effectedNum = productMapper.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ShopOperationException("更新商品信息失败：" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 删除某个商品下的所有详情图片
	 * 
	 * @param productId
	 */
	private void deleteProductImgList(long productId) {
		List<ProductImg> productImgList = productImgMapper.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());// 物理
		}
		productImgMapper.deleteProductImgByProductId(productId);// 数据库
	}

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalulator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productMapper.queryProductList(productCondition, rowIndex, pageSize);
		int count = productMapper.queryProductCount(productCondition);
		ProductExecution productExecution = new ProductExecution();
		productExecution.setProductList(productList);
		productExecution.setCount(count);
		return productExecution;
	}
}
