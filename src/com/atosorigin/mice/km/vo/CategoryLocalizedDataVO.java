package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class CategoryLocalizedDataVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7325292312228496156L;
	private String categoryId;
	private String localizedDataId;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getLocalizedDataId() {
		return localizedDataId;
	}
	public void setLocalizedDataId(String localizedDataId) {
		this.localizedDataId = localizedDataId;
	}
	
	

}
