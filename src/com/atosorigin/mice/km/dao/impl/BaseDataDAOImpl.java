package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.BaseDataDAO;
import com.atosorigin.mice.km.rowmapper.EventRowMapper;
import com.atosorigin.mice.km.vo.EventVO;

public class BaseDataDAOImpl implements BaseDataDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(List baseData) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO base_data (id, region_id, nationality, name, position, born, age, apps, ");
		sql.append("goal_for, ag, yellow_card, yellow_2nd_card, red_card) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[] {baseData.get(0),
									 baseData.get(1),
									 baseData.get(2),
									 baseData.get(3),
									 baseData.get(4),
									 baseData.get(5),
									 baseData.get(6),
									 baseData.get(8),
									 baseData.get(9),
									 baseData.get(10),
									 baseData.get(11),
									 baseData.get(12),
									 baseData.get(13)};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	
}
