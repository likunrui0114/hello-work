package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.AreaMapper;
import com.l319.eduo2o.pojo.Area;

/**
 * @author Administrator
 *
 */
public class AreaMapperTest extends BaseTest{
	@Autowired
	private AreaMapper areaMapper;
	
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaMapper.queryArea();
		assertEquals(4, areaList.size());
	}
	
}
