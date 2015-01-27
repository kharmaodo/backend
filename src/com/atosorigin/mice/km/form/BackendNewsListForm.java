package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class BackendNewsListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6543862179272060366L;
	/**
	 * 
	 */
	private int currentPage = 1;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
