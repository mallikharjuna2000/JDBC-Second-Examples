package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateMultipleRecords {

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

			/*
			 * System.out.println("enter employee number to delete : "); int enoToDelete =
			 * scanner.nextInt();
			 * 
			 * String deleteSQL = "delete from employee where eno=" + enoToDelete; int
			 * rowsAffected = statement.executeUpdate(deleteSQL); if (rowsAffected > 0) {
			 * System.out.println("record with employee number : " + enoToDelete +
			 * " Deleted Succesfully "); } else {
			 * System.out.println("No Record with Employee Number : " + enoToDelete); }
			 */

			System.out.println("Enter the salary thresshold below which records will be deleted ");
			double esal = scanner.nextDouble();

			String deleteSQL = "delete from employee where esal>=" + esal;
			int rowsAffected = statement.executeUpdate(deleteSQL);
			System.out.println(rowsAffected + " records deleted Succesfully ");
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
