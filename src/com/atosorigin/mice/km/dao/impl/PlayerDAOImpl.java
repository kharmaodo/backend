package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.PlayerDAO;
import com.atosorigin.mice.km.rowmapper.PlayerRowMapper;
import com.atosorigin.mice.km.rowmapper.ProjectRowMapper;
import com.atosorigin.mice.km.vo.PlayerVO;
import com.atosorigin.mice.km.vo.ProjectVO;

public class PlayerDAOImpl implements PlayerDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<PlayerVO> getAll() {
		PlayerRowMapper rm = new PlayerRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM player");
		List<PlayerVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		
		return vos.size() > 0 ? vos : null; 
	}

	@Override
	public List<PlayerVO> getByRegionId(int regionId) {
		PlayerRowMapper rm = new PlayerRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM player WHERE region = ?");
		Object[] obj = new Object[] {regionId};
				 					 
		List<PlayerVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size() > 0 ? vos : null; 
	}

	
	
}
