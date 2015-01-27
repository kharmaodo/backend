package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class VideoDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4939403352827492990L;
	private String id;
	private String name;
	private String description;
	private String locale;
	private String videoId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	

}
