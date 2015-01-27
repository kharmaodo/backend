package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.OverseasEventDetailDAO;
import com.atosorigin.mice.km.rowmapper.OverseasEventDetailRowMapper;
import com.atosorigin.mice.km.vo.OverseasEventDetailVO;

public class OverseasEventDetailDAOImpl implements OverseasEventDetailDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(OverseasEventDetailVO overseasEventDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OVERSEAS_EVENT_DETAIL VALUES (?, ?, ?, ?, ?)");
		Object[] obj = new Object[]{overseasEventDetailVO.getId(),
									overseasEventDetailVO.getEventName(),
									overseasEventDetailVO.getHostCity(),
									overseasEventDetailVO.getOverseasEventId(),
									overseasEventDetailVO.getLocale()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int delete(String overseasEventId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM OVERSEAS_EVENT_DETAIL WHERE OVERSEAS_EVENT_ID = ? ");
		Object[] obj = new Object[]{overseasEventId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<OverseasEventDetailVO> getOverseasEventDetails(String overseasEventId) {
		OverseasEventDetailRowMapper rm = new OverseasEventDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM OVERSEAS_EVENT_DETAIL WHERE OVERSEAS_EVENT_ID = ?");
		Object[] obj = new Object[]{overseasEventId};
		List<OverseasEventDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() == 0 ? null : vos;
	}

}
