package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class CaseStudyEditForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8778552808286542746L;
	private String description;
	private int rank;
	private MultipartFile photo;
	private String type;
	private String startDate;
	private String endDate;
	private String caseStudyCategoryId;
	private String id;
	private String photoURL;
	
	//zh_TW
	private String idTW;
	private String visibleTW;
	private String titleTW;
	private String urlTW;
	private String locationTW;
	private String organizerTW;
	private String attendeeTW;
	private String shortDescriptionTW;
	private String youtubeIdTW;
	private String contentTW;
	
	//zh_CN
	private String idCN;
	private String visibleCN;
	private String titleCN;
	private String urlCN;
	private String locationCN;
	private String organizerCN;
	private String attendeeCN;
	private String shortDescriptionCN;
	private String youtubeIdCN;
	private String contentCN;
	
	//en
	private String idEN;
	private String visibleEN;
	private String titleEN;
	private String urlEN;
	private String locationEN;
	private String organizerEN;
	private String attendeeEN;
	private String shortDescriptionEN;
	private String youtubeIdEN;
	private String contentEN;
	
	//JP
	private String idJP;
	private String visibleJP;
	private String titleJP;
	private String urlJP;
	private String locationJP;
	private String organizerJP;
	private String attendeeJP;
	private String shortDescriptionJP;
	private String youtubeIdJP;
	private String contentJP;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getVisibleTW() {
		return visibleTW;
	}
	public void setVisibleTW(String visibleTW) {
		this.visibleTW = visibleTW;
	}
	public String getTitleTW() {
		return titleTW;
	}
	public void setTitleTW(String titleTW) {
		this.titleTW = titleTW;
	}
	public String getUrlTW() {
		return urlTW;
	}
	public void setUrlTW(String urlTW) {
		this.urlTW = urlTW;
	}
	public String getLocationTW() {
		return locationTW;
	}
	public void setLocationTW(String locationTW) {
		this.locationTW = locationTW;
	}
	public String getOrganizerTW() {
		return organizerTW;
	}
	public void setOrganizerTW(String organizerTW) {
		this.organizerTW = organizerTW;
	}
	public String getAttendeeTW() {
		return attendeeTW;
	}
	public void setAttendeeTW(String attendeeTW) {
		this.attendeeTW = attendeeTW;
	}
	public String getShortDescriptionTW() {
		return shortDescriptionTW;
	}
	public void setShortDescriptionTW(String shortDescriptionTW) {
		this.shortDescriptionTW = shortDescriptionTW;
	}
	public String getYoutubeIdTW() {
		return youtubeIdTW;
	}
	public void setYoutubeIdTW(String youtubeIdTW) {
		this.youtubeIdTW = youtubeIdTW;
	}
	public String getContentTW() {
		return contentTW;
	}
	public void setContentTW(String contentTW) {
		this.contentTW = contentTW;
	}
	public String getVisibleCN() {
		return visibleCN;
	}
	public void setVisibleCN(String visibleCN) {
		this.visibleCN = visibleCN;
	}
	public String getTitleCN() {
		return titleCN;
	}
	public void setTitleCN(String titleCN) {
		this.titleCN = titleCN;
	}
	public String getUrlCN() {
		return urlCN;
	}
	public void setUrlCN(String urlCN) {
		this.urlCN = urlCN;
	}
	public String getLocationCN() {
		return locationCN;
	}
	public void setLocationCN(String locationCN) {
		this.locationCN = locationCN;
	}
	public String getOrganizerCN() {
		return organizerCN;
	}
	public void setOrganizerCN(String organizerCN) {
		this.organizerCN = organizerCN;
	}
	public String getAttendeeCN() {
		return attendeeCN;
	}
	public void setAttendeeCN(String attendeeCN) {
		this.attendeeCN = attendeeCN;
	}
	public String getShortDescriptionCN() {
		return shortDescriptionCN;
	}
	public void setShortDescriptionCN(String shortDescriptionCN) {
		this.shortDescriptionCN = shortDescriptionCN;
	}
	public String getYoutubeIdCN() {
		return youtubeIdCN;
	}
	public void setYoutubeIdCN(String youtubeIdCN) {
		this.youtubeIdCN = youtubeIdCN;
	}
	public String getContentCN() {
		return contentCN;
	}
	public void setContentCN(String contentCN) {
		this.contentCN = contentCN;
	}
	public String getVisibleEN() {
		return visibleEN;
	}
	public void setVisibleEN(String visibleEN) {
		this.visibleEN = visibleEN;
	}
	public String getTitleEN() {
		return titleEN;
	}
	public void setTitleEN(String titleEN) {
		this.titleEN = titleEN;
	}
	public String getUrlEN() {
		return urlEN;
	}
	public void setUrlEN(String urlEN) {
		this.urlEN = urlEN;
	}
	public String getLocationEN() {
		return locationEN;
	}
	public void setLocationEN(String locationEN) {
		this.locationEN = locationEN;
	}
	public String getOrganizerEN() {
		return organizerEN;
	}
	public void setOrganizerEN(String organizerEN) {
		this.organizerEN = organizerEN;
	}
	public String getAttendeeEN() {
		return attendeeEN;
	}
	public void setAttendeeEN(String attendeeEN) {
		this.attendeeEN = attendeeEN;
	}
	public String getShortDescriptionEN() {
		return shortDescriptionEN;
	}
	public void setShortDescriptionEN(String shortDescriptionEN) {
		this.shortDescriptionEN = shortDescriptionEN;
	}
	public String getYoutubeIdEN() {
		return youtubeIdEN;
	}
	public void setYoutubeIdEN(String youtubeIdEN) {
		this.youtubeIdEN = youtubeIdEN;
	}
	public String getContentEN() {
		return contentEN;
	}
	public void setContentEN(String contentEN) {
		this.contentEN = contentEN;
	}
	public String getVisibleJP() {
		return visibleJP;
	}
	public void setVisibleJP(String visibleJP) {
		this.visibleJP = visibleJP;
	}
	public String getTitleJP() {
		return titleJP;
	}
	public void setTitleJP(String titleJP) {
		this.titleJP = titleJP;
	}
	public String getUrlJP() {
		return urlJP;
	}
	public void setUrlJP(String urlJP) {
		this.urlJP = urlJP;
	}
	public String getLocationJP() {
		return locationJP;
	}
	public void setLocationJP(String locationJP) {
		this.locationJP = locationJP;
	}
	public String getOrganizerJP() {
		return organizerJP;
	}
	public void setOrganizerJP(String organizerJP) {
		this.organizerJP = organizerJP;
	}
	public String getAttendeeJP() {
		return attendeeJP;
	}
	public void setAttendeeJP(String attendeeJP) {
		this.attendeeJP = attendeeJP;
	}
	public String getShortDescriptionJP() {
		return shortDescriptionJP;
	}
	public void setShortDescriptionJP(String shortDescriptionJP) {
		this.shortDescriptionJP = shortDescriptionJP;
	}
	public String getYoutubeIdJP() {
		return youtubeIdJP;
	}
	public void setYoutubeIdJP(String youtubeIdJP) {
		this.youtubeIdJP = youtubeIdJP;
	}
	public String getContentJP() {
		return contentJP;
	}
	public void setContentJP(String contentJP) {
		this.contentJP = contentJP;
	}
	public String getCaseStudyCategoryId() {
		return caseStudyCategoryId;
	}
	public void setCaseStudyCategoryId(String caseStudyCategoryId) {
		this.caseStudyCategoryId = caseStudyCategoryId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdTW() {
		return idTW;
	}
	public void setIdTW(String idTW) {
		this.idTW = idTW;
	}
	public String getIdCN() {
		return idCN;
	}
	public void setIdCN(String idCN) {
		this.idCN = idCN;
	}
	public String getIdEN() {
		return idEN;
	}
	public void setIdEN(String idEN) {
		this.idEN = idEN;
	}
	public String getIdJP() {
		return idJP;
	}
	public void setIdJP(String idJP) {
		this.idJP = idJP;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	

	
}
