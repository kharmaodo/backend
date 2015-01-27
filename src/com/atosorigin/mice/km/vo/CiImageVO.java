package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class CiImageVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1908457899323469982L;
	private String id;
	private String serialNumber;
	private String fileName;
	private String downloadString;
	private Date createTime;
	private Date expiredTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownloadString() {
		return downloadString;
	}
	public void setDownloadString(String downloadString) {
		this.downloadString = downloadString;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	
}
