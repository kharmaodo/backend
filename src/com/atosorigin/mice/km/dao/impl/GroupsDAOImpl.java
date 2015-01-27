package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.GroupsDAO;
import com.atosorigin.mice.km.rowmapper.GroupsRowMapper;
import com.atosorigin.mice.km.vo.GroupsVO;

public class GroupsDAOImpl implements GroupsDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<GroupsVO> getAll() {
		GroupsRowMapper grm = new GroupsRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM GROUPS ORDER BY ID");
		List<GroupsVO> gvos = this.getJdbcTemplate().query(sql.toString(), grm);
		if(gvos.size() == 0) {
			return null;
		} else {
			return gvos;
		}
	}

}
