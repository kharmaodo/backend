package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class AnnouncementVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6559746540538419552L;
	private String id;
	private String description;
	private String source;
	private Date publishDate;
	private Date shelveDate;
	private Date unshelveDate;
	private String showPlace;
	private String activated;
	private String verified;
	private String creator;
	private Date createDate;
	private String modifier;
	private Date modifyDate;
	private String verifier;
	private Date verifyDate;
	private String verifyNote;
	private String ownerId;
	private String photo;
	private String announcementCategoryId;
	private String attachmentId;
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
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getShelveDate() {
		return shelveDate;
	}
	public void setShelveDate(Date shelveDate) {
		this.shelveDate = shelveDate;
	}
	public Date getUnshelveDate() {
		return unshelveDate;
	}
	public void setUnshelveDate(Date unshelveDate) {
		this.unshelveDate = unshelveDate;
	}
	public String getShowPlace() {
		return showPlace;
	}
	public void setShowPlace(String showPlace) {
		this.showPlace = showPlace;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public Date getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getVerifyNote() {
		return verifyNote;
	}
	public void setVerifyNote(String verifyNote) {
		this.verifyNote = verifyNote;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAnnouncementCategoryId() {
		return announcementCategoryId;
	}
	public void setAnnouncementCategoryId(String announcementCategoryId) {
		this.announcementCategoryId = announcementCategoryId;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	
}
