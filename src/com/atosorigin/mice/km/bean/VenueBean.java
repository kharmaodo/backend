package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.VenueDetailVO;
import com.atosorigin.mice.km.vo.VenueVO;

public class VenueBean implements Serializable {
	private VenueVO venueVO;
	private List<VenueDetailVO> venueDetailVOs;
	public VenueVO getVenueVO() {
		return venueVO;
	}
	public void setVenueVO(VenueVO venueVO) {
		this.venueVO = venueVO;
	}
	public List<VenueDetailVO> getVenueDetailVOs() {
		return venueDetailVOs;
	}
	public void setVenueDetailVOs(List<VenueDetailVO> venueDetailVOs) {
		this.venueDetailVOs = venueDetailVOs;
	}
	
	
}
