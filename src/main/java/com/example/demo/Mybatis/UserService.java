package com.example.demo.Mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class UserService {

	public static void main(String[] args) {
//		deleteUser();
		insertUser();
//		selectUserById();
//		selectUserByIdForUpdate();
//		selectUserByIdForUpdateTime();
//        selectAllUser();
	}

	/**
	 * 新增用户
	 */
	private static void insertUser() {
		DBTools.checkConnection();
		SqlSession session = DBTools.getSession();
		session.getConfiguration().setDefaultStatementTimeout(3); // 3秒
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserBean user = new UserBean("3", "3");
		Integer i = null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("id","2");
		map.put("account","2");
		try {
			i = mapper.insertUser(user);
//			i = session.update("com.example.demo.Mybatis.UserMapper.insertUser", map);
			System.out.println(user.toString());
			System.err.println(session.toString());
			return;
//			session.commit();
		} catch (Exception e) {
			System.err.println("输出长度" + i);
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * 删除用户
	 */
	private static void deleteUser() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			mapper.deleteUser("2");
//			session.commit();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * 根据id查询用户
	 */
	private static void selectUserByIdForUpdate() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		System.out.println("=============selectUserByIdForUpdate=====================");
		try {
			UserBean user = mapper.selectUserByIdForUpdate("1");
			System.out.println(user.toString());
			return;
//			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		/*
		 * finally { session.close(); }
		 */
	}

	/**
	 * 根据id查询用户
	 */
	private static void selectUserByIdForUpdateTime() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		System.out.println("=============selectUserByIdForUpdateTime=====================");
		try {
			UserBean user = mapper.selectUserByIdForUpdateTime("1");
			System.out.println(user.toString());
			session.commit();
			return;
//			session.rollback();

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		/*
		 * finally { session.close(); }
		 */
	}

	/**
	 * 根据id查询用户
	 */
	private static void selectUserById() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			UserBean user = mapper.selectUserById("1");
			System.out.println(user.toString());

//			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * 查询所有的用户
	 */
	private static void selectAllUser() {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			List<UserBean> user = mapper.selectAllUser();
			System.out.println(user.toString());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}