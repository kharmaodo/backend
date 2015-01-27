package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.OverseasEventBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.OverseasEventDetailVO;
import com.atosorigin.mice.km.vo.OverseasEventVO;

public interface OverseasEventService {
	public int insert(OverseasEventVO overseasEventVO, List<OverseasEventDetailVO> overseasEventDetails);
	public int update(OverseasEventVO overseasEventVO, List<OverseasEventDetailVO> overseasEventDetails);
	public int delete(String id);
	
	public OverseasEventBean getOverseasEvent(String id);
	
	public Pager getOverseasEvents(String from, String to, String description, int currentPage);
}
