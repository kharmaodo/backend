package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.EventCategoryDAO;
import com.atosorigin.mice.km.service.EventCategoryService;
import com.atosorigin.mice.km.vo.EventCategoryVO;

public class EventCategoryServiceImpl implements EventCategoryService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private EventCategoryDAO eventCategoryDAO;
	
	public void setEventCategoryDAO(EventCategoryDAO eventCategoryDAO) {
		this.eventCategoryDAO = eventCategoryDAO;
	}

	@Override
	public List<EventCategoryVO> getEventCategoryVOs() {
		return this.eventCategoryDAO.getEventCategoryVOs();
	}

}
