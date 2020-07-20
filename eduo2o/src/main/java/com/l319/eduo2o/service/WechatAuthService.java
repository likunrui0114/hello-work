package com.l319.eduo2o.service;

import com.l319.eduo2o.dto.WechatAuthExecution;
import com.l319.eduo2o.exception.WechatAuthOperationException;
import com.l319.eduo2o.pojo.WechatAuth;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
public interface WechatAuthService {
	/**
	 * 通过openId查找平台对应的微信帐号
	 * 
	 * @param openId
	 * @return
	 */
	WechatAuth getWechatAuthByOpenId(String openId);
	/**
	 * 注册本平台的微信帐号
	 * 
	 * @param wechatAuth
	 * @param profileImg
	 * @return
	 * @throws RuntimeException
	 */
	WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
