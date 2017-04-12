package com.intellimed;

import com.intellimed.dao.JdbcDaoImpl;
import com.intellimed.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
	}

}
