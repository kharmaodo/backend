package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.RegionCategoryDAO;
import com.atosorigin.mice.km.rowmapper.RegionCategoryRowMapper;
import com.atosorigin.mice.km.vo.RegionCategoryVO;

public class RegionCategoryDAOImpl implements RegionCategoryDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public RegionCategoryVO getRegionCategory(String id) {
		RegionCategoryRowMapper rm = new RegionCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM REGION_CATEGORY WHERE ID = ?");
		Object[] obj = new Object[]{id};
		List<RegionCategoryVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

	@Override
	public List<RegionCategoryVO> getRegionCategories(int level) {
		RegionCategoryRowMapper rm = new RegionCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM REGION_CATEGORY WHERE LEVEL = ?");
		Object[] obj = new Object[]{level};
		List<RegionCategoryVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public List<RegionCategoryVO> getRegionCategories(String parentId) {
		RegionCategoryRowMapper rm = new RegionCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM REGION_CATEGORY WHERE PARENT_ID = ?");
		Object[] obj = new Object[]{parentId};
		List<RegionCategoryVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

}
