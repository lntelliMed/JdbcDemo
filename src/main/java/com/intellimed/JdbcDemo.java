package com.intellimed;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intellimed.dao.HibernateDaoImpl;
import com.intellimed.dao.JdbcDaoImpl;
import com.intellimed.dao.JdbcDaoSupportImpl;
import com.intellimed.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		//Circle circle = new JdbcDaoImpl().getCircle(1);//Replacing with Spring framework support!
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
/*		
		JdbcDaoImpl jdbcDaoImpl = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		//Circle circle = jdbcDaoImpl.getCircle(1);
		//System.out.println(circle.getName());
		
		//Replacing the regular JDBC calls with JdbcTemplate
		System.out.println(jdbcDaoImpl.getCircleCount());
		System.out.println(jdbcDaoImpl.getCircleName(1));
		
		System.out.println(jdbcDaoImpl.getCircleforId(1).getName());
		
		//jdbcDaoImpl.createTriangleTable();

		//jdbcDaoImpl.insertCircle(new Circle(2, "Second Circle"));
		//jdbcDaoImpl.insertCircle(new Circle(3, "Third Circle"));
		
		//jdbcDaoImpl.insertCircle(new Circle(4, "Fourth Circle"));
		//System.out.println(jdbcDaoImpl.getAllCircles().size());
*/	

		

		/*			
		JdbcDaoSupportImpl jdbcDaoSupportImpl = context.getBean("jdbcDaoSupportImpl", JdbcDaoSupportImpl.class);
		System.out.println(jdbcDaoSupportImpl.getCircleCount());
*/
		
		
		// Use Hibernate instead now!
		HibernateDaoImpl hibernateDaoImpl = context.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		System.out.println(hibernateDaoImpl.getCircleCount());
	}

}
