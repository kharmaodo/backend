package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.OverseasEventVendorDAO;
import com.atosorigin.mice.km.rowmapper.OverseasEventVendorRowMapper;
import com.atosorigin.mice.km.vo.OverseasEventVendorVO;

public class OverseasEventVendorDAOImpl implements OverseasEventVendorDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(OverseasEventVendorVO overseasEventVendorVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OVERSEAS_EVENT_VENDOR VALUES (?, ?)");
		Object[] obj = new Object[]{overseasEventVendorVO.getOverseasEventId(),
									overseasEventVendorVO.getVendorId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public OverseasEventVendorVO getOverseasEventVendor(String overseasEventId) {
		OverseasEventVendorRowMapper rm = new OverseasEventVendorRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM OVERSEAS_EVENT_VENDOR WHERE OVERSEAS_EVENT_ID = ?");
		Object[] obj = new Object[]{overseasEventId};
		List<OverseasEventVendorVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() == 0 ? null : vos.get(0);
	}

}
