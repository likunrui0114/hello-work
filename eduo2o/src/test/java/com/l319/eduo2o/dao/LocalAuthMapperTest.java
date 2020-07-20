package com.l319.eduo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.mapper.LocalAuthMapper;
import com.l319.eduo2o.pojo.LocalAuth;
import com.l319.eduo2o.pojo.PersonInfo;

public class LocalAuthMapperTest extends BaseTest {
	@Autowired
	private LocalAuthMapper localAuthMapper;

	private static final String username = "testusername";
	private static final String password = "testpassword";

	@Test
	@Ignore
	public void testAInsertLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		int effectedNum = localAuthMapper.insertLocalAuth(localAuth);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testBQueryLocalByUserAndPwd() throws Exception {
		LocalAuth localAuth = localAuthMapper.queryLocalByUserNameAndPwd(username, password);
		assertEquals("李坤睿", localAuth.getPersonInfo().getName());
	}

	@Test
	@Ignore
	public void testCQueryLcoalAuthByUserId() throws Exception {
		LocalAuth localAuth = localAuthMapper.queryLocalByUserId(2L);
		assertEquals("李坤睿1", localAuth.getPersonInfo().getName());
	}

	@Test
	public void testDUpdateLocalAuth() throws Exception {
		Date date = new Date();
		int effectedNum = localAuthMapper.updateLocalAuth(1L, username, password, "new" + password, date);
		assertEquals(1, effectedNum);
	}
}
