package com.example.demo.Mybatis;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBTools {
	public static SqlSessionFactory sessionFactory;
	public static Connection con = null;
	public static Properties props = new Properties();
	static {
		try {
			// 使用MyBatis提供的Resources类加载mybatis的配置文件
			Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
			// 构建sqlSession的工厂
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		con = null;
		props.setProperty("user", "SYSTEM");
		props.setProperty("password", "159951");

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", props);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 创建能执行映射文件中sql的sqlSession
	public static SqlSession getSession() {

		// 单链接-事物重入
		try {
			if (con.isClosed() || con == null) {
//				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", props);
			}
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 需要在Configuration增加Mapper，否则会报错
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new testConfiguration()); 
		
		return sessionFactory.openSession(con);
//		return factory.openSession(con);
//		return sessionFactory.openSession(con);
	}
	
	public static void checkConnection() {
		try {
			Statement st= con.createStatement();
			st.execute("select 1 from dual");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static class testConfiguration extends Configuration {
		@Override
		public Integer getDefaultStatementTimeout() {
		    return 3;//秒
		  }
	}

}
