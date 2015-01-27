package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class IndustryNewsDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6552801438589501961L;
	private String id;
	private String visible;
	private String topic;
	private String content;
	private String locale;
	private String industryNewsId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getIndustryNewsId() {
		return industryNewsId;
	}
	public void setIndustryNewsId(String industryNewsId) {
		this.industryNewsId = industryNewsId;
	}
	
}
