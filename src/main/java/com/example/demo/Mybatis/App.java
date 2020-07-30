package com.example.demo.Mybatis;

import java.sql.Connection;
import java.sql.SQLException;

//import oracle.jdbc.pool.*;

public class App {

	static String db_url = "";
	static String db_user = "";
	static String db_pass = "";

	public static void main(String[] args) throws SQLException {
		/*
		OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
		ocpds.setURL(App.db_url); //JDBC URL for Oracle
		ocpds.setUser(App.db_user); //用户名
		ocpds.setPassword(App.db_pass);//口令
		OracleConnectionCacheImpl connPool = new OracleConnectionCacheImpl(ocpds); // Set the maximum number of connections
//		connPool.setMinLimit(this.min); connPool.setMaxLimit(this.max); // set the scheme
//		connPool.setCacheScheme(OracleConnectionCacheImpl.DYNAMIC_SCHEME);
		Connection conn = connPool.getConnection();
		*/
	}

	static class test{
		
		class test0{

			class inner{
				public boolean a = false;
			}
		}
		
		public static void main(String[] args) {
			
		}
	}
}
