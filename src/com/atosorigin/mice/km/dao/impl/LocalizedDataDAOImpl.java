package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.LocalizedDataDAO;
import com.atosorigin.mice.km.rowmapper.LocalizedDataRowMapper;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class LocalizedDataDAOImpl implements LocalizedDataDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(LocalizedDataVO ldvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO LOCALIZED_DATA (ID, ");
		sql.append("                            NAME, ");
		sql.append("                            LOCALE_NAME) ");
		sql.append("VALUES (?,?,?)");
		Object[] obj = new Object[] {ldvo.getId(),
									 ldvo.getName(),
									 ldvo.getLocaleName()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}


	@Override
	public int update(LocalizedDataVO ldvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE LOCALIZED_DATA NAME = ?, ");
		sql.append("                      LOCALE_NAME = ?) ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {ldvo.getName(),
									 ldvo.getLocaleName(),
									 ldvo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<LocalizedDataVO> getLocalizeData(String id) {
		StringBuilder sql = new StringBuilder();
		LocalizedDataRowMapper ldrm = new LocalizedDataRowMapper();
		sql.append("SELECT L.ID, ");
		sql.append("       L.NAME, ");
		sql.append("       L.LOCALE_NAME "); 
		sql.append("FROM CATEGORY_LOCALIZED_DATA C, ");
		sql.append("     LOCALIZED_DATA L ");
		sql.append("WHERE C.CATEGORY_ID = ? ");
		sql.append("AND   L.ID = C.LOCALIZED_DATA_ID");

		Object[] obj = new Object[] {id};
		List<LocalizedDataVO> ldvos = this.jdbcTemplate.query(sql.toString(), obj, ldrm);
		return (ldvos.size() == 0) ? null : ldvos;
	}
	
	@Override
	public int delete(String localizedDataId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM LOCALIZED_DATA WHERE ID = ?");
		Object[] obj = new Object[] {localizedDataId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<LocalizedDataVO> getLocalizeDataLike(String id) {
		id = "%" + id + "%";
		StringBuilder sql = new StringBuilder();
		LocalizedDataRowMapper ldrm = new LocalizedDataRowMapper();
		sql.append("SELECT ID, ");
		sql.append("       NAME, ");
		sql.append("       LOCALE_NAME "); 
		sql.append("FROM LOCALIZED_DATA ");
		sql.append("WHERE ID LIKE ? ");
		sql.append("ORDER BY ID");

		Object[] obj = new Object[] {id};
		List<LocalizedDataVO> ldvos = this.jdbcTemplate.query(sql.toString(), obj, ldrm);
		return (ldvos.size() == 0) ? null : ldvos;
	}
	
	@Override
	public List<LocalizedDataVO> getLocalizeDataLike(String id, String locale) {
		id = "%" + id + "%";
		StringBuilder sql = new StringBuilder();
		LocalizedDataRowMapper ldrm = new LocalizedDataRowMapper();
		sql.append("SELECT ID, ");
		sql.append("       NAME, ");
		sql.append("       LOCALE_NAME "); 
		sql.append("FROM LOCALIZED_DATA ");
		sql.append("WHERE ID LIKE ? ");
		sql.append("AND LOCALE_NAME = ? ");
		sql.append("ORDER BY ID");

		Object[] obj = new Object[] {id, locale};
		List<LocalizedDataVO> ldvos = this.jdbcTemplate.query(sql.toString(), obj, ldrm);
		return (ldvos.size() == 0) ? null : ldvos;
	}

}
