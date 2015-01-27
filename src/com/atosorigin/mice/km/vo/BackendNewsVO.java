package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class BackendNewsVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5936021620200538273L;
	private int id;
	private String content;
	private String groupIds;
	private Date createTime;
	private Date modifyTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	

}
