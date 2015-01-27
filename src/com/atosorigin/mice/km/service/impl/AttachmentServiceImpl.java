package com.atosorigin.mice.km.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.AttachmentBean;
import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.service.AttachmentService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;

public class AttachmentServiceImpl implements AttachmentService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private AttachmentDAO attachmentDAO;
	private AttachmentExtDAO attachmentExtDAO;
	
	public AttachmentDAO getAttachmentDAO() {
		return attachmentDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}
	
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setAttachmentExtDAO(AttachmentExtDAO attachmentExtDAO) {
		this.attachmentExtDAO = attachmentExtDAO;
	}

	public AttachmentServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	@Override
	public int insert(AttachmentVO avo, AttachmentExtVO aevo) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		avo.setId(uuid);
    		aevo.setAttachmentId(uuid);
			int rowM = this.attachmentDAO.insert(avo);
			int rowD = this.attachmentExtDAO.insert(aevo);
			if(rowM == 1 && rowD == 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}
	
	public int getAttachmentExtNum(Date today) {
		return this.attachmentExtDAO.getAttachmentExtNum(today);
	}

	@Override
	public AttachmentBean getAttachment(String id) {
		AttachmentVO attachmentVO = this.attachmentDAO.getAttachment(id);
		AttachmentExtVO attachmentExtVO = this.attachmentExtDAO.getAttattachmentExtVO(id);
		AttachmentBean bean = new AttachmentBean();
		bean.setAttachmentExtVO(attachmentExtVO);
		bean.setAttachmentVO(attachmentVO);
		return bean;
	}
	
}
