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

import com.atosorigin.mice.km.bean.OverseasEventBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.OverseasEventDAO;
import com.atosorigin.mice.km.dao.OverseasEventDetailDAO;
import com.atosorigin.mice.km.dao.RegionCategoryDAO;
import com.atosorigin.mice.km.service.OverseasEventService;
import com.atosorigin.mice.km.vo.OverseasEventDetailVO;
import com.atosorigin.mice.km.vo.OverseasEventVO;
import com.atosorigin.mice.km.vo.RegionCategoryVO;

public class OverseasEventServiceImpl implements OverseasEventService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private OverseasEventDAO overseasEventDAO;
	private OverseasEventDetailDAO overseasEventDetailDAO;
	private RegionCategoryDAO regionCategoryDAO;
	
	public OverseasEventServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	@Override
	public int insert(OverseasEventVO overseasEventVO, List<OverseasEventDetailVO> overseasEventDetails) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	     int rows = 0;
	     logger.debug("0000000000000000000");
	     try {
	    	 logger.debug("111111111111111111111");
	    	 overseasEventVO.setId(uuid);
	    	 int rowMaster = this.overseasEventDAO.insert(overseasEventVO);
	    	 int rowDetail = 0;
	    	 logger.debug("2222222222222222222");
	    	 for(OverseasEventDetailVO overseasEventDetailVO : overseasEventDetails){
	    		 logger.debug("333333333333333");
	    		 String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
	    		 overseasEventDetailVO.setId(uuidDetail);
	    		 overseasEventDetailVO.setOverseasEventId(uuid);
	    		 rowDetail += this.overseasEventDetailDAO.insert(overseasEventDetailVO);
	    		 logger.debug("4444444444444444");
	    	 }
	    	 
	    	 logger.debug("rowMaster ==== " + rowMaster);
	    	 logger.debug("rowDetail ==== " + rowDetail);
	    	 
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
	public int update(OverseasEventVO overseasEventVO, List<OverseasEventDetailVO> overseasEventDetails) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     int rows = 0;
	     try {
	    	 int rowMaster = this.overseasEventDAO.update(overseasEventVO);
	    	 int rowDetail = 0;
	    	 this.overseasEventDetailDAO.delete(overseasEventVO.getId());
	    	 for(OverseasEventDetailVO overseasEventDetailVO : overseasEventDetails){
	    		 String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
	    		 overseasEventDetailVO.setId(uuidDetail);
	    		 overseasEventDetailVO.setOverseasEventId(overseasEventVO.getId());
	    		 rowDetail += this.overseasEventDetailDAO.insert(overseasEventDetailVO);
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
	public int delete(String id) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     int rows = 0;
	     try {
	    	 int rowMaster = this.overseasEventDAO.delete(id);
	    	 int rowDetail = this.overseasEventDetailDAO.delete(id);
	    	 
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
	public OverseasEventBean getOverseasEvent(String id) {
		OverseasEventVO overseasEventVO = this.overseasEventDAO.getOverseasEvent(id);
		List<OverseasEventDetailVO> overseasEventDetails = this.overseasEventDetailDAO.getOverseasEventDetails(id);
		OverseasEventBean bean = new OverseasEventBean();
		bean.setOverseasEventVO(overseasEventVO);
		bean.setOverseasEventDetailVOs(overseasEventDetails);
		return bean;
	}

	@Override
	public Pager getOverseasEvents(String from, String to, String description, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		
		if(from.isEmpty()) {
			from = "2009-01-01";
		}
		
		if(to.isEmpty()) {
			to = "2099-12-31";
		}
		
		List beans = new ArrayList();
		List<OverseasEventVO> overseasEvents = this.overseasEventDAO.getOverseasEvents(description, from, to, startPosition, Constants.PAGE_ROWS);
		for(OverseasEventVO oevo : overseasEvents) {
			RegionCategoryVO rcvo = this.regionCategoryDAO.getRegionCategory(oevo.getRegionCategoryId());
			OverseasEventBean bean = new OverseasEventBean();
			bean.setOverseasEventVO(oevo);
			bean.setRegionCategoryVO(rcvo);
			beans.add(bean);
		}
		
		
		int total = this.overseasEventDAO.getOverseasEventsNum(description, from, to);
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(beans);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("oversea.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setOverseasEventDAO(OverseasEventDAO overseasEventDAO) {
		this.overseasEventDAO = overseasEventDAO;
	}

	public void setOverseasEventDetailDAO(
			OverseasEventDetailDAO overseasEventDetailDAO) {
		this.overseasEventDetailDAO = overseasEventDetailDAO;
	}

	public void setRegionCategoryDAO(RegionCategoryDAO regionCategoryDAO) {
		this.regionCategoryDAO = regionCategoryDAO;
	}
	
	

	
}
