package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertRecordsByPreparedStatement {
	static final String JDBC_URL = "jdbc:mysql://localhost:3306/adjava";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	public static void main(String[] args) {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		Scanner scanner;
		try {
			// establish the connection.
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// Prepare sql query with parameters or without parameters.
			String insertQuery = "insert into employee (eno,ename,esal,eaddr) values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertQuery);
			scanner = new Scanner(System.in);
			System.out.println("Enetr the Number of Records to Insert : ");
			int numOfRecords = scanner.nextInt();

			for (int i = 0; i < numOfRecords; i++) {
				System.out.println("Enter details for record " + (i + 1) + " : ");
				System.out.println("Enter Employee Number : ");
				int eno = scanner.nextInt();
				System.out.println("Enter the Employee Name : ");
				String ename = scanner.next();
				System.out.println("Enter the employee salary : ");
				double esal = scanner.nextDouble();
				System.out.println("Enter employee address : ");
				String eaddr = scanner.next();

				preparedStatement.setInt(1, eno);
				preparedStatement.setString(2, ename);
				preparedStatement.setDouble(3, esal);
				preparedStatement.setString(4, eaddr);

				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println(rowsAffected + "row(s) Inserted succesfully");
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
