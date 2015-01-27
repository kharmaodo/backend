package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.PressReleaseDetailDAO;
import com.atosorigin.mice.km.rowmapper.ReleaseRowDetailMapper;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class PressReleaseDetailDAOImpl implements PressReleaseDetailDAO {

private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(PressReleaseDetailVO pressReleaseDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PRESS_RELEASE_DETAIL (ID, ");
		sql.append("					  	   VISIBLE, ");
		sql.append("					  	   TOPIC, ");
		sql.append("					  	   CONTENT, ");
		sql.append("					       LOCALE, ");
		sql.append("					  	   PRESS_RELEASE_ID) ");
		sql.append("VALUES (?,?,?,?,?,?)");
		Object[] obj = new Object[]{pressReleaseDetailVO.getId(),
									pressReleaseDetailVO.getVisible(),
									pressReleaseDetailVO.getTopic(),
									pressReleaseDetailVO.getContent(),
									pressReleaseDetailVO.getLocale(),
									pressReleaseDetailVO.getPressReleaseId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	
	@Override
	public int delete(String pressReleaseId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM PRESS_RELEASE_DETAIL WHERE PRESS_RELEASE_ID = ?");
		Object[] obj = new Object[]{pressReleaseId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	


	@Override
	public List<PressReleaseDetailVO> getPressReleaseDetails(String pressReleaseId) {
		StringBuilder sql = new StringBuilder();
		ReleaseRowDetailMapper rm = new ReleaseRowDetailMapper();
		sql.append("SELECT * FROM PRESS_RELEASE_DETAIL WHERE PRESS_RELEASE_ID = ?");
		Object[] obj = new Object[] {pressReleaseId};
		List<PressReleaseDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		if(vos.size() == 0) {
			return null;
		} else {
			return vos;
		}
	}

}
