package com.intellimed.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellimed.model.Circle;

@Component
public class JdbcDaoImpl {
	@Autowired
	private DataSource dataSource;
	

	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


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

}
