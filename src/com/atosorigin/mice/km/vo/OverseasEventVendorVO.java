package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class OverseasEventVendorVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7229465103198658325L;
	private String overseasEventId;
	private String vendorId;
	public String getOverseasEventId() {
		return overseasEventId;
	}
	public void setOverseasEventId(String overseasEventId) {
		this.overseasEventId = overseasEventId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	
}
