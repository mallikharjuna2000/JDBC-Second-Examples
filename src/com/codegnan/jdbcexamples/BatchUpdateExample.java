package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchUpdateExample {
	static final String JDBC_URL = "jdbc:mysql://localhost:3306/adjava";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// establish connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String sql = "update employee set esal=? where eno=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, 72000);
			preparedStatement.setInt(2, 5);
			preparedStatement.addBatch();

			preparedStatement.setDouble(1, 6000);
			preparedStatement.setInt(2, 4);
			preparedStatement.addBatch();

			preparedStatement.setDouble(1, 12000);
			preparedStatement.setInt(2, 6);
			preparedStatement.addBatch();
			int[] updateCounts = preparedStatement.executeBatch();
			System.out.println("batch update is executed ");
			for (int i = 0; i < updateCounts.length; i++) {
				System.out.println("update count for statement " + i + " : " + updateCounts[i]);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}