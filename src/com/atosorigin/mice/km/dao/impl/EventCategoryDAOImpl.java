package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.EventCategoryDAO;
import com.atosorigin.mice.km.rowmapper.EventCategoryRowMapper;
import com.atosorigin.mice.km.vo.EventCategoryVO;

public class EventCategoryDAOImpl implements EventCategoryDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<EventCategoryVO> getEventCategoryVOs() {
		EventCategoryRowMapper rm = new EventCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EVENT_CATEGORY ORDER BY RANK");
		List<EventCategoryVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		return vos.size() > 0 ? vos : null;
		
	}

}
