package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.CaseStudyCategoryDAO;
import com.atosorigin.mice.km.rowmapper.CaseStudyCategoryRowMapper;
import com.atosorigin.mice.km.vo.CaseStudyCategoryVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class CaseStudyCategoryDAOImpl implements CaseStudyCategoryDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CaseStudyCategoryVO> getCaseStudyCategoryVOs() {
		CaseStudyCategoryRowMapper cscrm = new CaseStudyCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY_CATEGORY ORDER BY ID");
		List<CaseStudyCategoryVO> caseStudyCategoryVOs = this.jdbcTemplate.query(sql.toString(), cscrm);

		return (caseStudyCategoryVOs.size() > 0) ? caseStudyCategoryVOs : null;
	}

}
