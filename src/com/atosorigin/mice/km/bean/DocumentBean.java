package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.Date;

public class DocumentBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3906065437950430515L;
	
	private String documentId;
	private String description;
	private String activated;
	private Date createDate;
	private String approvalStatus;
	private String groupIds;
	private String parentId;
	private String categoerName;
	private String owner;
	private String parentCategoryName;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCategoerName() {
		return categoerName;
	}
	public void setCategoerName(String categoerName) {
		this.categoerName = categoerName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	
	
	

}
