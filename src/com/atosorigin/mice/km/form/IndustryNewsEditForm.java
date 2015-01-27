package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class IndustryNewsEditForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555972805671429433L;
	//IndustryNewsVO
	private String industryNewsId;
	private String source;
	private String description;
	private String url;
	private String industryNewsCategoryId;
	private String publishDate;
	private String shelveDate;
	private String unshelveDate;
	private String showPlace;
	private MultipartFile photo;
	private String contact;
	private String contactEmail;
	private String contactTel;
	private String photoString;

	//IndustryNewsDetailVO
	private String industryNewsDetailIdEN;
	private String visibleEN;
	private String topicEN;
	private String contentEN;
	
	private String industryNewsDetailIdTW;
	private String visibleTW;
	private String topicTW;
	private String contentTW;
	
	private String industryNewsDetailIdCN;
	private String visibleCN;
	private String topicCN;
	private String contentCN;
	
	private String industryNewsDetailIdJP;
	private String visibleJP;
	private String topicJP;
	private String contentJP;
	public String getIndustryNewsId() {
		return industryNewsId;
	}
	public void setIndustryNewsId(String industryNewsId) {
		this.industryNewsId = industryNewsId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIndustryNewsCategoryId() {
		return industryNewsCategoryId;
	}
	public void setIndustryNewsCategoryId(String industryNewsCategoryId) {
		this.industryNewsCategoryId = industryNewsCategoryId;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getShelveDate() {
		return shelveDate;
	}
	public void setShelveDate(String shelveDate) {
		this.shelveDate = shelveDate;
	}
	public String getUnshelveDate() {
		return unshelveDate;
	}
	public void setUnshelveDate(String unshelveDate) {
		this.unshelveDate = unshelveDate;
	}
	public String getShowPlace() {
		return showPlace;
	}
	public void setShowPlace(String showPlace) {
		this.showPlace = showPlace;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public String getIndustryNewsDetailIdEN() {
		return industryNewsDetailIdEN;
	}
	public void setIndustryNewsDetailIdEN(String industryNewsDetailIdEN) {
		this.industryNewsDetailIdEN = industryNewsDetailIdEN;
	}
	public String getVisibleEN() {
		return visibleEN;
	}
	public void setVisibleEN(String visibleEN) {
		this.visibleEN = visibleEN;
	}
	public String getTopicEN() {
		return topicEN;
	}
	public void setTopicEN(String topicEN) {
		this.topicEN = topicEN;
	}
	public String getContentEN() {
		return contentEN;
	}
	public void setContentEN(String contentEN) {
		this.contentEN = contentEN;
	}
	public String getIndustryNewsDetailIdTW() {
		return industryNewsDetailIdTW;
	}
	public void setIndustryNewsDetailIdTW(String industryNewsDetailIdTW) {
		this.industryNewsDetailIdTW = industryNewsDetailIdTW;
	}
	public String getVisibleTW() {
		return visibleTW;
	}
	public void setVisibleTW(String visibleTW) {
		this.visibleTW = visibleTW;
	}
	public String getTopicTW() {
		return topicTW;
	}
	public void setTopicTW(String topicTW) {
		this.topicTW = topicTW;
	}
	public String getContentTW() {
		return contentTW;
	}
	public void setContentTW(String contentTW) {
		this.contentTW = contentTW;
	}
	public String getIndustryNewsDetailIdCN() {
		return industryNewsDetailIdCN;
	}
	public void setIndustryNewsDetailIdCN(String industryNewsDetailIdCN) {
		this.industryNewsDetailIdCN = industryNewsDetailIdCN;
	}
	public String getVisibleCN() {
		return visibleCN;
	}
	public void setVisibleCN(String visibleCN) {
		this.visibleCN = visibleCN;
	}
	public String getTopicCN() {
		return topicCN;
	}
	public void setTopicCN(String topicCN) {
		this.topicCN = topicCN;
	}
	public String getContentCN() {
		return contentCN;
	}
	public void setContentCN(String contentCN) {
		this.contentCN = contentCN;
	}
	public String getIndustryNewsDetailIdJP() {
		return industryNewsDetailIdJP;
	}
	public void setIndustryNewsDetailIdJP(String industryNewsDetailIdJP) {
		this.industryNewsDetailIdJP = industryNewsDetailIdJP;
	}
	public String getVisibleJP() {
		return visibleJP;
	}
	public void setVisibleJP(String visibleJP) {
		this.visibleJP = visibleJP;
	}
	public String getTopicJP() {
		return topicJP;
	}
	public void setTopicJP(String topicJP) {
		this.topicJP = topicJP;
	}
	public String getContentJP() {
		return contentJP;
	}
	public void setContentJP(String contentJP) {
		this.contentJP = contentJP;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getPhotoString() {
		return photoString;
	}
	public void setPhotoString(String photoString) {
		this.photoString = photoString;
	}
	
	
	
	
}
