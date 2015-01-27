package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.IndustryNewsCategoryDAO;
import com.atosorigin.mice.km.rowmapper.IndustryNewsCategoryRowMapper;
import com.atosorigin.mice.km.vo.IndustryNewsCategoryVO;

public class IndustryNewsCategoryDAOImpl implements IndustryNewsCategoryDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<IndustryNewsCategoryVO> getIndustryNewsCategorys() {
		IndustryNewsCategoryRowMapper rm = new IndustryNewsCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM INDUSTRY_NEWS_CATEGORY WHERE ID LIKE 'CID%'");
		List<IndustryNewsCategoryVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		return vos.size()>0 ? vos : null;
	}

}
