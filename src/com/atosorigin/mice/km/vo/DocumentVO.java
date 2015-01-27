package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DocumentVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1635133877819182583L;
	private String id;
	private String description;
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
	private String documentCategoryId;
	private String attachmentId;
	private String issuuId;
	private List<DocumentDetailVO> ddvos;
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
	public String getDocumentCategoryId() {
		return documentCategoryId;
	}
	public void setDocumentCategoryId(String documentCategoryId) {
		this.documentCategoryId = documentCategoryId;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getIssuuId() {
		return issuuId;
	}
	public void setIssuuId(String issuuId) {
		this.issuuId = issuuId;
	}
	public List<DocumentDetailVO> getDdvos() {
		return ddvos;
	}
	public void setDdvos(List<DocumentDetailVO> ddvos) {
		this.ddvos = ddvos;
	}

}
