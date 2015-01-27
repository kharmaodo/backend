package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class VideoListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6614492399883579210L;
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
