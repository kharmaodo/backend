package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.OverseasEventVO;

public interface OverseasEventDAO {
	public int insert(OverseasEventVO overseasEventVO);
	public int update(OverseasEventVO overseasEventVO);
	public int delete(String id);
	
	public OverseasEventVO getOverseasEvent(String id);
	public List<OverseasEventVO> getOverseasEvents(String description, String from, String to, int startPosition, int pageRows);
	public int getOverseasEventsNum(String description, String from, String to);
}
