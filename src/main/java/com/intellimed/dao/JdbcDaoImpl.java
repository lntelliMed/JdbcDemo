package com.intellimed.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.intellimed.model.Circle;

@Component
public class JdbcDaoImpl {
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Deprecated
	public Circle getCircle(int circleId) {

		
		Connection conn = null;

		try {
			
			//Replacing the below traditional connection setup code with Spring dataSource from spring.xml!
/*			String driver = "org.postgresql.Driver";
			Class.forName(driver).newInstance();
			
			// DriverManager.registerDriver(new org.postgresql.Driver());
			String dbURL = "jdbc:postgresql://localhost/postgres";
			String user = "postgres";
			String pass = "1974";
			conn = DriverManager.getConnection(dbURL, user, pass);*/
			
			conn = dataSource.getConnection();
			
			

			
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
			ps.setInt(1, circleId);

			Circle circle = null;
			
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}
			rs.close();

			ps.close();
			return circle;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}
	
	
	public String getCircleCount(){
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		
		//return jdbcTemplate.queryForInt(sql); // A different package than "org.springframework.jdbc.core.JdbcTemplate;"
		return jdbcTemplate.queryForObject(sql, String.class);
	}
	
	public String getCircleName(int circleId){
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId}, String.class);
	}
	
	public Circle getCircleforId(int circleId){
		String sql = "SELECT * FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles(){
		String sql = "SELECT * FROM CIRCLE";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{

		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
		}

	
		
		
	}

} 
