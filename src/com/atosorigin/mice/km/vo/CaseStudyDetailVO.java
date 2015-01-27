package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class CaseStudyDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7240739093391925999L;
	private String id;
	private String visible;
	private String title;
	private String url;
	private String location;
	private String organizer;
	private String shortDescription;
	private String locale;
	private String content;
	private String caseStudyId;
	private String youtubeId;
	private String attendee;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCaseStudyId() {
		return caseStudyId;
	}
	public void setCaseStudyId(String caseStudyId) {
		this.caseStudyId = caseStudyId;
	}
	public String getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}
	public String getAttendee() {
		return attendee;
	}
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}
	
}
