package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.VendorCategoryDAO;
import com.atosorigin.mice.km.rowmapper.VendorCategoryRowMapper;
import com.atosorigin.mice.km.vo.VendorCategoryVO;

public class VendorCategoryDAOImpl implements VendorCategoryDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public VendorCategoryVO getById(String id) {
		VendorCategoryRowMapper vcrm = new VendorCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENDOR_CATEGORY WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<VendorCategoryVO> vcvos = this.jdbcTemplate.query(sql.toString(), obj, vcrm);
		if(vcvos.size() > 0) {
			return  vcvos.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<VendorCategoryVO> getByLevelIndex(int levelIndex) {
		VendorCategoryRowMapper vcrm = new VendorCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENDOR_CATEGORY WHERE LEVEL_INDEX = ?");
		Object[] obj = new Object[] {levelIndex};
		return this.jdbcTemplate.query(sql.toString(), obj, vcrm);
	}

	@Override
	public int insert(VendorCategoryVO vcvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO VENDOR_CATEGORY VALUES (?,?,?,?)");
		Object[] obj = new Object[] {vcvo.getId(),
					 				 vcvo.getDescription(),
					 				 vcvo.getLevelIndex(),
					 				 vcvo.getParentId()};
		int row = this.jdbcTemplate.update(sql.toString(), obj);
		logger.debug("DOCUMENT_CATEGORY:insert " + row + " row(s):Description = " + vcvo.getDescription());
		return row;
	}

	@Override
	public int update(VendorCategoryVO vcvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE VENDOR_CATEGORY SET DESCRIPTION = ?, ");
		sql.append("                           LEVEL_INDEX = ?, ");
		sql.append("                           PARENT_ID = ?, ");
		sql.append("                           LEVEL_INDEX = ?, ");
        sql.append("WHERE ID = ? ");
		Object[] obj = new Object[] {vcvo.getDescription(),
					 				 vcvo.getLevelIndex(),
					 				 vcvo.getParentId(),
					 				 vcvo.getId()};
		int row = this.jdbcTemplate.update(sql.toString(), obj);
		logger.debug("DOCUMENT_CATEGORY:update " + row + " row(s):ID = " + vcvo.getId());
		return row;
	}
	
	@Override
	public List<VendorCategoryVO> getByLevelIndex(int levelIndex, String parentId) {
		VendorCategoryRowMapper vcrm = new VendorCategoryRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENDOR_CATEGORY WHERE LEVEL_INDEX = ? ");
		if(levelIndex > 1) {
			sql.append("AND PARENT_ID = ? ");
		}
		sql.append("ORDER BY ID DESC");
		Object[] obj = new Object[] {levelIndex};
		return this.jdbcTemplate.query(sql.toString(), obj, vcrm);
	}

}
