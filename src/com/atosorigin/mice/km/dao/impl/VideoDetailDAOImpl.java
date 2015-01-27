package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.VideoDetailDAO;
import com.atosorigin.mice.km.rowmapper.VideoDetailRowMapper;
import com.atosorigin.mice.km.vo.VideoDetailVO;

public class VideoDetailDAOImpl implements VideoDetailDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(VideoDetailVO videoDetailVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO VIDEO_DETAIL VALUES (?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {videoDetailVo.getId(),
									 videoDetailVo.getName(),
									 videoDetailVo.getDescription(),
									 videoDetailVo.getLocale(),
									 videoDetailVo.getVideoId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(VideoDetailVO videoDetailVo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE VIDEO_DETAIL SET NAME = ?, ");
		sql.append("                        DESCRIPTION = ?, ");
		sql.append("                        LOCALE = ?, ");
		sql.append("                        VIDEO_ID = ? ");
		sql.append("WHERE ID = ?"); 
		Object[] obj = new Object[] {videoDetailVo.getName(),
									 videoDetailVo.getDescription(),
									 videoDetailVo.getLocale(),
									 videoDetailVo.getVideoId(),
									 videoDetailVo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public VideoDetailVO getVideoDetail(String id) {
		VideoDetailRowMapper vdrm = new VideoDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VIDEO_DETAIL WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<VideoDetailVO> videoDetailVos = this.jdbcTemplate.query(sql.toString(), obj, vdrm);
		
		if(videoDetailVos.size() > 0) {
			return videoDetailVos.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<VideoDetailVO> getVideoDetails(String videoId) {
		VideoDetailRowMapper vdrm = new VideoDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VIDEO_DETAIL WHERE VIDEO_ID = ?");
		Object[] obj = new Object[] {videoId};
		List<VideoDetailVO> videoDetailVos = this.jdbcTemplate.query(sql.toString(), obj, vdrm);
		
		if(videoDetailVos.size() > 0) {
			return videoDetailVos;
		} else {
			return null;
		}
	}

	@Override
	public int delete(String videoId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM VIDEO_DETAIL WHERE VIDEO_ID = ?");
		Object[] obj = new Object[] {videoId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

}
