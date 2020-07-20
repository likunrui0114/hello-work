package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.HeadLineMapper;
import com.l319.eduo2o.pojo.HeadLine;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
public class HeadLineTest extends BaseTest{
	@Autowired
	private HeadLineMapper headLineMapper;
	
	@Test
	public void testQuery() {
		List<HeadLine> headLineList = headLineMapper.queryHeadLineList(new HeadLine());
		assertEquals(0, headLineList.size());
	}
}
