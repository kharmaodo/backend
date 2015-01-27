package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class OverseasEventVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2934394743298223868L;
	private String id;
	private String description;
	private String regionCategoryId;
	private Date startDate;
	private Date endDate;
	private String url;
	private String vendorName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegionCategoryId() {
		return regionCategoryId;
	}
	public void setRegionCategoryId(String regionCategoryId) {
		this.regionCategoryId = regionCategoryId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	

}
