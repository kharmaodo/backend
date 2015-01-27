package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.VenueCategoryDAO;
import com.atosorigin.mice.km.rowmapper.VenueCategoryRowMapper;
import com.atosorigin.mice.km.vo.VenueCategoryVO;

public class VenueCategoryDAOImpl implements VenueCategoryDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<VenueCategoryVO> getVenueCategories() {
		VenueCategoryRowMapper rm = new VenueCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENUE_CATEGORY ORDER BY ID");
		List<VenueCategoryVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		return vos.size() > 0 ? vos : null;
	}

}
