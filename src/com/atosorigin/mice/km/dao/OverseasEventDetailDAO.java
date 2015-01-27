package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.OverseasEventDetailVO;

public interface OverseasEventDetailDAO {
	public int insert(OverseasEventDetailVO overseasEventDetailVO);
	public int delete(String overseasEventId);
	
	public List<OverseasEventDetailVO> getOverseasEventDetails(String overseasEventId);
}
