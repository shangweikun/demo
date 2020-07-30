package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.oracle.jdbc.Driver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	// Database credentials
	static final String USER = "SYSTEM";
	static final String PASS = "159951";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 3: Open a connection
			System.out.println("main Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);

			// STEP 4: Execute a query
			System.out.println("main Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "insert into T_USER VALUES (3,3)";
			System.out.println("main begin statement...");

			stmt.executeQuery(sql);
			Thread t = new Thread(() -> {
				try {
					Connection conn0 = null;
					Statement stmt0 = null;
					// STEP 3: Open a connection
					System.out.println("t Connecting to database...");
					conn0 = DriverManager.getConnection(DB_URL, USER, PASS);
					conn0.setAutoCommit(false);
					;

					// STEP 4: Execute a query
					System.out.println("t Creating statement...");
					stmt0 = conn0.createStatement();
					stmt0.setQueryTimeout(1);/* 未重现 user cancel */

					stmt0.execute(sql);
					conn0.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			while (true) {
//				t.start();
				System.out.println("main Begin Sleep...");
				Thread.sleep(100000l);
				break;
			}
			conn.commit();
			System.out.println("main Begin End...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
