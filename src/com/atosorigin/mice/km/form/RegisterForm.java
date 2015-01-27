package com.atosorigin.mice.km.form;

import java.io.Serializable;

import com.atosorigin.mice.km.vo.DocMembersVO;

public class RegisterForm extends DocMembersVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819339097989117888L;
	private String rePassword;
	private int currentPage = 1;
	
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
