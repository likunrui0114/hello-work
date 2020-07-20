package com.l319.eduo2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.l319.eduo2o.dto.ImageHolder;
import com.l319.eduo2o.dto.ShopExecution;
import com.l319.eduo2o.enums.ShopStateEnum;
import com.l319.eduo2o.pojo.Area;
import com.l319.eduo2o.pojo.PersonInfo;
import com.l319.eduo2o.pojo.Shop;
import com.l319.eduo2o.pojo.ShopCategory;
import com.l319.eduo2o.service.AreaService;
import com.l319.eduo2o.service.ShopCategoryService;
import com.l319.eduo2o.service.ShopService;
import com.l319.eduo2o.util.CodeUtil;
import com.l319.eduo2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	/**
	 * session 管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId < 0) {// 如果前端没有shopId 查看session
			Object currentShopObject = request.getSession().getAttribute("currentShop");
			if (currentShopObject == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/eduo2o/shopadmin/shoplist");
				System.out.println("-2");
			} else {
				Shop currentShop = (Shop) currentShopObject;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}

	/**
	 * 根据用户信息返回该用户所创建的店铺列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
//		user.setUserId(1L);
//		user.setName("test");
//		request.getSession().setAttribute("user", user);
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		System.out.println(user.toString());
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("shopList", shopExecution.getShopList());
			request.getSession().setAttribute("shopList", shopExecution.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	/**
	 * 根据店铺ID得到店铺信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	/**
	 * 初始化打开店铺注册页面，加载区域类别和店铺类别
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.getShopCategorieList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	/**
	 * 注册店铺
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 接受并转换为相应的参数，包括店铺信息和图片
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper objectMapper = new ObjectMapper();// 通过jackson将其转换为实体类
		Shop shop = null;
		try {
			shop = objectMapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 接受其中的文件流
		CommonsMultipartFile shopImgCommonsMultipartFile = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImgCommonsMultipartFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2注册店铺
		if (shop != null && shopImgCommonsMultipartFile != null) {
//			PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(1L);
			shop.setOwner(personInfo);
			ShopExecution shopExecution;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImgCommonsMultipartFile.getOriginalFilename(),
						shopImgCommonsMultipartFile.getInputStream());
				shopExecution = shopService.addShop(shop, imageHolder);
				if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					// 一个用户可以创建多个店铺,将自己的店铺保存到session里面
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(shopExecution.getShop());
					request.getSession().setAttribute("shopList", shopList);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", shopExecution.getStateInfo());
					return modelMap;
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modify(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 接受并转换为相应的参数，包括店铺信息和图片
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper objectMapper = new ObjectMapper();// 通过jackson将其转换为实体类
		Shop shop = null;
		try {
			shop = objectMapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 接受其中的文件流
		CommonsMultipartFile shopImgCommonsMultipartFile = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImgCommonsMultipartFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		// 2修改店铺
		if (shop != null && shop.getShopId() != null) {
			ShopExecution shopExecution;
			try {
				if (shopImgCommonsMultipartFile == null) {
					shopExecution = shopService.modifyShop(shop, null);
				} else {
					ImageHolder imageHolder = new ImageHolder(shopImgCommonsMultipartFile.getOriginalFilename(),
							shopImgCommonsMultipartFile.getInputStream());
					shopExecution = shopService.modifyShop(shop, imageHolder);
				}
				if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", shopExecution.getStateInfo());
					return modelMap;
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺ID");
			return modelMap;
		}
	}
//	private static void inputStreamToFile(InputStream inputStream, File file) {
//		FileOutputStream fileoutputStream = null;
//		try {
//			fileoutputStream = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[1024];
//			while ((bytesRead = inputStream.read(buffer)) != -1) {
//				fileoutputStream.write(buffer, 0, bytesRead);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
//		} finally {
//			try {
//				if (fileoutputStream != null) {
//					fileoutputStream.close();
//				}
//				if (inputStream != null) {
//					inputStream.close();
//				}
//			} catch (Exception e) {
//				throw new RuntimeException("inputStreamToFile产生异常：" + e.getMessage());
//			}
//		}
//	}
}
