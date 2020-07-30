package com.example.demo.Mybatis;

public class UserBean {

	String id;
	String account;

	public UserBean(String id, String account) {
		super();
		this.id = id;
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
