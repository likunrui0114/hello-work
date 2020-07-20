package edu.lkr.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.lkr.pojo.People;

public class Test {
	public static void main(String[] args) {
//		People peo = new People();
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		People people = ac.getBean("peo", People.class);
		System.out.println(people);
	}

}
