package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class VenueListForm implements Serializable {
	private static final long serialVersionUID = -6948712123186894962L;
	private String description;
	private String venueCategoryId;
	private String region;
	private int currentPage;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenueCategoryId() {
		return venueCategoryId;
	}
	public void setVenueCategoryId(String venueCategoryId) {
		this.venueCategoryId = venueCategoryId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	
	

}
