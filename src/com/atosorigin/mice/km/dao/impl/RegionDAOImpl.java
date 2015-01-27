package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.RegionDAO;
import com.atosorigin.mice.km.rowmapper.PlayerRowMapper;
import com.atosorigin.mice.km.rowmapper.RegionRowMapper;
import com.atosorigin.mice.km.vo.PlayerVO;
import com.atosorigin.mice.km.vo.RegionVO;

public class RegionDAOImpl implements RegionDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<RegionVO> getAll() {
		RegionRowMapper rm = new RegionRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM region");
		List<RegionVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		
		return vos.size() > 0 ? vos : null; 
	}
}
