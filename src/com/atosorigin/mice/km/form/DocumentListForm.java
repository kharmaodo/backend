package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class DocumentListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -942782145532971191L;
	private String description;
	private int currentPage;
	private String approvalStatus;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	

}
