package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class VendorCategoryEditForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -78448901238065389L;
	private String id;
	private String mainId;
	private String subId;
	private int levelIndex;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public int getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
