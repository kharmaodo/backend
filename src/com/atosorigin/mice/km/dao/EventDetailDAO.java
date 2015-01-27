package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.EventDetailVO;

public interface EventDetailDAO {
	public int insert(EventDetailVO eventDetailVO);
	public int delete(String eventId);
	
	public List<EventDetailVO> getEventDetailVO(String eventId);
}
