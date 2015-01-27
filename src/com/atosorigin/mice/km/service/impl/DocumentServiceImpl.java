package com.atosorigin.mice.km.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.DocumentDetailBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.dao.DocumentDAO;
import com.atosorigin.mice.km.dao.DocumentDetailDAO;
import com.atosorigin.mice.km.service.DocumentService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public class DocumentServiceImpl implements DocumentService {
	private Logger logger = Logger.getLogger(this.getClass());
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	
	private DocumentDAO documentDAO;
	private AttachmentDAO attachmentDAO;
	private AttachmentExtDAO attachmentExtDAO;
	private DocumentDetailDAO documentDetailDAO;
	
	public DocumentDAO getDocumentDAO() {
		return documentDAO;
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void setAttachmentExtDAO(AttachmentExtDAO attachmentExtDAO) {
		this.attachmentExtDAO = attachmentExtDAO;
	}

	public void setDocumentDetailDAO(DocumentDetailDAO documentDetailDAO) {
		this.documentDetailDAO = documentDetailDAO;
	}

	public DocumentServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	@Override
	public int insert(DocumentVO dvo, AttachmentVO avo, AttachmentExtVO aevo) {
		 TransactionStatus status = this.transactionManager.getTransaction(this.def);
	        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	        int rows = 0;
			try {
				dvo.setId(uuid);
				dvo.setAttachmentId(uuid);
				int rowDvo = this.documentDAO.insert(dvo);
				
				avo.setId(uuid);
				int rowAvo = this.attachmentDAO.insert(avo);
				
				aevo.setAttachmentId(uuid);
				int rowAevo = this.attachmentExtDAO.insert(aevo);
				int rowDdvo = 0;
				for(DocumentDetailVO ddvo : dvo.getDdvos()){
					String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
					ddvo.setDocumentId(uuid);
					ddvo.setId(uuidDetail);
					rowDdvo += this.documentDetailDAO.insert(ddvo);
				}
				if(rowAvo == 1 && rowAevo == 1 && rowDvo == 1 && rowDdvo >= 1) {
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
	public int getAttachmentExtNum(Date today) {
		return this.attachmentExtDAO.getAttachmentExtNum(today);
	}

	@Override
	public Pager getDocumentVOByOwnerId(int ownerId, String description, String approvalStatus, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List dbs;
		int total;
		if("0".equals(approvalStatus)) {
			dbs = this.getDocumentDAO().getDocumentVOByOwnerId(ownerId, description, startPosition, Constants.PAGE_ROWS);
			total = this.getDocumentDAO().getDocumentVOByOwnerIdNum(ownerId, description);
		} else {
			dbs = this.getDocumentDAO().getDocumentVOByOwnerId(ownerId, description, approvalStatus, startPosition, Constants.PAGE_ROWS);
			total = this.getDocumentDAO().getDocumentVOByOwnerIdNum(ownerId, description, approvalStatus);
		}
		pager.setCurrentPage(currentPage);
		pager.setObjList(dbs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("doc.htm?act=doListByOwnerId");
		pager.setTotal(total);
		return pager;
	}
	
	@Override
	public int update(DocumentVO dvo, AttachmentVO avo, AttachmentExtVO aevo) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
		try {
			int rowDdvoDel = this.documentDetailDAO.delete(dvo.getId());
			int rowDvo = this.documentDAO.update(dvo);
			int rowAvo = this.attachmentDAO.update(avo);
			int rowAevo = this.attachmentExtDAO.update(aevo);
			int rowDdvo = 0;
			for(DocumentDetailVO ddvo : dvo.getDdvos()){
				String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
				ddvo.setId(uuidDetail);
				ddvo.setDocumentId(dvo.getId());
				rowDdvo += this.documentDetailDAO.insert(ddvo);
			}
			if(rowDdvoDel > 0 && rowAvo == 1 && rowAevo == 1 && rowDvo == 1 && rowDdvo > 0) {
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
	public Pager getDocumentVOByVerified(String description, String approvalStatus, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List dbs;
		int total;
		if("0".equals(approvalStatus)) {
			dbs = this.getDocumentDAO().getDocumentVOByVerified(description, startPosition, Constants.PAGE_ROWS);
			total = this.getDocumentDAO().getDocumentVOByVerifiedNum(description);
		} else {
			dbs = this.getDocumentDAO().getDocumentVOByVerified(description, approvalStatus, startPosition, Constants.PAGE_ROWS);
			total = this.getDocumentDAO().getDocumentVOByVerifiedNum(description, approvalStatus);
		}
		pager.setCurrentPage(currentPage);
		pager.setObjList(dbs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("doc.htm?act=doListByVerified");
		pager.setTotal(total);
		return pager;
	}
	
	@Override
	public DocumentDetailBean getDocumentVOByVerified(String id) {
		return this.getDocumentDAO().getDocumentVOByVerified(id);
	}

	@Override
	public int deleteDocumentDetailByDocumentId(String doucmentId) {
		return this.documentDetailDAO.delete (doucmentId);
	}
	
	@Override
	public Pager getDocumentVOByBOFT(String description, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List dbs = this.getDocumentDAO().getDocumentVOByBOFT(description, startPosition, Constants.PAGE_ROWS);
		int total = this.getDocumentDAO().getDocumentVOByBOFTNum(description);
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(dbs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("doc.htm?act=doListByBOFT");
		pager.setTotal(total);
		return pager;
	}

	@Override
	public int getForTask(String approvalStatus) {
		return this.getDocumentDAO().getForTask(approvalStatus);
	}

}
