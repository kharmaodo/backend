package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class DocMembersVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7625871366888743023L;
	private int id;
	private String account;
	private String password;
	private String email;
	private String name;
	private String identifivation;
	private String gender;
	private String educationCategoryId;
	private String address;
	private String phone;
	private String regionCategoryId;
	private String vendorCategoryId;
	private String positionCategoryId;
	private String localeId;
	private String groupId;
	private String receiveInfo;
	private String vendorChecked;
	private String confirmSent;
	private String activateString;
	private String activated;
	private Date createTime;
	private String vendorId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentifivation() {
		return identifivation;
	}
	public void setIdentifivation(String identifivation) {
		this.identifivation = identifivation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducationCategoryId() {
		return educationCategoryId;
	}
	public void setEducationCategoryId(String educationCategoryId) {
		this.educationCategoryId = educationCategoryId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegionCategoryId() {
		return regionCategoryId;
	}
	public void setRegionCategoryId(String regionCategoryId) {
		this.regionCategoryId = regionCategoryId;
	}
	public String getVendorCategoryId() {
		return vendorCategoryId;
	}
	public void setVendorCategoryId(String vendorCategoryId) {
		this.vendorCategoryId = vendorCategoryId;
	}
	public String getPositionCategoryId() {
		return positionCategoryId;
	}
	public void setPositionCategoryId(String positionCategoryId) {
		this.positionCategoryId = positionCategoryId;
	}
	public String getLocaleId() {
		return localeId;
	}
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getReceiveInfo() {
		return receiveInfo;
	}
	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}
	public String getVendorChecked() {
		return vendorChecked;
	}
	public void setVendorChecked(String vendorChecked) {
		this.vendorChecked = vendorChecked;
	}
	public String getConfirmSent() {
		return confirmSent;
	}
	public void setConfirmSent(String confirmSent) {
		this.confirmSent = confirmSent;
	}
	public String getActivateString() {
		return activateString;
	}
	public void setActivateString(String activateString) {
		this.activateString = activateString;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id = " + this.id + "\n");
		sb.append("account = " + this.account + "\n");
		sb.append("password =  " + this.password + "\n");
		sb.append("email = " + this.email + "\n");
		sb.append("name = " + this.name + "\n");
		sb.append("identifivation = " + this.identifivation + "\n");
		sb.append("gender = " + this.gender + "\n");
		sb.append("educationCategoryId = " + this.educationCategoryId + "\n");
		sb.append("address = " + this.address + "\n");
		sb.append("phone = " +  this.phone + "\n");
		sb.append("regionCategoryId = " + this.regionCategoryId + "\n");
		sb.append("vendorCategoryId = " + this.vendorCategoryId + "\n");
		sb.append("positionCategoryId = " + this.positionCategoryId + "\n");
		sb.append("localeId = " + this.localeId + "\n");
		sb.append("groupId = " + this.groupId + "\n");
		sb.append("receiveInfo = " + this.receiveInfo + "\n");
		sb.append("vendorChecked = " + this.vendorChecked + "\n");
		sb.append("confirmSent = " + this.confirmSent + "\n");
		sb.append("activateString = " + this.activateString + "\n");
		sb.append("activated = " + this.activated + "\n");
		sb.append("createTime = " + this.createTime.toString() + "\n");
		sb.append("vendorId = " + this.vendorId + "\n");
		
		return sb.toString();
	}

}
