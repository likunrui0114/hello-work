package edu.lkr.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.lkr.pojo.User;
import edu.lkr.service.UserService;

public class SpringJDBCTest {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		User user = new User();
		user.setName("zs");
		user.setAge(20);
		user.setSex("男");
		userService.save(user);

		List<User> users = userService.getUsers();
		System.out.println("所有信息");
		for (User person : users) {
			System.out.println(person.getId() + " " + person.getName());
		}
	}
}
