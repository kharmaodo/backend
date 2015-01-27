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

import com.atosorigin.mice.km.bean.AnnouncementBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.AnnouncementDAO;
import com.atosorigin.mice.km.dao.AnnouncementDetailDAO;
import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.service.AnnouncementService;
import com.atosorigin.mice.km.vo.AnnouncementDetailVO;
import com.atosorigin.mice.km.vo.AnnouncementVO;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;

public class AnnouncementServiceImpl implements AnnouncementService {

private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private AnnouncementDAO announcementDAO;
	private AnnouncementDetailDAO announcementDetailDAO;
	private AttachmentDAO attachmentDAO;
	private AttachmentExtDAO attachmentExtDAO;
	
	public AnnouncementServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	@Override
	public int insert(AnnouncementVO announcementVO, List<AnnouncementDetailVO> announcementDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO) {
		 TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	     int rows = 0;
	     try {
	    	 if(attachment != null) {
	    		 attachment.setId(uuid);
	    		 attachment.setPressReleaseId(uuid);
	    		 attachmentExtVO.setAttachmentId(uuid);
	    		 announcementVO.setAttachmentId(uuid);
	    		 this.attachmentDAO.insert(attachment);
	    		 this.attachmentExtDAO.insert(attachmentExtVO);
	    	 }
	    	 
	    	 announcementVO.setId(uuid);
	    	 int rowMaster = this.announcementDAO.insert(announcementVO);
	    	 int rowDetail = 0;
	    	 for(AnnouncementDetailVO announcementDetailVO : announcementDetailVOs){
	    		 String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
	    		 announcementDetailVO.setId(uuidDetail);
	    		 announcementDetailVO.setAnnouncementId(uuid);
	    		 rowDetail += this.announcementDetailDAO.insert(announcementDetailVO);
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
	public int update(AnnouncementVO announcementVO, List<AnnouncementDetailVO> announcementDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO) {
		 TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     int rows = 0;
	     try {
	    	 if(attachment != null) {
	    		 this.attachmentDAO.update(attachment);
	    		 this.attachmentExtDAO.update(attachmentExtVO);
	    	 }

	    	 int rowMaster = this.announcementDAO.update(announcementVO);
	    	 int rowDetail = 0;
	    	 this.announcementDetailDAO.delete(announcementVO.getId());
	    	 for(AnnouncementDetailVO announcementDetailVO : announcementDetailVOs){
	    		 String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
	    		 announcementDetailVO.setId(uuidDetail);
	    		 announcementDetailVO.setAnnouncementId(announcementVO.getId());
	    		 rowDetail += this.announcementDetailDAO.insert(announcementDetailVO);
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
		return this.announcementDAO.getPhotoNum();
	}
	
	
	@Override
	public Pager getAnnouncement(String description, String from, String to, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List beans = new ArrayList();
		
		if(from.isEmpty()) {
			from = "2009-01-01";
		}
		
		if(to.isEmpty()) {
			to = "2099-12-31";
		}
		
		List<AnnouncementVO> avos = this.announcementDAO.getAnnouncement(description, from, to, startPosition, Constants.PAGE_ROWS);
		int total = this.announcementDAO.getAnnouncementNum(description, from, to);
		for(AnnouncementVO vo : avos) {
			AnnouncementBean bean = new AnnouncementBean();
			List advos = this.announcementDetailDAO.getAnnouncementDetails(vo.getId());
			bean.setAnnouncementVO(vo);
			bean.setAnnouncementDetailVOs(advos);
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
		pager.setToLink("ann.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}
	
	public AnnouncementBean getAnnouncement(String id) {
		AnnouncementVO vo = this.announcementDAO.getAnnouncement(id);
		List<AnnouncementDetailVO> vos = this.announcementDetailDAO.getAnnouncementDetails(id);
		AnnouncementBean bean = new AnnouncementBean();
		bean.setAnnouncementVO(vo);
		bean.setAnnouncementDetailVOs(vos);
		return bean;
	}

	
	public void setAnnouncementDAO(AnnouncementDAO announcementDAO) {
		this.announcementDAO = announcementDAO;
	}

	public void setAnnouncementDetailDAO(AnnouncementDetailDAO announcementDetailDAO) {
		this.announcementDetailDAO = announcementDetailDAO;
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
