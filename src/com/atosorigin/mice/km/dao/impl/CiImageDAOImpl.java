package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.CiImageDAO;
import com.atosorigin.mice.km.rowmapper.CiImageRowMapper;
import com.atosorigin.mice.km.vo.CiImageVO;

public class CiImageDAOImpl implements CiImageDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CiImageVO> getCiImages(String serialNumber) {
		CiImageRowMapper cirm = new CiImageRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_IMAGE ");
		sql.append("WHERE SERIAL_NUMBER = ?");
		Object[] obj = new Object[] {serialNumber};

		List<CiImageVO> civos = this.jdbcTemplate.query(sql.toString(), obj, cirm);
		if(civos.size() == 0) {
			return null;
		}
		
		return civos;
	}

}
