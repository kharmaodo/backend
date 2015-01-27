package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.IndustryNewsDetailDAO;
import com.atosorigin.mice.km.rowmapper.IndustryNewsDetailRowMapper;
import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;

public class IndustryNewsDetailDAOImpl implements IndustryNewsDetailDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(IndustryNewsDetailVO industryNewsDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO INDUSTRY_NEWS_DETAIL VALUES (?,?,?,?,?,?)");
		Object[] obj = new Object[] {industryNewsDetailVO.getId(),
									 industryNewsDetailVO.getVisible(),
									 industryNewsDetailVO.getTopic(),
									 industryNewsDetailVO.getContent(),
									 industryNewsDetailVO.getLocale(),
									 industryNewsDetailVO.getIndustryNewsId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int delete(String industryNewsId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM INDUSTRY_NEWS_DETAIL WHERE INDUSTRY_NEWS_ID = ?");
		Object[] obj = new Object[]{industryNewsId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<IndustryNewsDetailVO> getIndustryNewsDetails(String industryNewsId) {
		IndustryNewsDetailRowMapper rm = new IndustryNewsDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM INDUSTRY_NEWS_DETAIL WHERE INDUSTRY_NEWS_ID = ?");
		Object[] obj = new Object[]{industryNewsId};
		List<IndustryNewsDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

}
