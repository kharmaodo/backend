package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.VenueDetailVO;

public interface VenueDetailDAO {
	public int insert(VenueDetailVO vo);
	public int delete(String venueId);
	public List<VenueDetailVO> getVenueDetail(String venueId);
}
