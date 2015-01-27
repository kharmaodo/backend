package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class ProjectDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7822176490734830134L;
	public int id;
	public String localizedDataId;
	public String description;
	public String content;
	public String projectId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocalizedDataId() {
		return localizedDataId;
	}
	public void setLocalizedDataId(String localizedDataId) {
		this.localizedDataId = localizedDataId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
