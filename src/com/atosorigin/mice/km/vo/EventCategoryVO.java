package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class EventCategoryVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5987305496307243480L;
	private String id;
	private int rank;
	private String calendarId;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
