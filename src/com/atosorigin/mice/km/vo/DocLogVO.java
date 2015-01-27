package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class DocLogVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2100858913694550687L;
	private int id;
	private String fromIp;
	private String account;
	private String what;
	private Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromIp() {
		return fromIp;
	}
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id = " + this.id + "\n");
		sb.append("formIp = " + this.fromIp + "\n");
		sb.append("account = " + this.account + "\n");
		sb.append("what = " + this.what + "\n");
		sb.append("createTime = " + this.createTime.toString() + "\n");
		return sb.toString();
	}

}
