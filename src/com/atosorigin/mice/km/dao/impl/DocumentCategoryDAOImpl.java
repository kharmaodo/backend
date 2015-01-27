package com.atosorigin.mice.km.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.DocumentCategoryDAO;
import com.atosorigin.mice.km.rowmapper.DocumentCategoryRowMapper;
import com.atosorigin.mice.km.rowmapper.LocalizedDataRowMapper;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class DocumentCategoryDAOImpl implements DocumentCategoryDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * CATEGORY_GROUP   1/5/10 = 一般大眾/登入會員/計劃成員
	 */
	
	@Override
	public int insert(DocumentCategoryVO dcvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DOCUMENT_CATEGORY (ID, ");
		sql.append("                               DESCRIPTION, ");
		sql.append("                               LEVEL_INDEX, ");
		sql.append("                               CATEGORY_GROUP, ");
		sql.append("                               RANK, ");
		sql.append("                               PARENT_ID) ");
		sql.append("VALUES (?,?,?,?,?,?)");
		Object[] obj = new Object[] {dcvo.getId(),
									 dcvo.getDescription(),
									 dcvo.getLevelIndex(),
									 dcvo.getCategoryGroupId(),
									 dcvo.getRank(),
									 dcvo.getParentId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	@Override
	public int update(DocumentCategoryVO dcvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DOCUMENT_CATEGORY SET DESCRIPTION = ?, ");
		sql.append("                             LEVEL_INDEX = ?, ");
		sql.append("                             CATEGORY_GROUP = ?, ");
		sql.append("                             RANK = ?, ");
		sql.append("                             PARENT_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {dcvo.getDescription(),
									 dcvo.getLevelIndex(),
									 dcvo.getCategoryGroupId(),
									 dcvo.getRank(),
									 dcvo.getParentId(),
									 dcvo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	private int getLocalizedDataNum(String id, String localeName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM LOCALIZED_DATA ");
		sql.append("WHERE ID = ? AND LOCALE_NAME = ?");
		Object[] obj = new Object[] {id,
									 localeName};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	

	@Override
	public List<DocumentCategoryVO> getDocumentCategory(String description, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocumentCategoryRowMapper dcrm = new DocumentCategoryRowMapper();
		sql.append("SELECT ID, ");
		sql.append("       DESCRIPTION, ");
		sql.append("       CATEGORY_GROUP, ");
		sql.append("       RANK, ");
		sql.append("       LEVEL_INDEX, ");
		sql.append("       PARENT_ID ");
		sql.append("FROM DOCUMENT_CATEGORY ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		sql.append("LIMIT ?, ?");
		
		description = "%" + description + "%";
		Object[] obj = new Object[] {description,
				                     startPosition,
				                     Constants.PAGE_ROWS};
		
		List<DocumentCategoryVO> dcvos = this.jdbcTemplate.query(sql.toString(), obj, dcrm);
		if(dcvos.size() == 0) {
			return null;
		} else {
			return dcvos;
		}
	}

	@Override
	public int getDocumentCategoryNum(String description) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM DOCUMENT_CATEGORY ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		
		description = "%" + description + "%";
		Object[] obj = new Object[] {description};
		
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public DocumentCategoryVO getDocumentCategory(String id) {
		DocumentCategoryVO result = new DocumentCategoryVO();
		StringBuilder sql = new StringBuilder();
		DocumentCategoryRowMapper dcrm = new DocumentCategoryRowMapper();
		sql.append("SELECT ID, ");
		sql.append("       DESCRIPTION, ");
		sql.append("       CATEGORY_GROUP, ");
		sql.append("       RANK, ");
		sql.append("       LEVEL_INDEX, ");
		sql.append("       PARENT_ID ");
		sql.append("FROM DOCUMENT_CATEGORY ");
		sql.append("WHERE ID = ? ");
		
		Object[] obj = new Object[] {id};
		
		List<DocumentCategoryVO> dcvos = this.jdbcTemplate.query(sql.toString(), obj, dcrm);
		if(dcvos.size() == 0) {
			return null;
		} else {
			return dcvos.get(0);
		}
	}

	@Override
	public List<DocumentCategoryVO> getParent() {
		StringBuilder sql = new StringBuilder();
		DocumentCategoryRowMapper dcrm = new DocumentCategoryRowMapper();
		sql.append("SELECT * FROM DOCUMENT_CATEGORY WHERE PARENT_ID IS NULL");
		List<DocumentCategoryVO> dcvos = this.jdbcTemplate.query(sql.toString(), dcrm);
		if(dcvos.size() == 0) {
			return null;
		} else {
			return dcvos;
		}
	}

	@Override
	public List<DocumentCategoryVO> getDocumentCategoryByParent(String parentId) {
		StringBuilder sql = new StringBuilder();
		DocumentCategoryRowMapper dcrm = new DocumentCategoryRowMapper();
		sql.append("SELECT * FROM DOCUMENT_CATEGORY WHERE PARENT_ID = ?");
		Object[] obj = new Object[] {parentId};
		List<DocumentCategoryVO> dcvos = this.jdbcTemplate.query(sql.toString(), obj, dcrm);
		if(dcvos.size() == 0) {
			return null;
		} else {
			return dcvos;
		}
	}


}
