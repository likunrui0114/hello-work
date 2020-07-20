package com.l319.eduo2o.service;
/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */

import java.io.IOException;
import java.util.List;

import com.l319.eduo2o.pojo.HeadLine;

public interface HeadLineService {
	public static final String HLLISTKEY = "headlinelist";

	/**
	 * 根据条件查询头条
	 * 
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
