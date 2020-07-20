package edu.lkr.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.lkr.mapper.UserMapper;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		User people = ac.getBean("peo",User.class);
//		System.out.println(people);
		UserMapper userDao = ac.getBean("userMapper", UserMapper.class);
		System.out.println(userDao.getUser(4));
	}

}
