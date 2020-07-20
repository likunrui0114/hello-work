package com.l319.eduo2o.mapper;
/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.l319.eduo2o.pojo.HeadLine;

public interface HeadLineMapper {
	/**
	 * 查询头条
	 * 
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLineList(@Param("headLineCondition") HeadLine headLineCondition);
}
