package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class OverseasEventDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2151981322839350320L;
	private String id;
	private String eventName;
	private String hostCity;
	private String overseasEventId;
	private String locale;
	private String vendorName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getHostCity() {
		return hostCity;
	}
	public void setHostCity(String hostCity) {
		this.hostCity = hostCity;
	}
	public String getOverseasEventId() {
		return overseasEventId;
	}
	public void setOverseasEventId(String overseasEventId) {
		this.overseasEventId = overseasEventId;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
}
