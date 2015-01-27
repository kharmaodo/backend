package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

public class CaseStudyListForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8862176799259014496L;
	private String description;
	private String caseStudyCategoryId;
	private int currentPage;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCaseStudyCategoryId() {
		return caseStudyCategoryId;
	}
	public void setCaseStudyCategoryId(String caseStudyCategoryId) {
		this.caseStudyCategoryId = caseStudyCategoryId;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	

}
