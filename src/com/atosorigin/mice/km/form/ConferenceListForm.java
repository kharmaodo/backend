package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

public class ConferenceListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2199930863966988564L;
	private String name;
	private String from;
	private String to;
	private int currentPage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
