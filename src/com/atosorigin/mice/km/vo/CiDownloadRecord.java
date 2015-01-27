package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class CiDownloadRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1454838443458167051L;
	private String id;
	private String applicationId;
	private String ipAddress;
	private int ciType;  // 1=BOFT 2=MEETTAIWAN
	private Date downloadDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getCiType() {
		return ciType;
	}
	public void setCiType(int ciType) {
		this.ciType = ciType;
	}
	public Date getDownloadDate() {
		return downloadDate;
	}
	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}
	
}
