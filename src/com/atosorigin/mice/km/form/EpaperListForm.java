package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class EpaperListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6696815342111640752L;
	private String description;
	private String from;
	private String to;
	private int currentPage;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
