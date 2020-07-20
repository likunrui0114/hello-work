package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.ProductImgMapper;
import com.l319.eduo2o.pojo.ProductImg;

/**
 *
 * @author likunrui
 * @version 1.0
 */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //顺序
public class ProductImgMapperTest extends BaseTest {
	@Autowired
	private ProductImgMapper productImgMapper;

	@Test
	@Ignore
	public void testABathInsertProductImg() throws Exception {
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("test1");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("test2");
		productImg2.setImgDesc("测试图片2");
		productImg2.setPriority(2);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgMapper.bathInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}

	@Test
	@Ignore
	public void testCDeleteProductImgByProductId() throws Exception {
		long productId = 1;
		int effectedNum = productImgMapper.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
}
