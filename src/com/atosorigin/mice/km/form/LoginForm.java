package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class LoginForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1597837463528833319L;
	private String account;
	private String password;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
