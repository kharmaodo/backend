package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.EventBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.EventDetailVO;
import com.atosorigin.mice.km.vo.EventVO;

public interface EventService {
	public int insert(EventVO eventVO, List<EventDetailVO> eventDetailVOs);
	public int update(EventVO eventVO, List<EventDetailVO> eventDetailVOs);
	
	public Pager getEvents(String description, String eventCategoryId, String regionTaiwanId, int currentPage);
	public EventBean getEvent(String id);
	
	public Pager getEventsForApprove(int currentPage);
}
