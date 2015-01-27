package com.atosorigin.mice.km.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.CategoryLocalizedDataDAO;
import com.atosorigin.mice.km.vo.CategoryLocalizedDataVO;

public class CategoryLocalizedDataDAOImpl implements CategoryLocalizedDataDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(CategoryLocalizedDataVO categoryLocalizedDataVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CATEGORY_LOCALIZED_DATA (CATEGORY_ID, ");
		sql.append("                                     LOCALIZED_DATA_ID) ");
		sql.append("VALUES (?,?)");
		Object[] obj = new Object[] {categoryLocalizedDataVO.getCategoryId(),
									 categoryLocalizedDataVO.getLocalizedDataId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(CategoryLocalizedDataVO categoryLocalizedDataVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int delete(String localizedDataId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CATEGORY_LOCALIZED_DATA WHERE LOCALIZED_DATA_ID = ?");
		Object[] obj = new Object[] {localizedDataId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

}
