package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class VendorCategoryVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9127459927094101100L;
	private String id;
	private String description;
	private int levelIndex;
	private String parentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
