package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertRecords {
	static final String JDBC_URL = "jdbc:mysql://localhost:3306/adjava";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	static final String driver = "com.mysql.jdbc.Driver";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		Scanner scanner = new Scanner(System.in);
		try {
			// 1. load and register the drive
			// Class.forName(driver);

			// 2. establish the connection
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// 3. CREATE THE STATEMENT
			statement = connection.createStatement();
			System.out.println("Eneter employee number : ");
			int eno = scanner.nextInt();
			System.out.println("enter the Employee Name : ");
			String ename = scanner.next();
			System.out.println("Employee salary : ");
			double esal = scanner.nextDouble();
			System.out.println("enter employee address : ");
			String eaddr = scanner.next();
			// prepare sql query
			String insertQuery = "INSERT INTO EMPLOYEE(ENO,ENAME,ESAL,EADDR) VALUES(" + eno + "," + ename + "," + esal
					+ "," + eaddr + ")";
			int rowsAffected = statement.executeUpdate(insertQuery);
			if (rowsAffected == 1) {
				System.out.println("records inserted succesfully");
			} else {
				System.out.println("record insertyion failure");
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
