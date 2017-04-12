package com.intellimed.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intellimed.model.Circle;

public class JdbcDaoImpl {

	public Circle getCircle(int circleId) {

		Connection conn = null;

		try {
			String driver = "org.postgresql.Driver";
			Class.forName(driver).newInstance();
			// DriverManager.registerDriver(new org.postgresql.Driver());

			String dbURL = "jdbc:postgresql://localhost/postgres";
			String user = "postgres";
			String pass = "1974";
			conn = DriverManager.getConnection(dbURL, user, pass);

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
