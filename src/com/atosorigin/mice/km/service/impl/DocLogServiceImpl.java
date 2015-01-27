package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.DocLogDAO;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.vo.DocLogVO;

public class DocLogServiceImpl implements DocLogService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DocLogDAO docLogDAO;
	
	public DocLogDAO getDocLogDAO() {
		return docLogDAO;
	}

	public void setDocLogDAO(DocLogDAO docLogDAO) {
		this.docLogDAO = docLogDAO;
	}

	public Pager getDocLog(String from, String to, String account, int currentPage) {
		List dlvos;
		int total;
		String toLink = "";
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		
		if("".equals(account)) {
			total = this.getDocLogDAO().getDocLogNum(from, to);
			dlvos = this.getDocLogDAO().getDocLog(from, to, startPosition, Constants.PAGE_ROWS);
		} else {
			total = this.getDocLogDAO().getDocLogNum(from, to, account);
			dlvos = this.getDocLogDAO().getDocLog(from, to, account, startPosition, Constants.PAGE_ROWS);
		}
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(dlvos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink(toLink);
		pager.setTotal(total);
		
		return pager;
	}

	public int insert(DocLogVO dlvo) {
		return this.getDocLogDAO().insert(dlvo);
	}

	@Override
	public DocLogVO getLastLogin(String account) {
		List<DocLogVO> result = this.getDocLogDAO().getDocLogDoWhat(account, "login", 1, 1);
		if(result == null) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Pager queryDocLog(String from, String to, String keyword, String sort, int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List dlvos = this.getDocLogDAO().queryDocLog(from, to, keyword, sort, startPosition, Constants.PAGE_ROWS);
		int total = this.getDocLogDAO().queryDocLogNum(from, to, keyword);
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(dlvos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("log.htm?act=doList");
		pager.setTotal(total);
		pager.setSort(sort);
		return pager;
	}

}
