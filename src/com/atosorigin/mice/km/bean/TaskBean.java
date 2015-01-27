package com.atosorigin.mice.km.bean;

import java.io.Serializable;

public class TaskBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5486906150855058199L;
	private String url;
	private String type;
	private int num;
	private String description;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
