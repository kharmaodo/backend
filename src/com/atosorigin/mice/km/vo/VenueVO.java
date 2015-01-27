package com.atosorigin.mice.km.vo;

import java.io.Serializable;
import java.util.Date;


public class VenueVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2188399384625164403L;
	private String id;
	private String description;
	private String tel;
	private String url;
	private String ownRoom;
	private int room;
	private String nearbyHotel;
	private String ownParking;
	private String nearbyParking;
	private String nearbyMrtStation;
	private String nearbyBusStop;
	private String nearbyHsrStation;
	private String nearbyTrainStation;
	private String nearbyHospital;
	private String chineseFood;
	private String westernFood;
	private String refreshment;
	private String noCateringService;
	private String activated;
	private String verified;
	private String creator;
	private Date createDate;
	private String modifier;
	private Date modifyDate;
	private String verifier;
	private Date verifyDate;
	private String verifyNote;
	private String ownerId;
	private String venueCategoryId;
	private String countyCategoryId;
	private String region;
	private String latitude;
	private String longitude;
	private int booth;
	private int meetingRoom;
	private int capacity;
	private String division;
	private int divisionRoom;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOwnRoom() {
		return ownRoom;
	}
	public void setOwnRoom(String ownRoom) {
		this.ownRoom = ownRoom;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getNearbyHotel() {
		return nearbyHotel;
	}
	public void setNearbyHotel(String nearbyHotel) {
		this.nearbyHotel = nearbyHotel;
	}
	public String getOwnParking() {
		return ownParking;
	}
	public void setOwnParking(String ownParking) {
		this.ownParking = ownParking;
	}
	public String getNearbyParking() {
		return nearbyParking;
	}
	public void setNearbyParking(String nearbyParking) {
		this.nearbyParking = nearbyParking;
	}
	public String getNearbyMrtStation() {
		return nearbyMrtStation;
	}
	public void setNearbyMrtStation(String nearbyMrtStation) {
		this.nearbyMrtStation = nearbyMrtStation;
	}
	public String getNearbyBusStop() {
		return nearbyBusStop;
	}
	public void setNearbyBusStop(String nearbyBusStop) {
		this.nearbyBusStop = nearbyBusStop;
	}
	public String getNearbyHsrStation() {
		return nearbyHsrStation;
	}
	public void setNearbyHsrStation(String nearbyHsrStation) {
		this.nearbyHsrStation = nearbyHsrStation;
	}
	public String getNearbyTrainStation() {
		return nearbyTrainStation;
	}
	public void setNearbyTrainStation(String nearbyTrainStation) {
		this.nearbyTrainStation = nearbyTrainStation;
	}
	public String getNearbyHospital() {
		return nearbyHospital;
	}
	public void setNearbyHospital(String nearbyHospital) {
		this.nearbyHospital = nearbyHospital;
	}
	public String getChineseFood() {
		return chineseFood;
	}
	public void setChineseFood(String chineseFood) {
		this.chineseFood = chineseFood;
	}
	public String getWesternFood() {
		return westernFood;
	}
	public void setWesternFood(String westernFood) {
		this.westernFood = westernFood;
	}
	public String getRefreshment() {
		return refreshment;
	}
	public void setRefreshment(String refreshment) {
		this.refreshment = refreshment;
	}
	public String getNoCateringService() {
		return noCateringService;
	}
	public void setNoCateringService(String noCateringService) {
		this.noCateringService = noCateringService;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public Date getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getVerifyNote() {
		return verifyNote;
	}
	public void setVerifyNote(String verifyNote) {
		this.verifyNote = verifyNote;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	
	

}
