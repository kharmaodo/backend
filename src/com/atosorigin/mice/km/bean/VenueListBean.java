package com.atosorigin.mice.km.bean;

import java.io.Serializable;

import com.atosorigin.mice.km.vo.VenueVO;

public class VenueListBean extends VenueVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -744124952727807925L;
	private String venueCategoryName;
	private String regionName;
	private String countyName;
	public String getVenueCategoryName() {
		return venueCategoryName;
	}
	public void setVenueCategoryName(String venueCategoryName) {
		this.venueCategoryName = venueCategoryName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	

}
