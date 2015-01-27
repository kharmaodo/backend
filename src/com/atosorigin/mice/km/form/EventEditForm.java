package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

public class EventEditForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244290689166564792L;
	//EventVO
	private String eventId;
	private String description;
	private String eventCategoryId;
	private String regionTaiwanId;
	private String startDate;
	private String endDate;
	private String overseas;
	private String source;
	private String contact;
	private String contactEmail;
	private String contactTel;

	// EventDetailVO
	
	private String eventDetailIdEN;
	private String visibleEN;
	private String nameEN;
	private String urlEN;
	private String countryEN;
	private String placeEN;
	private String organizationEN;
	private String contentEN;
	
	private String eventDetailIdJP;
	private String visibleJP;
	private String nameJP;
	private String urlJP;
	private String countryJP;
	private String placeJP;
	private String organizationJP;
	private String contentJP;
	
	private String eventDetailIdTW;
	private String visibleTW;
	private String nameTW;
	private String urlTW;
	private String countryTW;
	private String placeTW;
	private String organizationTW;
	private String contentTW;
	
	private String eventDetailIdCN;
	private String visibleCN;
	private String nameCN;
	private String urlCN;
	private String countryCN;
	private String placeCN;
	private String organizationCN;
	private String contentCN;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEventCategoryId() {
		return eventCategoryId;
	}
	public void setEventCategoryId(String eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}
	public String getRegionTaiwanId() {
		return regionTaiwanId;
	}
	public void setRegionTaiwanId(String regionTaiwanId) {
		this.regionTaiwanId = regionTaiwanId;
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
	public String getOverseas() {
		return overseas;
	}
	public void setOverseas(String overseas) {
		this.overseas = overseas;
	}
	public String getEventDetailIdEN() {
		return eventDetailIdEN;
	}
	public void setEventDetailIdEN(String eventDetailIdEN) {
		this.eventDetailIdEN = eventDetailIdEN;
	}
	public String getVisibleEN() {
		return visibleEN;
	}
	public void setVisibleEN(String visibleEN) {
		this.visibleEN = visibleEN;
	}
	public String getNameEN() {
		return nameEN;
	}
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	public String getUrlEN() {
		return urlEN;
	}
	public void setUrlEN(String urlEN) {
		this.urlEN = urlEN;
	}
	public String getCountryEN() {
		return countryEN;
	}
	public void setCountryEN(String countryEN) {
		this.countryEN = countryEN;
	}
	public String getPlaceEN() {
		return placeEN;
	}
	public void setPlaceEN(String placeEN) {
		this.placeEN = placeEN;
	}
	public String getOrganizationEN() {
		return organizationEN;
	}
	public void setOrganizationEN(String organizationEN) {
		this.organizationEN = organizationEN;
	}
	public String getContentEN() {
		return contentEN;
	}
	public void setContentEN(String contentEN) {
		this.contentEN = contentEN;
	}
	public String getEventDetailIdJP() {
		return eventDetailIdJP;
	}
	public void setEventDetailIdJP(String eventDetailIdJP) {
		this.eventDetailIdJP = eventDetailIdJP;
	}
	public String getVisibleJP() {
		return visibleJP;
	}
	public void setVisibleJP(String visibleJP) {
		this.visibleJP = visibleJP;
	}
	public String getNameJP() {
		return nameJP;
	}
	public void setNameJP(String nameJP) {
		this.nameJP = nameJP;
	}
	public String getUrlJP() {
		return urlJP;
	}
	public void setUrlJP(String urlJP) {
		this.urlJP = urlJP;
	}
	public String getCountryJP() {
		return countryJP;
	}
	public void setCountryJP(String countryJP) {
		this.countryJP = countryJP;
	}
	public String getPlaceJP() {
		return placeJP;
	}
	public void setPlaceJP(String placeJP) {
		this.placeJP = placeJP;
	}
	public String getOrganizationJP() {
		return organizationJP;
	}
	public void setOrganizationJP(String organizationJP) {
		this.organizationJP = organizationJP;
	}
	public String getContentJP() {
		return contentJP;
	}
	public void setContentJP(String contentJP) {
		this.contentJP = contentJP;
	}
	public String getEventDetailIdTW() {
		return eventDetailIdTW;
	}
	public void setEventDetailIdTW(String eventDetailIdTW) {
		this.eventDetailIdTW = eventDetailIdTW;
	}
	public String getVisibleTW() {
		return visibleTW;
	}
	public void setVisibleTW(String visibleTW) {
		this.visibleTW = visibleTW;
	}
	public String getNameTW() {
		return nameTW;
	}
	public void setNameTW(String nameTW) {
		this.nameTW = nameTW;
	}
	public String getUrlTW() {
		return urlTW;
	}
	public void setUrlTW(String urlTW) {
		this.urlTW = urlTW;
	}
	public String getCountryTW() {
		return countryTW;
	}
	public void setCountryTW(String countryTW) {
		this.countryTW = countryTW;
	}
	public String getPlaceTW() {
		return placeTW;
	}
	public void setPlaceTW(String placeTW) {
		this.placeTW = placeTW;
	}
	public String getOrganizationTW() {
		return organizationTW;
	}
	public void setOrganizationTW(String organizationTW) {
		this.organizationTW = organizationTW;
	}
	public String getContentTW() {
		return contentTW;
	}
	public void setContentTW(String contentTW) {
		this.contentTW = contentTW;
	}
	public String getEventDetailIdCN() {
		return eventDetailIdCN;
	}
	public void setEventDetailIdCN(String eventDetailIdCN) {
		this.eventDetailIdCN = eventDetailIdCN;
	}
	public String getVisibleCN() {
		return visibleCN;
	}
	public void setVisibleCN(String visibleCN) {
		this.visibleCN = visibleCN;
	}
	public String getNameCN() {
		return nameCN;
	}
	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
	public String getUrlCN() {
		return urlCN;
	}
	public void setUrlCN(String urlCN) {
		this.urlCN = urlCN;
	}
	public String getCountryCN() {
		return countryCN;
	}
	public void setCountryCN(String countryCN) {
		this.countryCN = countryCN;
	}
	public String getPlaceCN() {
		return placeCN;
	}
	public void setPlaceCN(String placeCN) {
		this.placeCN = placeCN;
	}
	public String getOrganizationCN() {
		return organizationCN;
	}
	public void setOrganizationCN(String organizationCN) {
		this.organizationCN = organizationCN;
	}
	public String getContentCN() {
		return contentCN;
	}
	public void setContentCN(String contentCN) {
		this.contentCN = contentCN;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	
	
	
	
}
