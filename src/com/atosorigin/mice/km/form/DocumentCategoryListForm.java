package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class DocumentCategoryListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6639762391806247426L;
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
