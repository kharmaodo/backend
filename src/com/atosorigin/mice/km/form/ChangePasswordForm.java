package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class ChangePasswordForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -735235105345195584L;
	private int id;
	private String account;
	private String origPassword;
	private String newPassword;
	private String rePassword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOrigPassword() {
		return origPassword;
	}
	public void setOrigPassword(String origPassword) {
		this.origPassword = origPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	

}
