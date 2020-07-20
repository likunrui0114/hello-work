package com.l319.eduo2o.util;

import java.security.MessageDigest;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
public class MD5 {
	/**
	 * 对传入的str进行MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static final String getMd5(String str) {
		// 16进制数组
		char hexDigits[] = { '5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y' };
		try {
			char s[];
			byte strTemp[] = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			// 传入需要加密的目标数组
			mdTemp.update(strTemp);
			// 获取加密后的数组
			byte md[] = mdTemp.digest();
			int j = md.length;
			s = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				s[k++] = hexDigits[byte0 >>> 4 & 0xf];
				s[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(s);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5.getMd5("123456"));
	}
}
