package com.l319.eduo2o.service;

import com.l319.eduo2o.dto.LocalAuthExecution;
import com.l319.eduo2o.exception.LocalAuthOperationException;
import com.l319.eduo2o.pojo.LocalAuth;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
public interface LocalAuthService {
	/**
	 * 通过账号和密码查询对应信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalByUserNameAndPwd(String username, String password);

	/**
	 * 通过用户Id查询对应的信息
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalByUserId(Long userId);

	/**
	 * 微信绑定
	 * 
	 * @param localAuth
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	/**
	 * 更改密码
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword)
			throws LocalAuthOperationException;
}
