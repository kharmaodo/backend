package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.VenueBean;
import com.atosorigin.mice.km.vo.VenueDetailVO;
import com.atosorigin.mice.km.vo.VenueVO;

public interface VenueService {
	public int insert(VenueVO vo, List<VenueDetailVO> vos);
	public int update(VenueVO vo, List<VenueDetailVO> vos);
	
	public VenueBean getVenue(String id);
	
	public Pager getVenue(String description, String venueCategoryId, String region, int currentPage);
}
