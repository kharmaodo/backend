package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class CaseStudyVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3011814468881089372L;
	private String id;
	private String description;
	private String photo;
	private int rank;
	private Date startDate;
	private Date endDate;
	private String caseStudyCategoryId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCaseStudyCategoryId() {
		return caseStudyCategoryId;
	}
	public void setCaseStudyCategoryId(String caseStudyCategoryId) {
		this.caseStudyCategoryId = caseStudyCategoryId;
	}
	
}
