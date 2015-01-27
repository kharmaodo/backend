package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.CiDownloadRecordDAO;
import com.atosorigin.mice.km.rowmapper.CiApplicationRowMapper;
import com.atosorigin.mice.km.vo.CiApplicationVO;

public class CiDownloadRecordDAOImpl implements CiDownloadRecordDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int getCiDownloadRecordNum(int ciType, String from, String to) {
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_DOWNLOAD_RECORD ");
		sql.append("WHERE CI_TYPE = ? ");
		sql.append("AND DATE_FORMAT(DOWNLOAD_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(DOWNLOAD_DATE, '%Y-%m-%d') <= ? ");
		
		Object[] obj = new Object[] {ciType, 
				                     from,
				                     to};

		return  this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	
}
