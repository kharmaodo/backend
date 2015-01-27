package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.WiflyDAO;
import com.atosorigin.mice.km.rowmapper.WiflyRowMapper;
import com.atosorigin.mice.km.vo.WiflyVO;

public class WiflyDAOImpl implements WiflyDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public WiflyVO getNotUse() {
		WiflyRowMapper wrm = new WiflyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM WIFLY WHERE CARD_USER_ID IS NULL");
		List<WiflyVO> wvos = this.jdbcTemplate.query(sql.toString(), wrm);
		if(wvos.size() > 0) {
			return wvos.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(WiflyVO wvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE WIFLY SET CARD_USER_ID = ?, "); 
		sql.append("			     APPLIED_DATE = ? ");
		sql.append("WHERE ACCOUNT = ? ");
				            
		Object[] obj = new Object[] {wvo.getCardUserId(),
									 wvo.getAppliedDate(),
									 wvo.getAccount()};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

}
