package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.DocumentDetailDAO;
import com.atosorigin.mice.km.rowmapper.DocumentCategoryRowMapper;
import com.atosorigin.mice.km.rowmapper.DocumentDetailRowMapper;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;

public class DocumentDetailDAOImpl implements DocumentDetailDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(DocumentDetailVO ddvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DOCUMENT_DETAIL (ID, ");
		sql.append("                             VISIBLE, ");
		sql.append("                             TOPIC, ");
		sql.append("                             SOURCE, ");
		sql.append("                             LOCALE, ");
		sql.append("                             DOCUMENT_ID) ");
		sql.append("VALUES (?,?,?,?,?,?)");
		Object[] obj = new Object[] {ddvo.getId(),
									 ddvo.getVisible(),
									 ddvo.getTopic(),
									 ddvo.getSource(),
									 ddvo.getLocale(),
									 ddvo.getDocumentId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(DocumentDetailVO documentDetailVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String documentId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM DOCUMENT_DETAIL WHERE DOCUMENT_ID = ?");
		Object[] obj = new Object[] {documentId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	

	@Override
	public List<DocumentDetailVO> getDocumentDetails(String documentId) {
		DocumentDetailRowMapper ddrm = new DocumentDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM DOCUMENT_DETAIL WHERE DOCUMENT_ID = ?");
		Object[] obj = new Object[] {documentId};
		List<DocumentDetailVO> ddvos = this.jdbcTemplate.query(sql.toString(), obj, ddrm);
		return ddvos;
	}

}
