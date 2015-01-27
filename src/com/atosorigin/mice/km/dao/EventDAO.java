package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.EventVO;

public interface EventDAO {
	public int insert(EventVO eventVO);
	public int update(EventVO eventVO);
	
	public List<EventVO> getEventVOs(String description, String eventCategoryId, String regionTaiwanid, int startPosition, int pageRows);
	public int getEventVOsNum(String description, String eventCategoryId, String regionTaiwanid);
	public EventVO getEventVO(String id);
	
	public List<EventVO> getEventVOsForApprove(int startPosition, int pageRows);
	public int getEventVOsForApproveNum();
}
