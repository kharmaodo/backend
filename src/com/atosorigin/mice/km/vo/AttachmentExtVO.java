package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class AttachmentExtVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5992453683992745283L;
	private int id;
	private String attachmentId;
	private String approvalStatus;
	private String approval1;
	private String approval2;
	private String approval3;
	private String groupId;
	private String downloadable;
	private String copyRight;
	private Date createTime;
	private String createBy;
	private Date modifyTime;
	private String modifyBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	
	
}
