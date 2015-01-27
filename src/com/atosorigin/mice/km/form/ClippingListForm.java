package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class ClippingListForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3085691579329730764L;
	private String keyword;
	private int currentPage;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
