package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.EventDAO;
import com.atosorigin.mice.km.rowmapper.EventRowMapper;
import com.atosorigin.mice.km.vo.EventVO;

public class EventDAOImpl implements EventDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(EventVO eventVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO EVENT VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[] {eventVO.getId(),
									 eventVO.getDescription(), 
									 eventVO.getLogo(), 
									 eventVO.getOverseas(), 
									 eventVO.getStartDate(),
									 eventVO.getEndDate(), 
									 eventVO.getPeople(), 
									 eventVO.getInfo(), 
									 eventVO.getUrl(), 
									 eventVO.getPotentialShow(),
									 eventVO.getCountries(), 
									 eventVO.getSource(), 
									 eventVO.getPhoto(), 
									 eventVO.getActivated(), 
									 eventVO.getVerified(),
									 eventVO.getCreator(), 
									 eventVO.getCreateDate(), 
									 eventVO.getModifier(), 
									 eventVO.getModifyDate(), 
									 eventVO.getVerifier(),
									 eventVO.getVerifyDate(), 
									 eventVO.getVerifyNote(), 
									 eventVO.getOwnerId(), 
									 eventVO.getEventEntryId(),
									 eventVO.getEventCategoryId(), 
									 eventVO.getRegionTaiwanId(),
									 eventVO.getContact(),
									 eventVO.getContactEmail(),
									 eventVO.getContactTel()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(EventVO eventVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE EVENT SET DESCRIPTION = ?, ");
		sql.append("                 LOGO = ?, ");
		sql.append("                 OVERSEAS = ?, ");
		sql.append("                 START_DATE = ?, ");
		sql.append("                 END_DATE = ?, ");
		sql.append("                 PEOPLE = ?, ");
		sql.append("                 INFO = ?, ");
		sql.append("                 URL = ?, ");
		sql.append("                 POTENTIAL_SHOW = ?, ");
		sql.append("                 COUNTRIES = ?, ");
		sql.append("                 SOURCE = ?, ");
		sql.append("                 PHOTO = ?, ");
		sql.append("                 ACTIVATED = ?, ");
		sql.append("                 VERIFIED = ?, ");
		sql.append("                 CREATOR = ?, ");
		sql.append("                 CREATE_DATE = ?, ");
		sql.append("                 MODIFIER = ?, ");
		sql.append("                 MODIFY_DATE = ?, ");
		sql.append("                 VERIFIER = ?, ");
		sql.append("                 VERIFY_DATE = ?, ");
		sql.append("                 VERIFY_NOTE = ?, ");
		sql.append("                 OWNER_ID = ?, ");
		sql.append("                 EVENT_ENTRY_ID = ?, ");
		sql.append("                 EVENT_CATEGORY_ID = ?, ");
		sql.append("                 REGION_TAIWAN_ID = ? ");
		sql.append("WHERE ID = ?");
		
		Object[] obj = new Object[] {eventVO.getDescription(), 
				 					 eventVO.getLogo(), 
				 					 eventVO.getOverseas(), 
				 					 eventVO.getStartDate(),
				 					 eventVO.getEndDate(), 
				 					 eventVO.getPeople(), 
				 					 eventVO.getInfo(), 
				 					 eventVO.getUrl(), 
				 					 eventVO.getPotentialShow(),
									 eventVO.getCountries(), 
									 eventVO.getSource(), 
									 eventVO.getPhoto(), 
									 eventVO.getActivated(), 
									 eventVO.getVerified(),
									 eventVO.getCreator(), 
									 eventVO.getCreateDate(), 
									 eventVO.getModifier(), 
									 eventVO.getModifyDate(), 
									 eventVO.getVerifier(),
									 eventVO.getVerifyDate(), 
									 eventVO.getVerifyNote(), 
									 eventVO.getOwnerId(), 
									 eventVO.getEventEntryId(),
									 eventVO.getEventCategoryId(), 
									 eventVO.getRegionTaiwanId(),
									 eventVO.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<EventVO> getEventVOs(String description, String eventCategoryId, String regionTaiwanId, int startPosition, int pageRows) {
		EventRowMapper rm = new EventRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EVENT WHERE ");
		sql.append("ACTIVATED = 'Y' AND "); 
		if(!description.isEmpty()) {
			description = "%" + description + "%";
			sql.append("DESCRIPTION LIKE '" + description + "' AND ");
		}
		if(!eventCategoryId.isEmpty()) {
			sql.append("EVENT_CATEGORY_ID = '" + eventCategoryId +  "'  AND ");
		}
		if(!regionTaiwanId.isEmpty()) {
			sql.append("REGION_TAIWAN_ID = '" + regionTaiwanId + "' AND ");
		}
		sql.delete(sql.length()-4, sql.length());
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ?");
		
		Object[] obj = new Object[]{startPosition,
									pageRows};
		
		List<EventVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}
	
	
	@Override
	public int getEventVOsNum(String description, String eventCategoryId, String regionTaiwanId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM EVENT WHERE ");
		sql.append("ACTIVATED = 'Y' AND ");
		if(!description.isEmpty()) {
			description = "%" + description + "%";
			sql.append("DESCRIPTION LIKE '" + description + "' AND ");
		}
		if(!eventCategoryId.isEmpty()) {
			sql.append("EVENT_CATEGORY_ID = '" + eventCategoryId +  "'  AND ");
		}
		if(!regionTaiwanId.isEmpty()) {
			sql.append("REGION_TAIWAN_ID = '" + regionTaiwanId + "' AND ");
		}
		sql.delete(sql.length()-4, sql.length());
		
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public EventVO getEventVO(String id) {
		EventRowMapper rm = new EventRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EVENT WHERE ID = ?");
		Object[] obj = new Object[]{id};
		List<EventVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size()>0 ? vos.get(0) : null;
	}

	@Override
	public List<EventVO> getEventVOsForApprove(int startPosition, int pageRows) {
		EventRowMapper rm = new EventRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EVENT WHERE CREATOR = 'Guest' AND ");
		sql.append("ACTIVATED IS NULL ORDER BY CREATE_DATE ");
		sql.append("LIMIT ?, ?");
		
		Object[] obj = new Object[]{startPosition,
									pageRows};
		
		List<EventVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getEventVOsForApproveNum() {
		EventRowMapper rm = new EventRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM EVENT WHERE CREATOR = 'Guest' AND ");
		sql.append("ACTIVATED IS NULL ");
		
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

}
