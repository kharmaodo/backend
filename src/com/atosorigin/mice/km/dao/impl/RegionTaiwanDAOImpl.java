package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.RegionTaiwanDAO;
import com.atosorigin.mice.km.rowmapper.RegionTaiwanRowMapper;
import com.atosorigin.mice.km.vo.RegionTaiwanVO;

public class RegionTaiwanDAOImpl implements RegionTaiwanDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<RegionTaiwanVO> getRegionTaiwanVOs() {
		RegionTaiwanRowMapper rm = new RegionTaiwanRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM REGION_TAIWAN");
		List<RegionTaiwanVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		return vos.size() > 0 ? vos : null;
	}

}
