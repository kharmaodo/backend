package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class RegionCategoryVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1729374838381029534L;
	private String id;
	private String description;
	private int level;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
