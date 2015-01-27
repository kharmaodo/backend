package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;

public class WiflyVO implements Serializable {
	private String account;
	private String password;
	private String cardUserId;
	private Date appliedDate;
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
	public String getCardUserId() {
		return cardUserId;
	}
	public void setCardUserId(String cardUserId) {
		this.cardUserId = cardUserId;
	}
	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	

}
