package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class VenueDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2753848928323515506L;
	private String id;
	private String visible;
	private String name;
	private String address;
	private String parking;
	private String hotel;
	private String mrtStation;
	private String busStop;
	private String hsrStation;
	private String trainStation;
	private String hospital;
	private String locale;
	private String url;
	private String venueId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getMrtStation() {
		return mrtStation;
	}
	public void setMrtStation(String mrtStation) {
		this.mrtStation = mrtStation;
	}
	public String getBusStop() {
		return busStop;
	}
	public void setBusStop(String busStop) {
		this.busStop = busStop;
	}
	public String getHsrStation() {
		return hsrStation;
	}
	public void setHsrStation(String hsrStation) {
		this.hsrStation = hsrStation;
	}
	public String getTrainStation() {
		return trainStation;
	}
	public void setTrainStation(String trainStation) {
		this.trainStation = trainStation;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	
}
