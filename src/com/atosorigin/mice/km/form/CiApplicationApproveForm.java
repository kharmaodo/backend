package com.atosorigin.mice.km.form;

import java.io.Serializable;

public class CiApplicationApproveForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -181159348523059227L;
	private String[] userId;
	private String conferenceId;
	private int currentPage = 1;

	public String[] getUserId() {
		return userId;
	}

	public void setUserId(String[] userId) {
		this.userId = userId;
	}

	public String getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(String conferenceId) {
		this.conferenceId = conferenceId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	

}
