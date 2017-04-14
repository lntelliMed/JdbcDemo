package com.intellimed.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

// This is instead of even setting up the DataSource and JdbcTemplate members done manually in JdbcDaoImpl.
// All can be configured in "spring.xml" now!
public class JdbcDaoSupportImpl extends JdbcDaoSupport {
	
	
	
	public String getCircleCount(){
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		
		//return jdbcTemplate.queryForInt(sql); // A different package than "org.springframework.jdbc.core.JdbcTemplate;"
		return this.getJdbcTemplate().queryForObject(sql, String.class);
	}

}
