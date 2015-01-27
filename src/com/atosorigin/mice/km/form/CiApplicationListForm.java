package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class CiApplicationListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5423137668040812143L;
	private String applyOrg;
	private int approvalStatus;
	private int currentPage;
	private String from;
	private String to;
	
	public String getApplyOrg() {
		return applyOrg;
	}
	public void setApplyOrg(String applyOrg) {
		this.applyOrg = applyOrg;
	}
	public int getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	

}
