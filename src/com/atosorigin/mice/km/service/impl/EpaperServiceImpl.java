package com.atosorigin.mice.km.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.EpaperDAO;
import com.atosorigin.mice.km.service.EpaperService;
import com.atosorigin.mice.km.vo.EpaperVO;

public class EpaperServiceImpl implements EpaperService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private EpaperDAO epaperDAO;
	
	public void setEpaperDAO(EpaperDAO epaperDAO) {
		this.epaperDAO = epaperDAO;
	}

	@Override
	public int insert(EpaperVO vo) {
	    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	    vo.setId(uuid); 
		return this.epaperDAO.insert(vo);
	}

	@Override
	public int update(EpaperVO vo) {
		return this.epaperDAO.update(vo);
	}

	@Override
	public EpaperVO getEpaper(String id) {
		return this.epaperDAO.getEpaper(id);
	}

	@Override
	public Pager getEpapers(String from, String to, String description, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		from = from.isEmpty() ? "2009-01-01" : from;
		to = to.isEmpty() ? "2099-12-31" : to;
	    List vos = this.epaperDAO.getEpapers(from, to, description, startPosition, Constants.PAGE_ROWS);
	    int total = this.epaperDAO.getEpapersNum(from, to, description);
	    
	    pager.setCurrentPage(currentPage);
	    pager.setObjList(vos);
	    pager.setPageRows(Constants.PAGE_ROWS);
	    pager.setToLink("epaper.htm?act=doList");
	    pager.setTotal(total);
	    
	    return pager;
	}

}
