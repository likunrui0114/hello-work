package com.l319.eduo2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.l319.eduo2o.BaseTest;
import com.l319.eduo2o.dto.LocalAuthExecution;
import com.l319.eduo2o.pojo.LocalAuth;
import com.l319.eduo2o.pojo.PersonInfo;

public class LocalAuthServiceTest extends BaseTest {
	@Autowired
	private LocalAuthService localAuthService;

	@Test
	@Ignore
	public void testABindLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		String usernameString = "testusername";
		String passwordString = "testpassword";
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUsername(usernameString);
		localAuth.setPassword(passwordString);
		LocalAuthExecution localAuthExcution = localAuthService.bindLocalAuth(localAuth);
		assertEquals(0, localAuthExcution.getState());
		localAuth = localAuthService.getLocalByUserId(personInfo.getUserId());
		System.out.println("用户名称：" + localAuth.getPersonInfo().getName());
		System.out.println("平台账号密码：" + localAuth.getPassword());
	}

	@Test

	public void testBModefyLocalAuth() {
		long userId = 1;
		String username = "testusername";
		String password = "testpassword";
		String newPassword = "testnewpassword";
		LocalAuthExecution localAuthExcution = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
		assertEquals(0, localAuthExcution.getState());
		LocalAuth localAuth = localAuthService.getLocalByUserNameAndPwd(username, newPassword);
		System.out.println(localAuth.getPersonInfo().getName());
	}
}
