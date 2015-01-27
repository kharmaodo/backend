package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.bean.VenueBean;
import com.atosorigin.mice.km.vo.VenueDetailVO;
import com.atosorigin.mice.km.vo.VenueVO;

public interface VenueDAO {
	public int insert(VenueVO vo);	
	public int update(VenueVO vo);
	
	public VenueVO getVenue(String id);
	
	public List<VenueVO> getVenue(String description, String venueCategoryId, String region, int startPosition, int pageRows);
	public int getVenueNum(String description, String venueCategoryId, String region);
}
