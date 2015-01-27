package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.AnnouncementDetailDAO;
import com.atosorigin.mice.km.rowmapper.AnnounceDetailRowMapper;
import com.atosorigin.mice.km.vo.AnnouncementDetailVO;

public class AnnouncementDetailDAOImpl implements AnnouncementDetailDAO {

private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(AnnouncementDetailVO announcementDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ANNOUNCEMENT_DETAIL (ID, ");
		sql.append("					  	   VISIBLE, ");
		sql.append("					  	   TOPIC, ");
		sql.append("					  	   CONTENT, ");
		sql.append("					       LOCALE, ");
		sql.append("					  	   ANNOUNCEMENT_ID) ");
		sql.append("VALUES (?,?,?,?,?,?)");
		Object[] obj = new Object[]{announcementDetailVO.getId(),
									announcementDetailVO.getVisible(),
									announcementDetailVO.getTopic(),
									announcementDetailVO.getContent(),
									announcementDetailVO.getLocale(),
									announcementDetailVO.getAnnouncementId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	
	@Override
	public int delete(String announcementId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ANNOUNCEMENT_DETAIL WHERE ANNOUNCEMENT_ID = ?");
		Object[] obj = new Object[]{announcementId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	


	@Override
	public List<AnnouncementDetailVO> getAnnouncementDetails(String announcementId) {
		StringBuilder sql = new StringBuilder();
		AnnounceDetailRowMapper rm = new AnnounceDetailRowMapper();
		sql.append("SELECT * FROM ANNOUNCEMENT_DETAIL WHERE ANNOUNCEMENT_ID = ?");
		Object[] obj = new Object[] {announcementId};
		List<AnnouncementDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		if(vos.size() == 0) {
			return null;
		} else {
			return vos;
		}
	}

}
