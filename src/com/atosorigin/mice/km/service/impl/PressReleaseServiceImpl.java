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

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.PressReleaseBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.dao.PressReleaseDAO;
import com.atosorigin.mice.km.dao.PressReleaseDetailDAO;
import com.atosorigin.mice.km.service.PressReleaseService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class PressReleaseServiceImpl implements PressReleaseService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private PressReleaseDAO pressReleaseDAO;
	private PressReleaseDetailDAO pressReleaseDetailDAO;
	private AttachmentDAO attachmentDAO;
	private AttachmentExtDAO attachmentExtDAO;
	
	public PressReleaseServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	@Override
	public int insert(PressReleaseVO pressReleaseVO, List<PressReleaseDetailVO> pressReleaseDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO) {
		 TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	     int rows = 0;
	     try {
	    	 if(attachment != null) {
	    		 attachment.setId(uuid);
	    		 attachment.setPressReleaseId(uuid);
	    		 attachmentExtVO.setAttachmentId(uuid);
	    		 pressReleaseVO.setAttachmentId(uuid);
	    		 this.attachmentDAO.insert(attachment);
	    		 this.attachmentExtDAO.insert(attachmentExtVO);
	    	 }
	    	 
	    	 pressReleaseVO.setId(uuid);
	    	 int rowMaster = this.pressReleaseDAO.insert(pressReleaseVO);
	    	 int rowDetail = 0;
	    	 for(PressReleaseDetailVO pressReleaseDetailVO : pressReleaseDetailVOs){
	    		 String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
	    		 pressReleaseDetailVO.setId(uuidDetail);
	    		 pressReleaseDetailVO.setPressReleaseId(uuid);
	    		 rowDetail += this.pressReleaseDetailDAO.insert(pressReleaseDetailVO);
	    	 }
	    	 
			 if(rowMaster == 1 && rowDetail >= 1) {
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
	public int update(PressReleaseVO pressReleaseVO, List<PressReleaseDetailVO> pressReleaseDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO) {
		 TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     int rows = 0;
	     try {
	    	 if(attachment != null) {
	    		 this.attachmentDAO.update(attachment);
	    		 this.attachmentExtDAO.update(attachmentExtVO);
	    	 }

	    	 int rowMaster = this.pressReleaseDAO.update(pressReleaseVO);
	    	 int rowDetail = 0;
	    	 this.pressReleaseDetailDAO.delete(pressReleaseVO.getId());
	    	 for(PressReleaseDetailVO pressReleaseDetailVO : pressReleaseDetailVOs){
	    		 String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
	    		 pressReleaseDetailVO.setId(uuidDetail);
	    		 pressReleaseDetailVO.setPressReleaseId(pressReleaseVO.getId());
	    		 rowDetail += this.pressReleaseDetailDAO.insert(pressReleaseDetailVO);
	    	 }
	    	 
			 if(rowMaster == 1 && rowDetail >= 1) {
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
	public int getPhotoNum() {
		return this.pressReleaseDAO.getPhotoNum();
	}
	
	
	@Override
	public Pager getPressRelease(String description, String from, String to, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List beans = new ArrayList();
		
		if(from.isEmpty()) {
			from = "2009-01-01";
		}
		
		if(to.isEmpty()) {
			to = "2099-12-31";
		}
		
		List<PressReleaseVO> prvos = this.pressReleaseDAO.getPressReleases(description, from, to, startPosition, Constants.PAGE_ROWS);
		int total = this.pressReleaseDAO.getPressReleasesNum(description, from, to);
		for(PressReleaseVO vo : prvos) {
			PressReleaseBean bean = new PressReleaseBean();
			List prdvos = this.pressReleaseDetailDAO.getPressReleaseDetails(vo.getId());
			bean.setPressReleaseVO(vo);
			bean.setPressReleaseDetailVOs(prdvos);
			//如果有附件的話
			if(vo.getAttachmentId() != null){
				AttachmentVO avo = this.attachmentDAO.getAttachment(vo.getAttachmentId());
				AttachmentExtVO aevo = this.attachmentExtDAO.getAttattachmentExtVO(vo.getAttachmentId());
				bean.setAttachmentVO(avo);
				bean.setAttachmentExtVO(aevo);
			}
			beans.add(bean);
		}
		pager.setCurrentPage(currentPage);
		pager.setObjList(beans);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("pr.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}
	
	public PressReleaseBean getPressRelease(String id) {
		PressReleaseVO vo = this.pressReleaseDAO.getPressReleases(id);
		List<PressReleaseDetailVO> vos = this.pressReleaseDetailDAO.getPressReleaseDetails(id);
		PressReleaseBean bean = new PressReleaseBean();
		bean.setPressReleaseVO(vo);
		bean.setPressReleaseDetailVOs(vos);
		return bean;
	}

	
	
	public void setPressReleaseDAO(PressReleaseDAO pressReleaseDAO) {
		this.pressReleaseDAO = pressReleaseDAO;
	}
	
	public void setPressReleaseDetailDAO(PressReleaseDetailDAO pressReleaseDetailDAO) {
		this.pressReleaseDetailDAO = pressReleaseDetailDAO;
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

	
	
	
	
	

}
