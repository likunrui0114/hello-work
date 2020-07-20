package com.l319.eduo2o.web.shopadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.l319.eduo2o.dto.ProductCategoryExecution;
import com.l319.eduo2o.dto.Result;
import com.l319.eduo2o.enums.ProductCategoryStateEnum;
import com.l319.eduo2o.exception.ProductCategoryOperationException;
import com.l319.eduo2o.pojo.ProductCategory;
import com.l319.eduo2o.pojo.Shop;
import com.l319.eduo2o.service.ProductCategoryService;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		// remove
		Shop shop = new Shop();
		shop.setShopId(1L);
		request.getSession().setAttribute("currentShop", shop);

		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> productcategoryList = null;
		if (null != currentShop && currentShop.getShopId() > 0) {
			productcategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
			Result<List<ProductCategory>> result = new Result<List<ProductCategory>>(true, productcategoryList);
			return result;
		} else {
			ProductCategoryStateEnum productCategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, productCategoryStateEnum.getStateInfo(),
					productCategoryStateEnum.getState());
		}
	}

	@RequestMapping(value = "/addproductcategories", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addproductcategories(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		for (ProductCategory productCategory : productCategoryList) {
			productCategory.setShopId(currentShop.getShopId());
			productCategory.setCreateTime(new Date());
		}
		if (null != productCategoryList && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution productCategoryExecution = productCategoryService
						.batchAddProductCategory(productCategoryList);
				if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", productCategoryExecution.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}

	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeproductcategory(Long productCategoryId, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (null != productCategoryId && productCategoryId > 0) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution productCategoryExecution = productCategoryService
						.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", productCategoryExecution.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "至少选择一个商品类别");
		}
		return modelMap;
	}
}
