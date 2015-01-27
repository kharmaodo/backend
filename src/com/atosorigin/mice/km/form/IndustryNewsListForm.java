package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class IndustryNewsListForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5620841553105934277L;
	private String description;
	private int currentPage;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
