package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.vo.DocumentDetailVO;

public class AttachmentComboBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5784617659778217156L;
	private String activated;
	private String approval1;
	private String approval2;
	private String approval3;
	private String approvalStatus;
	private int attachmentExtId;
	private String attachmentId;
	private int categoryGroup;
	private String copyRight;
	private String createBy;
	private Date createDate;
	private Date createTime;
	private String creator;
	private String documentCategoryDescription;
	private String documentCategoryId;
	private String documentDescription;
	private String documentId;
	private String downloadable;
	private String groupIds;
	private String issuuId;
	private int levelIndex;
	private String modifier;
	private String modifyBy;
	private Date modifyDate;
	private Date modifyTime;
	private String originName;
	private String ownerId;
	private String parentId;
	private String path;
	private String pressReleaseId;
	private int rank;
	private String title;
	private String type;
	private String verified;
	private String verifier;
	private Date verifyDate;
	private String verifyNote;
	List<DocumentDetailVO> documentDetailVOs;
	
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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
	public int getAttachmentExtId() {
		return attachmentExtId;
	}
	public void setAttachmentExtId(int attachmentExtId) {
		this.attachmentExtId = attachmentExtId;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public int getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(int categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getDocumentCategoryDescription() {
		return documentCategoryDescription;
	}
	public void setDocumentCategoryDescription(String documentCategoryDescription) {
		this.documentCategoryDescription = documentCategoryDescription;
	}
	public String getDocumentCategoryId() {
		return documentCategoryId;
	}
	public void setDocumentCategoryId(String documentCategoryId) {
		this.documentCategoryId = documentCategoryId;
	}
	public String getDocumentDescription() {
		return documentDescription;
	}
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDownloadable() {
		return downloadable;
	}
	public void setDownloadable(String downloadable) {
		this.downloadable = downloadable;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getIssuuId() {
		return issuuId;
	}
	public void setIssuuId(String issuuId) {
		this.issuuId = issuuId;
	}
	public int getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
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
	public List<DocumentDetailVO> getDocumentDetailVOs() {
		return documentDetailVOs;
	}
	public void setDocumentDetailVOs(List<DocumentDetailVO> documentDetailVOs) {
		this.documentDetailVOs = documentDetailVOs;
	}
	
}
