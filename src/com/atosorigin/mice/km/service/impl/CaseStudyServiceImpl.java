package com.atosorigin.mice.km.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.CaseStudyBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.CaseStudyDAO;
import com.atosorigin.mice.km.dao.CaseStudyDetailDAO;
import com.atosorigin.mice.km.service.CaseStudyService;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;
import com.atosorigin.mice.km.vo.CaseStudyVO;

public class CaseStudyServiceImpl implements CaseStudyService {
private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private CaseStudyDAO caseStudyDAO;
	private CaseStudyDetailDAO caseStudyDetailDAO;

	public void setCaseStudyDAO(CaseStudyDAO caseStudyDAO) {
		this.caseStudyDAO = caseStudyDAO;
	}
	public void setCaseStudyDetailDAO(CaseStudyDetailDAO caseStudyDetailDAO) {
		this.caseStudyDetailDAO = caseStudyDetailDAO;
	}
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public CaseStudyServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	@Override
	public CaseStudyBean getCaseStudyVO(String id) {
		CaseStudyBean bean = new CaseStudyBean();
		CaseStudyVO caseStudyVO = this.caseStudyDAO.getCaseStudyVO(id);
		List<CaseStudyDetailVO> caseStudyDetailVOs = this.caseStudyDetailDAO.getCaseStudyDetailVOs(id);
		bean.setCaseStudyVO(caseStudyVO);
		bean.setCaseStudyDetailVOs(caseStudyDetailVOs);
		return bean;
	}

	@Override
	public Pager getCaseStudyVOs(String description, String caseStudyCategoryId, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List result = new ArrayList();
		int total = 0;
		if(description.isEmpty() && caseStudyCategoryId.isEmpty()) {
			result = this.caseStudyDAO.getCaseStudyVOs(startPosition, Constants.PAGE_ROWS);
			total = this.caseStudyDAO.getCaseStudyVOsNum();
		} else if(!description.isEmpty() && caseStudyCategoryId.isEmpty()) {
			result = this.caseStudyDAO.getCaseStudyVOsByDescription(description, startPosition, Constants.PAGE_ROWS);
			total = this.caseStudyDAO.getCaseStudyVOsByDescriptionNum(description);
		} else if(description.isEmpty() && !caseStudyCategoryId.isEmpty()) {
			result = this.caseStudyDAO.getCaseStudyVOsByCaseStudyCategoryId(caseStudyCategoryId, startPosition, Constants.PAGE_ROWS);
			total = this.caseStudyDAO.getCaseStudyVOsByCaseStudyCategoryIdNum(caseStudyCategoryId);
		} else {
			result = this.caseStudyDAO.getCaseStudyVOs(description, caseStudyCategoryId, startPosition, Constants.PAGE_ROWS);
			total = this.caseStudyDAO.getCaseStudyVOsNum(description, caseStudyCategoryId);
		}
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(result);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("case.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}

	@Override
	public int insert(CaseStudyVO caseStudyVO, List<CaseStudyDetailVO> caseStudyDetailVos) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowC = this.caseStudyDAO.insert(caseStudyVO);
			int rowCD = 0;
			for(CaseStudyDetailVO caseStudyDetailVo : caseStudyDetailVos) {
				rowCD += this.caseStudyDetailDAO.insert(caseStudyDetailVo);
			}
			if(rowC == 1 && rowCD >= 1) {
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
	public int update(CaseStudyVO caseStudyVO, List<CaseStudyDetailVO> caseStudyDetailVos) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		this.caseStudyDetailDAO.delete(caseStudyVO.getId());
			int rowC = this.caseStudyDAO.update(caseStudyVO);
			int rowCD = 0;
			for(CaseStudyDetailVO caseStudyDetailVo : caseStudyDetailVos) {
				rowCD += this.caseStudyDetailDAO.insert(caseStudyDetailVo);
			}
			if(rowC == 1 && rowCD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}

}
