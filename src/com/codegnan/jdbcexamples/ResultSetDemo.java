package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {
	static final String JDBC_URL = "jdbc:mysql://localhost:3306/adjava";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// establish connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			// CREATE THE STATEMENT Object
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// prepare SQLQuery.
			String sqlQuery = "select*from employee";

			// Process results from resultSet
			resultSet = statement.executeQuery(sqlQuery);
			System.out.println("Records in Forward Direction : ");
			System.out.println();
			System.out.println("ENO\tENAME\teSAL\tEaddr");
			System.out.println("=========================================================");
			while (resultSet.next()) {
				System.out.println(resultSet.getRow() + "------------>" + resultSet.getInt(1) + "\t"
						+ resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getString(4));
			}

			System.out.println("Records in Backward Direction : ");
			System.out.println();
			System.out.println("ENO\tENAME\teSAL\tEaddr");
			System.out.println("=========================================================");
			while (resultSet.previous()) {
				System.out.println(resultSet.getRow() + "------------>" + resultSet.getInt(1) + "\t"
						+ resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getString(4));
			}
			resultSet.first();
			System.out.println(resultSet.getRow() + "------------>" + resultSet.getInt(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getString(4));

			resultSet.last();
			System.out.println(resultSet.getRow() + "------------>" + resultSet.getInt(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getString(4));
			resultSet.relative(-4);
			System.out.println(resultSet.getRow() + "------------>" + resultSet.getInt(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getString(4));

			resultSet.absolute(2);
			System.out.println(resultSet.getRow() + "------------>" + resultSet.getInt(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getDouble(3) + "\t" + resultSet.getString(4));

			System.out.println(resultSet.isFirst());
			System.out.println(resultSet.isLast());

			System.out.println(resultSet.isBeforeFirst());
			System.out.println(resultSet.isAfterLast());
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
