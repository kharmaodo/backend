package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class VendorListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2188119289790420212L;
	private String account;
	private String name;
	private String groupId;
	private int currentPage;
	
	public VendorListForm() {
		this.currentPage = 1;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	

}
