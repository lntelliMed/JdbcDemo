package com.intellimed;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intellimed.dao.JdbcDaoImpl;
import com.intellimed.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		//Circle circle = new JdbcDaoImpl().getCircle(1);//Replacing with Spring framework support!
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		
		JdbcDaoImpl jdbcDaoImpl = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		Circle circle = jdbcDaoImpl.getCircle(1);
		
		System.out.println(circle.getName());
	}

}
