package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.EventDetailVO;
import com.atosorigin.mice.km.vo.EventVO;

public class EventBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2461498575811605062L;
	private EventVO eventVO;
	private List<EventDetailVO> eventDetailVOs;
	public EventVO getEventVO() {
		return eventVO;
	}
	public void setEventVO(EventVO eventVO) {
		this.eventVO = eventVO;
	}
	public List<EventDetailVO> getEventDetailVOs() {
		return eventDetailVOs;
	}
	public void setEventDetailVOs(List<EventDetailVO> eventDetailVOs) {
		this.eventDetailVOs = eventDetailVOs;
	}

	
	
}
