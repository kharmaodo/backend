package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class MappApplicationListForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7949834576307353328L;
	private String campaign;
	private String applyOrganization;
	private String status;
	private int currentPage;
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getApplyOrganization() {
		return applyOrganization;
	}
	public void setApplyOrganization(String applyOrganization) {
		this.applyOrganization = applyOrganization;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	

}
