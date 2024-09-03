package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectParticularColumns {

	static final String JDBC_URL = "jdbc:mysql://localhost:3306/adjava";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// establish connection.
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// create statement object.
			statement = connection.createStatement();

			// prepare sql query
			String selectSQL = "select eno,ename from employees";

			// execute the sql query

			resultSet = statement.executeQuery(selectSQL);
			System.out.println("ENO\tENAME");
			System.out.println("-------------------------------------------------------");
			// process the resultSet.
			while (resultSet.next()) {

				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
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
