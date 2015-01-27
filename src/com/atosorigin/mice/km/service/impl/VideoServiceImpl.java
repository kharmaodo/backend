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
import com.atosorigin.mice.km.bean.VideoBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.VideoDAO;
import com.atosorigin.mice.km.dao.VideoDetailDAO;
import com.atosorigin.mice.km.service.VideoService;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class VideoServiceImpl implements VideoService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private VideoDAO videoDAO;
	private VideoDetailDAO videoDetailDAO;
	
	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}
	
	public void setVideoDetailDAO(VideoDetailDAO videoDetailDAO) {
		this.videoDetailDAO = videoDetailDAO;
	}
	
	public VideoServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	

	@Override
	public VideoBean getVideo(String id) {
		VideoBean bean = new VideoBean();
		VideoVO videoVO = this.videoDAO.getVideo(id);
		List<VideoDetailVO> videoDetails = this.videoDetailDAO.getVideoDetails(id);
		bean.setVideoVo(videoVO);
		bean.setVideoDetailVo(videoDetails);
		return bean;
	}

	@Override
	public int insert(VideoVO videoVo, List<VideoDetailVO> videoDetailVos) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowV = this.videoDAO.insert(videoVo);
			int rowVD = 0;
			for(VideoDetailVO videoDetailVo : videoDetailVos) {
				rowVD += this.videoDetailDAO.insert(videoDetailVo);
			}
			if(rowV == 1 && rowVD >= 1) {
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
	public int update(VideoVO videoVo, List<VideoDetailVO> videoDetailVos) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowV = this.videoDAO.update(videoVo);
			int rowVD = 0;
			if(this.videoDetailDAO.delete(videoVo.getId()) > 0) {
				for(VideoDetailVO videoDetailVo : videoDetailVos) {
					rowVD += this.videoDetailDAO.insert(videoDetailVo);
				}
			}
			if(rowV == 1 && rowVD >= 1) {
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
	public Pager getVideos(String keyword, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List videos = new ArrayList();
		int total = 0;
		if(keyword.length() == 0) {
			videos = this.videoDAO.getVideos(startPosition, Constants.PAGE_ROWS);
			total = this.videoDAO.getVideosNum();
		} else {
			videos = this.videoDAO.getVideos(keyword, startPosition, Constants.PAGE_ROWS);
			total = this.videoDAO.getVideosNum(keyword);
		}
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(videos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("video.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}

	@Override
	public String getMaxId() {
		return this.videoDAO.getMaxId();
	}

}
