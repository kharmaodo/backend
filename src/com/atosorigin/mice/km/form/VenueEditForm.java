package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class VenueEditForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2066754055054289633L;
	//venueVO
	private String id;
	private String description;
	private String latitude;
	private String longitude;
	private String venueCategoryId;
	private String countyCategoryId;
	private String region;
	private int room;
	private int booth;
	private int meetingRoom;
	private int capacity;
	private String division;
	private int divisionRoom;
	private String tel;
	private String activated;

	//venueDetailVO
	private String visibleEN;
	private String nameEN;
	private String addressEN;
	private String urlEN;
	
	private String visibleTW;
	private String nameTW;
	private String addressTW;
	private String urlTW;
	
	private String visibleCN;
	private String nameCN;
	private String addressCN;
	private String urlCN;
	
	private String visibleJP;
	private String nameJP;
	private String addressJP;
	private String urlJP;
	
	public VenueEditForm() {
		this.room = 0;
		this.booth = 0;
		this.meetingRoom = 0;
		this.capacity = 0;
		this.divisionRoom = 0;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getVenueCategoryId() {
		return venueCategoryId;
	}
	public void setVenueCategoryId(String venueCategoryId) {
		this.venueCategoryId = venueCategoryId;
	}
	public String getCountyCategoryId() {
		return countyCategoryId;
	}
	public void setCountyCategoryId(String countyCategoryId) {
		this.countyCategoryId = countyCategoryId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getBooth() {
		return booth;
	}
	public void setBooth(int booth) {
		this.booth = booth;
	}
	public int getMeetingRoom() {
		return meetingRoom;
	}
	public void setMeetingRoom(int meetingRoom) {
		this.meetingRoom = meetingRoom;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public int getDivisionRoom() {
		return divisionRoom;
	}
	public void setDivisionRoom(int divisionRoom) {
		this.divisionRoom = divisionRoom;
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
	public String getAddressEN() {
		return addressEN;
	}
	public void setAddressEN(String addressEN) {
		this.addressEN = addressEN;
	}
	public String getUrlEN() {
		return urlEN;
	}
	public void setUrlEN(String urlEN) {
		this.urlEN = urlEN;
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
	public String getAddressTW() {
		return addressTW;
	}
	public void setAddressTW(String addressTW) {
		this.addressTW = addressTW;
	}
	public String getUrlTW() {
		return urlTW;
	}
	public void setUrlTW(String urlTW) {
		this.urlTW = urlTW;
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
	public String getAddressCN() {
		return addressCN;
	}
	public void setAddressCN(String addressCN) {
		this.addressCN = addressCN;
	}
	public String getUrlCN() {
		return urlCN;
	}
	public void setUrlCN(String urlCN) {
		this.urlCN = urlCN;
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
	public String getAddressJP() {
		return addressJP;
	}
	public void setAddressJP(String addressJP) {
		this.addressJP = addressJP;
	}
	public String getUrlJP() {
		return urlJP;
	}
	public void setUrlJP(String urlJP) {
		this.urlJP = urlJP;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}
	
	
	
	
}
