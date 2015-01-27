package com.atosorigin.mice.km.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PressReleaseEditForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7818167139050593019L;
	private String id;
	private String description;
	private String source;
	private String publishDate;
	private String shelveDate;
	private String unshelveDate;
	private MultipartFile photo;
	private MultipartFile attachment;
	private String activated;
	private String photoString;
	private String attachmentString;
	private String attachmentId;
	
	private String visibleTW;
	private String topicTW;
	private String contentTW;
	
	private String visibleEN;
	private String topicEN;
	private String contentEN;
	
	private String visibleCN;
	private String topicCN;
	private String contentCN;
	
	private String visibleJP;
	private String topicJP;
	private String contentJP;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public MultipartFile getAttachment() {
		return attachment;
	}
	public void setAttachment(MultipartFile attachment) {
		this.attachment = attachment;
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
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public String getPhotoString() {
		return photoString;
	}
	public void setPhotoString(String photoString) {
		this.photoString = photoString;
	}
	public String getAttachmentString() {
		return attachmentString;
	}
	public void setAttachmentString(String attachmentString) {
		this.attachmentString = attachmentString;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	
	
	

}
