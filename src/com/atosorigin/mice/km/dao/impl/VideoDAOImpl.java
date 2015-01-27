package com.atosorigin.mice.km.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.VideoBean;
import com.atosorigin.mice.km.dao.VideoDAO;
import com.atosorigin.mice.km.rowmapper.VideoDetailRowMapper;
import com.atosorigin.mice.km.rowmapper.VideoRowMapper;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class VideoDAOImpl implements VideoDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(VideoVO videoVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO VIDEO VALUES (?, ?, ?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {videoVo.getId(),
									 videoVo.getYoutubeId(),
									 videoVo.getTitle(),
									 videoVo.getDescription(),
									 videoVo.getKeywords(),
									 videoVo.getVerified(),
									 videoVo.getUploadDate()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	@Override
	public String getMaxId() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MAX(ID) FROM VIDEO");
		String maxId = (String)this.jdbcTemplate.queryForObject(sql.toString(), java.lang.String.class);
		return maxId;
	}
	
	public int update(VideoVO videoVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE VIDEO SET YOUTUBE_ID = ?, ");
		sql.append("                 TITLE = ?, ");
		sql.append("                 DESCRIPTION = ?, ");
		sql.append("                 KEYWORDS = ?, ");
		sql.append("                 VERIFIED = ?, ");
		sql.append("                 UPLOAD_DATE = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {videoVo.getYoutubeId(),
									 videoVo.getTitle(),
									 videoVo.getDescription(),
									 videoVo.getKeywords(),
									 videoVo.getVerified(),
									 videoVo.getUploadDate(),
									 videoVo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	@Override
	public List<VideoVO> getVideos(String keyword, int startPosition, int pageRows) {
		List<String> videoIds = this.getVideoIds(keyword);
		if(videoIds.size() == 0) return null;
		String ids = "";
		for(String s : videoIds) {
			ids += "'" + s + "',";
		}
		ids = ids.substring(0, ids.length()-1);
		
		VideoRowMapper vrm = new VideoRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VIDEO WHERE ID IN (" + ids + ") ");
		sql.append("ORDER BY UPLOAD_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {startPosition, pageRows};
		List<VideoVO> videoVos = this.jdbcTemplate.query(sql.toString(), obj, vrm);

		return (videoVos.size() > 0) ? videoVos : null;
	}
	
	@Override
	public List<VideoVO> getVideos(int startPosition, int pageRows) {
		VideoRowMapper vrm = new VideoRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VIDEO ORDER BY UPLOAD_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {startPosition, pageRows};
		List<VideoVO> videoVos = this.jdbcTemplate.query(sql.toString(), obj, vrm);

		return (videoVos.size() > 0) ? videoVos : null;
	}
	
	@Override
	public int getVideosNum(String keyword) {
		List<String> videoIds = this.getVideoIds(keyword);
		if(videoIds.size() == 0) return 0;
		return videoIds.size();
	}
	
	@Override
	public int getVideosNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM VIDEO");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}
	
	private List<String> getVideoIds(String keyword) {
		keyword = "%" + keyword + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT A.ID FROM ");
		sql.append("VIDEO A, VIDEO_DETAIL B ");
		sql.append("WHERE (A.TITLE LIKE ? OR B.NAME LIKE ?) ");
		sql.append("AND B.VIDEO_ID = A.ID "); 
		sql.append("ORDER BY A.UPLOAD_DATE DESC");
		Object[] obj = new Object[] {keyword, keyword};
		List<String> videoIds = this.jdbcTemplate.queryForList(sql.toString(), obj, java.lang.String.class);
		return videoIds;
	}

	@Override
	public VideoVO getVideo(String id) {
		VideoRowMapper vrm = new VideoRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VIDEO WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<VideoVO> videoVos = this.jdbcTemplate.query(sql.toString(), obj, vrm);
		
		if(videoVos.size() > 0) {
			return videoVos.get(0);
		} else {
			return null;
		}
	}

}
