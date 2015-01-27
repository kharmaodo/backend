package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class DocumentEditForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1618737602112822519L;
	//Document
	private String documentId;
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
	private MultipartFile attachment;
	private String newAttachment;
	//Document_Detail
	private String documentDetailIdTW;
	private String documentDetailIdEN;
	private String documentDetailIdJP;
	private String documentDetailIdCN;
	private String visibleTW;
	private String visibleEN;
	private String visibleJP;
	private String visibleCN;
	private String topicTW;
	private String topicEN;
	private String topicJP;
	private String topicCN;
	private String sourceTW;
	private String sourceEN;
	private String sourceJP;
	private String sourceCN;
	//attachment
	private String title;
	private String originNanme;
	private String type;
	private String path;
	private String pressReleaseId;
	
	//attachment_ext
	private int attachmentExtId;
	private String apprivalStatus;
	private String approval1;
	private String approval2;
	private String approval3;
	private String groupIds;
	private String downloadable;
	private String copyRight;
	private Date createTime;
	private String createBy;
	private Date modifyTime;
	private String modifyBy;
	private String isPass;
	//DocumentCategory
	private String parentId;
	private String parentName;
	private String categoryId;
	private String categroyName;
	private int categoryGroup;
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginNanme() {
		return originNanme;
	}
	public void setOriginNanme(String originNanme) {
		this.originNanme = originNanme;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPressReleaseId() {
		return pressReleaseId;
	}
	public void setPressReleaseId(String pressReleaseId) {
		this.pressReleaseId = pressReleaseId;
	}
	public int getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(int categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	public int getAttachmentExtId() {
		return attachmentExtId;
	}
	public void setAttachmentExtId(int attachmentExtId) {
		this.attachmentExtId = attachmentExtId;
	}
	public String getApprivalStatus() {
		return apprivalStatus;
	}
	public void setApprivalStatus(String apprivalStatus) {
		this.apprivalStatus = apprivalStatus;
	}
	public String getApproval1() {
		return approval1;
	}
	public void setApproval1(String approval1) {
		this.approval1 = approval1;
	}
	public String getApproval2() {
		return approval2;
	}
	public void setApproval2(String approval2) {
		this.approval2 = approval2;
	}
	public String getApproval3() {
		return approval3;
	}
	public void setApproval3(String approval3) {
		this.approval3 = approval3;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getDownloadable() {
		return downloadable;
	}
	public void setDownloadable(String downloadable) {
		this.downloadable = downloadable;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public MultipartFile getAttachment() {
		return attachment;
	}
	public void setAttachment(MultipartFile attachment) {
		this.attachment = attachment;
	}
	public String getDocumentDetailIdTW() {
		return documentDetailIdTW;
	}
	public void setDocumentDetailIdTW(String documentDetailIdTW) {
		this.documentDetailIdTW = documentDetailIdTW;
	}
	public String getDocumentDetailIdEN() {
		return documentDetailIdEN;
	}
	public void setDocumentDetailIdEN(String documentDetailIdEN) {
		this.documentDetailIdEN = documentDetailIdEN;
	}
	public String getDocumentDetailIdJP() {
		return documentDetailIdJP;
	}
	public void setDocumentDetailIdJP(String documentDetailIdJP) {
		this.documentDetailIdJP = documentDetailIdJP;
	}
	public String getDocumentDetailIdCN() {
		return documentDetailIdCN;
	}
	public void setDocumentDetailIdCN(String documentDetailIdCN) {
		this.documentDetailIdCN = documentDetailIdCN;
	}
	public String getVisibleTW() {
		return visibleTW;
	}
	public void setVisibleTW(String visibleTW) {
		this.visibleTW = visibleTW;
	}
	public String getVisibleEN() {
		return visibleEN;
	}
	public void setVisibleEN(String visibleEN) {
		this.visibleEN = visibleEN;
	}
	public String getVisibleJP() {
		return visibleJP;
	}
	public void setVisibleJP(String visibleJP) {
		this.visibleJP = visibleJP;
	}
	public String getVisibleCN() {
		return visibleCN;
	}
	public void setVisibleCN(String visibleCN) {
		this.visibleCN = visibleCN;
	}
	public String getTopicTW() {
		return topicTW;
	}
	public void setTopicTW(String topicTW) {
		this.topicTW = topicTW;
	}
	public String getTopicEN() {
		return topicEN;
	}
	public void setTopicEN(String topicEN) {
		this.topicEN = topicEN;
	}
	public String getTopicJP() {
		return topicJP;
	}
	public void setTopicJP(String topicJP) {
		this.topicJP = topicJP;
	}
	public String getTopicCN() {
		return topicCN;
	}
	public void setTopicCN(String topicCN) {
		this.topicCN = topicCN;
	}
	public String getSourceTW() {
		return sourceTW;
	}
	public void setSourceTW(String sourceTW) {
		this.sourceTW = sourceTW;
	}
	public String getSourceEN() {
		return sourceEN;
	}
	public void setSourceEN(String sourceEN) {
		this.sourceEN = sourceEN;
	}
	public String getSourceJP() {
		return sourceJP;
	}
	public void setSourceJP(String sourceJP) {
		this.sourceJP = sourceJP;
	}
	public String getSourceCN() {
		return sourceCN;
	}
	public void setSourceCN(String sourceCN) {
		this.sourceCN = sourceCN;
	}
	public String getNewAttachment() {
		return newAttachment;
	}
	public void setNewAttachment(String newAttachment) {
		this.newAttachment = newAttachment;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategroyName() {
		return categroyName;
	}
	public void setCategroyName(String categroyName) {
		this.categroyName = categroyName;
	}
	
}
