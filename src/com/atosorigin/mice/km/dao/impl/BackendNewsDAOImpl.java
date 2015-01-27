package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.BackendNewsDAO;
import com.atosorigin.mice.km.rowmapper.BackendNewsRowMapper;
import com.atosorigin.mice.km.vo.BackendNewsVO;

public class BackendNewsDAOImpl implements BackendNewsDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insert(BackendNewsVO bnvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO BACKEND_NEWS (CONTENT, GROUP_IDS, CREATE_TIME, MODIFY_TIME) VALUES (?,?,?,?)");
		Object[] obj = new Object[] {bnvo.getContent(),
									 bnvo.getGroupIds(),
									 bnvo.getCreateTime(),
									 bnvo.getModifyTime()};
		
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		return row;
	}
	
	public int update(BackendNewsVO bnvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BACKEND_NEWS SET CONTENT = ?, ");
		sql.append("                        GROUP_IDS = ?, ");
		sql.append("                        CREATE_TIME = ?, ");
		sql.append("                        MODIFY_TIME = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {bnvo.getContent(),
									 bnvo.getGroupIds(),
									 bnvo.getCreateTime(),
									 bnvo.getModifyTime(),
									 bnvo.getId()};
		
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("BACKEND_NEWS : update " + row + " row(s):ID = " + bnvo.getId());
		return row;
	}
	
	
	public int delete(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM BACKEND_NEWS WHERE ID = ? ");
		Object[] obj = new Object[] {id};
		
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("BACKEND_NEWS : delete " + row + " row(s):ID = " + id);
		return row;
	}
	
	
	public List<BackendNewsVO> getBackendNews(int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		BackendNewsRowMapper bnrm = new BackendNewsRowMapper();
		sql.append("SELECT * FROM BACKEND_NEWS ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {startPosition,
									 pageRows};
		List<BackendNewsVO> bnvos = this.jdbcTemplate.query(sql.toString(), obj, bnrm);
		if(bnvos == null) {
			return null;
		} else {
			return bnvos;
		}
	}
	
	public int getBackendNewsNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM BACKEND_NEWS");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public BackendNewsVO getBackendNews(int id) {
		StringBuilder sql = new StringBuilder();
		BackendNewsRowMapper bnrm = new BackendNewsRowMapper();
		sql.append("SELECT * FROM BACKEND_NEWS WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<BackendNewsVO> bnvos = this.jdbcTemplate.query(sql.toString(), obj, bnrm);
		if(bnvos == null) {
			return null;
		} else {
			return bnvos.get(0);
		}
	}

}
