package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

public class VideoEditForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8678920622685542220L;
	//VideoVO
	private String id;
	private String youtubeId;
	private String title;
	private String description;
	private String keywords;
	private String verified;
	private Date uploadDate;
	
	// VideoDetailVO
	private String nameEN;
	private String descriptionEN;
	private String nameJP;
	private String descriptionJP;
	private String nameTW;
	private String descriptionTW;
	private String nameCN;
	private String descriptionCN;
	
	public String getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNameEN() {
		return nameEN;
	}
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	public String getDescriptionEN() {
		return descriptionEN;
	}
	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
	}
	public String getNameJP() {
		return nameJP;
	}
	public void setNameJP(String nameJP) {
		this.nameJP = nameJP;
	}
	public String getDescriptionJP() {
		return descriptionJP;
	}
	public void setDescriptionJP(String descriptionJP) {
		this.descriptionJP = descriptionJP;
	}
	public String getNameTW() {
		return nameTW;
	}
	public void setNameTW(String nameTW) {
		this.nameTW = nameTW;
	}
	public String getDescriptionTW() {
		return descriptionTW;
	}
	public void setDescriptionTW(String descriptionTW) {
		this.descriptionTW = descriptionTW;
	}
	public String getNameCN() {
		return nameCN;
	}
	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
	public String getDescriptionCN() {
		return descriptionCN;
	}
	public void setDescriptionCN(String descriptionCN) {
		this.descriptionCN = descriptionCN;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	
}
