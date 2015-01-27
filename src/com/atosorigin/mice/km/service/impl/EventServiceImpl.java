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

import com.atosorigin.mice.km.bean.EventBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.EventDAO;
import com.atosorigin.mice.km.dao.EventDetailDAO;
import com.atosorigin.mice.km.service.EventService;
import com.atosorigin.mice.km.vo.EventDetailVO;
import com.atosorigin.mice.km.vo.EventVO;
import com.atosorigin.mice.km.vo.VideoDetailVO;

public class EventServiceImpl implements EventService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private EventDAO eventDAO;
	private EventDetailDAO eventDetailDAO;

	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public void setEventDetailDAO(EventDetailDAO eventDetailDAO) {
		this.eventDetailDAO = eventDetailDAO;
	}

	public EventServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	@Override
	public int insert(EventVO eventVO, List<EventDetailVO> eventDetailVOs) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		eventVO.setId(uuid);
			int rowM = this.eventDAO.insert(eventVO);
			int rowD = 0;
			for(EventDetailVO eventDetailVO : eventDetailVOs) {
				String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
				eventDetailVO.setId(uuidDetail);
				eventDetailVO.setEventId(uuid);
				rowD += this.eventDetailDAO.insert(eventDetailVO);
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
	public int update(EventVO eventVO, List<EventDetailVO> eventDetailVOs) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowM = this.eventDAO.update(eventVO);
			int rowD = 0;
			if(this.eventDetailDAO.delete(eventVO.getId()) > 0) {
				for(EventDetailVO eventDetailVO : eventDetailVOs) {
					String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
					eventDetailVO.setId(uuidDetail);
					eventDetailVO.setEventId(eventVO.getId());
					rowD += this.eventDetailDAO.insert(eventDetailVO);
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
	public Pager getEvents(String description, String eventCategoryId, String regionTaiwanId, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List eventVOs = this.eventDAO.getEventVOs(description, eventCategoryId, regionTaiwanId, startPosition, Constants.PAGE_ROWS);
		int total = this.eventDAO.getEventVOsNum(description, eventCategoryId, regionTaiwanId);
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(eventVOs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("event.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	
	}

	@Override
	public EventBean getEvent(String id) {
		EventBean bean = new EventBean();
		EventVO eventVO = this.eventDAO.getEventVO(id);
		List<EventDetailVO> eventDetailVOs = this.eventDetailDAO.getEventDetailVO(id);
		bean.setEventVO(eventVO);
		bean.setEventDetailVOs(eventDetailVOs);
		return bean;
	}
	
	@Override
	public Pager getEventsForApprove(int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List eventVOs = this.eventDAO.getEventVOsForApprove(startPosition, Constants.PAGE_ROWS);
		int total = this.eventDAO.getEventVOsForApproveNum();
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(eventVOs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("eventApp.htm?act=doListApp");
		pager.setTotal(total);
		
		return pager;
	
	}

}
