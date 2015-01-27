package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class AttachmentVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6126805491641077323L;
	private String id;
	private String title;
	private String originName;
	private String type;
	private String path;
	private String pressReleaseId;
	private int categoryGroup;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPressReleaseId() {
		return pressReleaseId;
	}
	public void setPressReleaseId(String pressReleaseId) {
		this.pressReleaseId = pressReleaseId;
	}
	public int getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(int categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	
	
	
}
