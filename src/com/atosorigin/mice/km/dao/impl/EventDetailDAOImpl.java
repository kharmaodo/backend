package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.EventDetailDAO;
import com.atosorigin.mice.km.rowmapper.EventDetailRowMapper;
import com.atosorigin.mice.km.vo.EventDetailVO;

public class EventDetailDAOImpl implements EventDetailDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(EventDetailVO eventDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO EVENT_DETAIL VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[]{eventDetailVO.getId(), 
				                    eventDetailVO.getVisible(), 
				                    eventDetailVO.getName(),
				                    eventDetailVO.getTopic(),
				                    eventDetailVO.getUrl(),
				                    eventDetailVO.getCountry(), 
				                    eventDetailVO.getPlace(),
				                    eventDetailVO.getOrganization(), 
				                    eventDetailVO.getLocale(), 
				                    eventDetailVO.getEventId(), 
				                    eventDetailVO.getContent()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int delete(String eventId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM EVENT_DETAIL WHERE EVENT_ID = ?");
		Object[] obj = new Object[]{eventId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<EventDetailVO> getEventDetailVO(String eventId) {
		EventDetailRowMapper rm = new EventDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EVENT_DETAIL WHERE EVENT_ID = ?");
		Object[] obj = new Object[]{eventId};
		List<EventDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size()>0 ? vos : null;
	}

}
