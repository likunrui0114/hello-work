package com.l319.eduo2o.service;

import java.util.List;

import com.l319.eduo2o.pojo.Area;

public interface AreaService {
	public static final String AREALISTKEY = "arealist";

	/**
	 * 获取区域列表
	 * 
	 * @return
	 */
	List<Area> getAreaList();
}
