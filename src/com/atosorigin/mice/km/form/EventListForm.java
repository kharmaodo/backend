package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class EventListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6917853406116660607L;
	private String description;
	private String eventCategoryId;
	private String regionTaiwanId;
	private int currentPage;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEventCategoryId() {
		return eventCategoryId;
	}
	public void setEventCategoryId(String eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}
	public String getRegionTaiwanId() {
		return regionTaiwanId;
	}
	public void setRegionTaiwanId(String regionTaiwanId) {
		this.regionTaiwanId = regionTaiwanId;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
