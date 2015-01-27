package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class ProjectListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3958228126499815693L;
	private String menuId;
	private String locale;
	private int currentPage;
	
	public ProjectListForm() {
		this.currentPage = 1;
	}
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
