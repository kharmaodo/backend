package com.atosorigin.mice.km.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.IndustryNewsBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.IndustryNewsDAO;
import com.atosorigin.mice.km.dao.IndustryNewsDetailDAO;
import com.atosorigin.mice.km.service.IndustryNewsService;
import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;
import com.atosorigin.mice.km.vo.IndustryNewsVO;

public class IndustryNewsServiceImpl implements IndustryNewsService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private IndustryNewsDAO industryNewsDAO;
	private IndustryNewsDetailDAO industryNewsDetailDAO;
	
	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setIndustryNewsDAO(IndustryNewsDAO industryNewsDAO) {
		this.industryNewsDAO = industryNewsDAO;
	}

	public void setIndustryNewsDetailDAO(IndustryNewsDetailDAO industryNewsDetailDAO) {
		this.industryNewsDetailDAO = industryNewsDetailDAO;
	}
	
	public IndustryNewsServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	@Override
	public int insert(IndustryNewsVO industryNewsVO, List<IndustryNewsDetailVO> industryNewsDetailVOs) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		industryNewsVO.setId(uuid);
			int rowM = this.industryNewsDAO.insert(industryNewsVO);
			int rowD = 0;
			for(IndustryNewsDetailVO industryNewsDetailVO : industryNewsDetailVOs) {
				String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
				industryNewsDetailVO.setId(uuidDetail);
				industryNewsDetailVO.setIndustryNewsId(uuid);
				rowD += this.industryNewsDetailDAO.insert(industryNewsDetailVO);
			}
			if(rowM == 1 && rowD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}

	@Override
	public int update(IndustryNewsVO industryNewsVO, List<IndustryNewsDetailVO> industryNewsDetailVOs) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowM = this.industryNewsDAO.update(industryNewsVO);
			int rowD = 0;
			if(this.industryNewsDetailDAO.delete(industryNewsVO.getId()) > 0) {
				for(IndustryNewsDetailVO industryNewsDetailVO : industryNewsDetailVOs) {
					String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
					industryNewsDetailVO.setId(uuidDetail);
					industryNewsDetailVO.setIndustryNewsId(industryNewsVO.getId());
					rowD += this.industryNewsDetailDAO.insert(industryNewsDetailVO);
				}
			}
			if(rowM == 1 && rowD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}

	@Override
	public Pager getIndustryNews(String description, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List vos = new ArrayList();
		int total = 0;
		if(description.isEmpty()) {
			vos = this.industryNewsDAO.getIndustryNews(startPosition, Constants.PAGE_ROWS);
			total = this.industryNewsDAO.getIndustryNewsNum();
		} else {
			vos = this.industryNewsDAO.getIndustryNews(description, startPosition, Constants.PAGE_ROWS);
			total = this.industryNewsDAO.getIndustryNewsNum(description);
		}
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(vos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("industryNews.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	
	}

	@Override
	public IndustryNewsBean getIndustryNews(String id) {
		IndustryNewsVO industryNewsVO = this.industryNewsDAO.getIndustryNews(id);
		List<IndustryNewsDetailVO> industryNewsDetailVOs = this.industryNewsDetailDAO.getIndustryNewsDetails(id);
		IndustryNewsBean bean = new IndustryNewsBean();
		bean.setIndustryNewsDetailVOs(industryNewsDetailVOs);
		bean.setIndustryNewsVO(industryNewsVO);
		return bean;
	}

	@Override
	public Pager getIndustryNewsForApprove(int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List vos = this.industryNewsDAO.getIndustryNewsForApprove(startPosition, Constants.PAGE_ROWS);
		int total = this.industryNewsDAO.getIndustryNewsForApproveNum();
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(vos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("industryNews.htm?act=doListResultApp");
		pager.setTotal(total);
		
		return pager;
	}

	@Override
	public int getPhotoNum() {
		return this.industryNewsDAO.getPhotoNum();
	}

}
