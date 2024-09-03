package com.codegnan.jdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertMultipleRecords {
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
			System.out.println("enter the number of records to insert : ");
			int numRecords = scanner.nextInt();
			scanner.nextLine();// consume new line charcter.

			for (int i = 0; i < numRecords; i++) {
				System.out.println(" enter details of record  : " + (i + 1) + ":");
				System.out.println("enter the employee id : ");
				int eno = scanner.nextInt();
				System.out.println("Enter the Employee Name : ");
				String ename = scanner.next();
				System.out.println("enter employee salary : ");
				double esal = scanner.nextDouble();
				System.out.println("Enter employee address : ");
				String eaddr = scanner.next();
				String insertSql = "insert into employee(eno,ename,esal,eaddr)values(" + eno + "," + ename + "," + esal
						+ "," + eaddr + ")";
				int rowsAffected = statement.executeUpdate(insertSql);
				if (rowsAffected == 1) {
					System.out.println("Record" + (i + 1) + " inserted succesfully ");
				} else {
					System.out.println("Failed to insert Record" + (i + 1));
				}
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
