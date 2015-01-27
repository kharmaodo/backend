package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.List;

public class DocumentCategoryVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5143500502366676119L;
	private String id;
	private String description;
	private int levelIndex;
	private int categoryGroupId;
	private int rank;
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
	public int getCategoryGroupId() {
		return categoryGroupId;
	}
	public void setCategoryGroup(int categoryGroup) {
		this.categoryGroupId = categoryGroup;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
}
