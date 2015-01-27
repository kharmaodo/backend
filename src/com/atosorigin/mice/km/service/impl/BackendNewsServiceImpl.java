package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.BackendNewsDAO;
import com.atosorigin.mice.km.service.BackendNewsService;
import com.atosorigin.mice.km.vo.BackendNewsVO;


public class BackendNewsServiceImpl implements BackendNewsService {
	private Logger logger = Logger.getLogger(this.getClass());
	private BackendNewsDAO backendNewsDAO;
	

	public BackendNewsDAO getBackendNewsDAO() {
		return backendNewsDAO;
	}

	public void setBackendNewsDAO(BackendNewsDAO backendNewsDAO) {
		this.backendNewsDAO = backendNewsDAO;
	}

	@Override
	public int delete(int id) {
		return this.getBackendNewsDAO().delete(id);
	}

	@Override
	public int insert(BackendNewsVO bnvo) {
		return this.getBackendNewsDAO().insert(bnvo);
	}

	@Override
	public int update(BackendNewsVO bnvo) {
		return this.getBackendNewsDAO().update(bnvo);
	}

	@Override
	public List<BackendNewsVO> getBackendNews() {
		return this.getBackendNewsDAO().getBackendNews(0, 3);
	}

	@Override
	public Pager getBackendNews(int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List objList = this.getBackendNewsDAO().getBackendNews(startPosition, Constants.PAGE_ROWS);
		int total = this.getBackendNewsDAO().getBackendNewsNum();
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(objList);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("news.htm?act=doList");
		pager.setTotal(total);
		return pager;
	}

	@Override
	public BackendNewsVO getBackendNewsById(int id) {
		return this.getBackendNewsDAO().getBackendNews(id);
	}

	@Override
	public Pager getBackendNewsForHome(int currentPage) {
		int startPosition = (currentPage - 1) * 5;
		List objList = this.getBackendNewsDAO().getBackendNews(startPosition, 5);
		int total = this.getBackendNewsDAO().getBackendNewsNum();
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(objList);
		pager.setPageRows(5);
		pager.setToLink("news.htm?act=doListForHome");
		pager.setTotal(total);
		return pager;
	}

}
