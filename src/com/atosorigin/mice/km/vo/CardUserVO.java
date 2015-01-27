package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class CardUserVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6198940378170975282L;
	private String id;
	private String name;
	private String nationality;
	private int conferenceId;
	private Date stayFrom;
	private Date stayTo;
	private String organization;
	private String email;
	private Date createDate;
	private String status;
	private String verifier;
	private Date verifiedDate;
	private int stayDays;
	private String locale;
	private String gender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getConferenceId() {
		return conferenceId;
	}
	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}
	public Date getStayFrom() {
		return stayFrom;
	}
	public void setStayFrom(Date stayFrom) {
		this.stayFrom = stayFrom;
	}
	public Date getStayTo() {
		return stayTo;
	}
	public void setStayTo(Date stayTo) {
		this.stayTo = stayTo;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public Date getVerifiedDate() {
		return verifiedDate;
	}
	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}
	public int getStayDays() {
		return stayDays;
	}
	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
