package com.atosorigin.mice.km.xml.bean;

import java.io.Serializable;

public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 215993155453282843L;
	private String lat;
	private String lng;
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
}
