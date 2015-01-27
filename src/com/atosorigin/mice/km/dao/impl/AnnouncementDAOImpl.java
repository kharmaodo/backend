package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.AnnouncementDAO;
import com.atosorigin.mice.km.rowmapper.AnnouncementRowMapper;
import com.atosorigin.mice.km.rowmapper.PressReleaseRowMapper;
import com.atosorigin.mice.km.vo.AnnouncementVO;

public class AnnouncementDAOImpl implements AnnouncementDAO {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(AnnouncementVO announcementVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ANNOUNCEMENT  (ID, ");
		sql.append("					  	   DESCRIPTION, ");
		sql.append("					  	   SOURCE, ");
		sql.append("					  	   PUBLISH_DATE, ");
		sql.append("					       SHELVE_DATE, ");
		sql.append("					  	   UNSHELVE_DATE, ");
		sql.append("					  	   SHOW_PLACE, ");
		sql.append("					  	   ACTIVATED, ");
		sql.append("					       VERIFIED, ");
		sql.append("					       CREATOR, ");
		sql.append("					  	   CREATE_DATE, ");
		sql.append("					       MODIFIER, ");
		sql.append("					       MODIFY_DATE, ");
		sql.append("					       VERIFIER, ");
		sql.append("					       VERIFY_DATE, ");
		sql.append("					       VERIFY_NOTE, ");
		sql.append("					       OWNER_ID, ");
		sql.append("					       PHOTO, ");
		sql.append("					       ATTACHMENT_ID) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[]{announcementVO.getId(),
									announcementVO.getDescription(),
									announcementVO.getSource(),
									announcementVO.getPublishDate(),
									announcementVO.getShelveDate(),
									announcementVO.getUnshelveDate(),
									announcementVO.getShowPlace(),
									announcementVO.getActivated(),
									announcementVO.getVerified(),
									announcementVO.getCreator(),
									announcementVO.getCreateDate(),
									announcementVO.getModifier(),
									announcementVO.getModifyDate(),
									announcementVO.getVerifier(),
									announcementVO.getVerifyDate(),
									announcementVO.getVerifyNote(),
									announcementVO.getOwnerId(),
									announcementVO.getPhoto(),
									announcementVO.getAttachmentId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	
	@Override
	public int update(AnnouncementVO announcementVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ANNOUNCEMENT  SET DESCRIPTION = ?, ");
		sql.append("					  	 SOURCE = ?, ");
		sql.append("					  	 PUBLISH_DATE = ?, ");
		sql.append("					     SHELVE_DATE = ?, ");
		sql.append("					  	 UNSHELVE_DATE = ?, ");
		sql.append("					  	 SHOW_PLACE = ?, ");
		sql.append("					  	 ACTIVATED = ?, ");
		sql.append("					     MODIFIER = ?, ");
		sql.append("					     MODIFY_DATE = ?, ");
		sql.append("					     PHOTO = ?, ");
		sql.append("					     ATTACHMENT_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[]{announcementVO.getDescription(),
									announcementVO.getSource(),
									announcementVO.getPublishDate(),
									announcementVO.getShelveDate(),
									announcementVO.getUnshelveDate(),
									announcementVO.getShowPlace(),
									announcementVO.getActivated(),
									announcementVO.getModifier(),
									announcementVO.getModifyDate(),
									announcementVO.getPhoto(),
									announcementVO.getAttachmentId(),
									announcementVO.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int getPhotoNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ANNOUNCEMENT WHERE DATE_FORMAT(CREATE_DATE, '%Y-%m-%d') = CURDATE() AND PHOTO IS NOT NULL");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public List<AnnouncementVO> getAnnouncement(String description, String from, String to, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		AnnouncementRowMapper rm = new AnnouncementRowMapper();
		sql.append("SELECT * FROM ");
		sql.append("ANNOUNCEMENT ");
		sql.append("WHERE ACTIVATED = 'Y' AND VERIFIED = 'Y' AND ");
		sql.append("DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') >= ? AND ");
		sql.append("DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') <= ? ");
		if(!description.isEmpty()) {
			sql.append("AND DESCRIPTION LIKE '%"+ description +"%' ");
		}
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {from,
									 to,
				                     startPosition,
				                     pageRows};
		List<AnnouncementVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		if(vos.size() == 0) {
			return null;
		} else {
			return vos;
		}
	}

	@Override
	public int getAnnouncementNum(String description, String from, String to) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ");
		sql.append("ANNOUNCEMENT ");
		sql.append("WHERE ACTIVATED = 'Y' AND VERIFIED = 'Y' AND ");
		sql.append("DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') >= ? AND ");
		sql.append("DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') <= ? ");
		if(!description.isEmpty()) {
			description = "%" + description + "%";
			sql.append("AND DESCRIPTION LIKE '"+ description +"' ");
		}
		Object[] obj = new Object[] {from,
									 to};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public AnnouncementVO getAnnouncement(String id) {
		StringBuilder sql = new StringBuilder();
		AnnouncementRowMapper rm = new AnnouncementRowMapper();
		sql.append("SELECT * FROM ANNOUNCEMENT WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<AnnouncementVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

}
