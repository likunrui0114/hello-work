package com.l319.eduo2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author likunrui
 * @version 1.0
 */
//路由器编写
@Controller
@RequestMapping(value = "shopadmin", method = { RequestMethod.GET })
public class ShopAdminController {

	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}

	@RequestMapping(value = "/shoplist")
	public String shopList() {
		System.out.println("333");
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}

	@RequestMapping(value = "productcategorymanagement", method = RequestMethod.GET)
	private String productCategoryManagement() {
		return "shop/productcategorymanagement";
	}

	@RequestMapping(value = "/productoperation")
	public String productOpreation() {
		return "shop/productoperation";
	}

	@RequestMapping(value = "/productmanagement", method = RequestMethod.GET)
	public String productManagement() {
		return "shop/productmanagement";
	}
}
