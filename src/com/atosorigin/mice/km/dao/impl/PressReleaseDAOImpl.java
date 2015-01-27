package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.PressReleaseDAO;
import com.atosorigin.mice.km.rowmapper.DocLogRowMapper;
import com.atosorigin.mice.km.rowmapper.PressReleaseRowMapper;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class PressReleaseDAOImpl implements PressReleaseDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(PressReleaseVO pressReleaseVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PRESS_RELEASE (ID, ");
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
		Object[] obj = new Object[]{pressReleaseVO.getId(),
									pressReleaseVO.getDescription(),
									pressReleaseVO.getSource(),
									pressReleaseVO.getPublishDate(),
									pressReleaseVO.getShelveDate(),
									pressReleaseVO.getUnshelveDate(),
									pressReleaseVO.getShowPlace(),
									pressReleaseVO.getActivated(),
									pressReleaseVO.getVerified(),
									pressReleaseVO.getCreator(),
									pressReleaseVO.getCreateDate(),
									pressReleaseVO.getModifier(),
									pressReleaseVO.getModifyDate(),
									pressReleaseVO.getVerifier(),
									pressReleaseVO.getVerifyDate(),
									pressReleaseVO.getVerifyNote(),
									pressReleaseVO.getOwnerId(),
									pressReleaseVO.getPhoto(),
									pressReleaseVO.getAttachmentId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	
	@Override
	public int update(PressReleaseVO pressReleaseVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PRESS_RELEASE SET DESCRIPTION = ?, ");
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
		Object[] obj = new Object[]{pressReleaseVO.getDescription(),
									pressReleaseVO.getSource(),
									pressReleaseVO.getPublishDate(),
									pressReleaseVO.getShelveDate(),
									pressReleaseVO.getUnshelveDate(),
									pressReleaseVO.getShowPlace(),
									pressReleaseVO.getActivated(),
									pressReleaseVO.getModifier(),
									pressReleaseVO.getModifyDate(),
									pressReleaseVO.getPhoto(),
									pressReleaseVO.getAttachmentId(),
									pressReleaseVO.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int getPhotoNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM PRESS_RELEASE WHERE DATE_FORMAT(CREATE_DATE, '%Y-%m-%d') = CURDATE() AND PHOTO IS NOT NULL");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public List<PressReleaseVO> getPressReleases(String description, String from, String to, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		PressReleaseRowMapper rm = new PressReleaseRowMapper();
		sql.append("SELECT * FROM ");
		sql.append("PRESS_RELEASE ");
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
		List<PressReleaseVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		if(vos.size() == 0) {
			return null;
		} else {
			return vos;
		}
	}

	@Override
	public int getPressReleasesNum(String description, String from, String to) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ");
		sql.append("PRESS_RELEASE ");
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
	public PressReleaseVO getPressReleases(String id) {
		StringBuilder sql = new StringBuilder();
		PressReleaseRowMapper rm = new PressReleaseRowMapper();
		sql.append("SELECT * FROM PRESS_RELEASE WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<PressReleaseVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

}
