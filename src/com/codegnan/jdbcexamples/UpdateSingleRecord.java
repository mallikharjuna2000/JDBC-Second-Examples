package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateSingleRecord {

	static final String JDBC_URL = "jdbc:mysql://localhost:3306/Adjava";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		Scanner scanner = new Scanner(System.in);
		try {
			// 2. establish the connection.
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// create statement;
			statement = connection.createStatement();

			// prompt user for number of records to insert.

			System.out.println("enter the employee number to update : ");
			int eno = scanner.nextInt();
			System.out.println("Enter the Employee Name : ");
			String ename = scanner.next();
			System.out.println("enter employee salary : ");
			double esal = scanner.nextDouble();
			System.out.println("Enter employee address : ");
			String eaddr = scanner.next();

			String updateSql = "update employee set ename='" + ename + "',esal=" + esal + ",eaddr='" + eaddr
					+ "'where eno=" + eno;

			int rowsAffected = statement.executeUpdate(updateSql);
			if (rowsAffected > 0) {
				System.out.println("Data Updated Successfully for employee number : " + eno);

			} else {
				System.out.println("Record not found for employee number " + eno + ". update operation failure");
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
