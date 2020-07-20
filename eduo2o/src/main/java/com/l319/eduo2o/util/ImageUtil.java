package com.l319.eduo2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.l319.eduo2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 图片工具类
 * 
 * @author likunrui
 * @version 1.0
 */
public class ImageUtil {
	/**
	 * 当前运行环境路径
	 */
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	/**
	 * 格式化时间格式
	 */
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 定义一个随机数生成类
	 */
	private static final Random random = new Random();
	/**
	 * 定义日志类
	 */
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 将CommonsMultipartFile转为File
	 * 
	 * @param cFile 文件流
	 * @return 返回文件对象
	 */
	public static File transferCommonsMultipartFiletoFile(CommonsMultipartFile cFile) {
		File file = new File(cFile.getOriginalFilename());
		System.out.println("22");
		try {
			cFile.transferTo(file);// 文件流写入文件
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 处理商品的详情图片
	 * 
	 * @param thumbnail
	 * @param target
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail, String target) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(target);
		String relativeAddr = target + realFileName + extension;
		logger.debug("current relativeAddr" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 处理缩略图，为用户上传的图片进行标记
	 * 
	 * @param file   接受的文件流
	 * @param target 文件保存的路径
	 * @return 返回新生成的图片的相对路径
	 */
	public static String generateThumbnail(ImageHolder thumbnail, String target) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(target);
		String relativeAddr = target + realFileName + extension;
		logger.debug("current relativeAddr" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		// 绝对路径
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 创建目录
	 * 
	 * @param target 图片存储的文件路径
	 */
	private static void makeDirPath(String target) {
		String realFileParentPath = PathUtil.getImgBasePath() + target;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取上传图片的扩展名
	 * 
	 * @param file 图片文件
	 * @return 图片的扩展名
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机图片名
	 * 
	 * @return 图片名称
	 */
	public static String getRandomFileName() {
		// 生成随机的五位数
		int rannum = random.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}

	public static void main(String[] args) throws IOException {
//		Thumbnails.of(new File("F:/Picture/xiaolongren.png")).size(200, 200)
//				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
//				.outputQuality(0.8f).toFile("F:/Picture/xiaolongrennew.png");
	}

	/**
	 * 删除文件或者删除目录下的所有文件
	 * 
	 * @param storePath 文件的路径或者目录的路径
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
