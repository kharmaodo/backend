package com.atosorigin.mice.km.bean;

import java.io.Serializable;

public class ProjectTaskBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4929877151911844207L;
	private String description;
	private String content;
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
	
	
}
