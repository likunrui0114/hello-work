package com.l319.eduo2o.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.l319.eduo2o.pojo.LocalAuth;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
public interface LocalAuthMapper {
	/**
	 * 通过账号和密码查询对应信息
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

	/**
	 * 通过用户Id查询对应的信息
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") Long userId);

	/**
	 * 添加账号
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 更改密码
	 * 
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
