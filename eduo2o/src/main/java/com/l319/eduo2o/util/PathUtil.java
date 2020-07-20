package com.l319.eduo2o.util;

public class PathUtil {
	/**
	 * 获取系统目录分隔符号
	 */
	private static String separator = System.getProperty("file.separator");

	/**
	 * 根据系统运行环境获取店铺图片存储的根路径
	 * 
	 * @return 存储店铺图片的根目录
	 */
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "E:/bigo2o/image";
		} else {
			basePath = "/Users/baidu/work/image";
		}
		basePath = basePath.replace("/", separator);
		return basePath;
	}

	/**
	 * 根据店铺的编号生成存储图片的路径
	 * 
	 * @param shopId 店铺的编号
	 * @return 运行系统下的店铺图片子路径
	 */
	public static String getShopImagePath(Long shopId) {
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", separator);
	}
}
