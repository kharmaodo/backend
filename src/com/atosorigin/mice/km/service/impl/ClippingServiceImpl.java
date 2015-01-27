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

import com.atosorigin.mice.km.bean.ClippingBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.dao.ClippingDAO;
import com.atosorigin.mice.km.service.ClippingService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.ClippingVO;

public class ClippingServiceImpl implements ClippingService {
	private Logger logger = Logger.getLogger(this.getClass());
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private ClippingDAO clippingDAO;
	private AttachmentDAO attachmentDAO;
	private AttachmentExtDAO attachmentExtDAO;
	
	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setClippingDAO(ClippingDAO clippingDAO) {
		this.clippingDAO = clippingDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void setAttachmentExtDAO(AttachmentExtDAO attachmentExtDAO) {
		this.attachmentExtDAO = attachmentExtDAO;
	}

	public ClippingServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	@Override
	public int insert(ClippingVO clippingVO, AttachmentVO attachmentVO, AttachmentExtVO attachmentExtVO) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		if (attachmentVO != null) {
				clippingVO.setAttachmentId(uuid);
				attachmentVO.setId(uuid);
	    		attachmentExtVO.setAttachmentId(uuid);
				int rowA = this.attachmentDAO.insert(attachmentVO);
				int rowAE = this.attachmentExtDAO.insert(attachmentExtVO);
			}
    		
			int rowC = this.clippingDAO.insert(clippingVO);
			
			if(rowC == 1) {
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
	public int update(ClippingVO clippingVO, AttachmentVO attachmentVO, AttachmentExtVO attachmentExtVO) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowC = this.clippingDAO.update(clippingVO);
			if(attachmentVO != null) {
				int rowA = this.attachmentDAO.update(attachmentVO);
				int rowAE = this.attachmentExtDAO.update(attachmentExtVO);
			}
			if(rowC == 1) {
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
	public ClippingBean getClipping(String id) {
		AttachmentVO attachmentVO = null;
		AttachmentExtVO attachmentExtVO = null;
		ClippingBean bean = new ClippingBean();
		ClippingVO clippingVO = this.clippingDAO.getClipping(id);
		bean.setClippingVO(clippingVO);
		
		if(clippingVO.getAttachmentId() != null) {
			attachmentVO = this.attachmentDAO.getAttachment(clippingVO.getAttachmentId());
			attachmentExtVO = this.attachmentExtDAO.getAttattachmentExtVO(clippingVO.getAttachmentId());
		}
		
		bean.setAttachmentVO(attachmentVO);
		bean.setAttachmentExtVO(attachmentExtVO);
		return bean;
	}

	@Override
	public Pager getClippings(String keyword, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List result = this.clippingDAO.getClippings(keyword, startPosition, Constants.PAGE_ROWS);
		int total = this.clippingDAO.getClippingsNum(keyword);
		pager.setCurrentPage(currentPage);
		pager.setObjList(result);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("clip.htm?act=doList");
		pager.setTotal(total);
		return pager;
	}

}
