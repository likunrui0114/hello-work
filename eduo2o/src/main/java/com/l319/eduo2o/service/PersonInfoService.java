package com.l319.eduo2o.service;

import com.l319.eduo2o.pojo.PersonInfo;

public interface PersonInfoService {
	/**
	 * 根据用户Id获取personInfo信息
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
}
