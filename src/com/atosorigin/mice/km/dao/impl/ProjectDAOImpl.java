package com.atosorigin.mice.km.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.ProjectDAO;
import com.atosorigin.mice.km.rowmapper.ProjectRowMapper;
import com.atosorigin.mice.km.vo.ProjectDetailVO;
import com.atosorigin.mice.km.vo.ProjectVO;

public class ProjectDAOImpl implements ProjectDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public int insert(ProjectVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PROJECT VALUES (?, ?, ?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {vo.getId(),
									 vo.getMenuId(),
									 vo.getDescription(),
									 vo.getLocale(),
									 vo.getCreateTime(),
									 vo.getCreateBy(),
									 vo.getVisible()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	@Override
	public int update(ProjectVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PROJECT SET VISIBLE = ? WHERE ID = ?"); 
		Object[] obj = new Object[] {vo.getVisible(),
									 vo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public ProjectVO getProject(String menuId, String locale, Date createTime) {
		ProjectRowMapper rm = new ProjectRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROJECT WHERE MENU_ID = ? AND LOCALE = ? AND CREATE_TIME = ? AND VISIBLE = 'Y'");
		Object[] obj = new Object[] {menuId,
									 locale,
									 createTime};
		List<ProjectVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		if(vos.size() > 0) {
			return vos.get(0);
		} else {
			return null;
		}
	}


	@Override
	public List<ProjectVO> getProjects(String menuId, String locale, int startPosition, int pageRows) {
		ProjectRowMapper rm = new ProjectRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROJECT WHERE MENU_ID = ? AND LOCALE = ? AND VISIBLE = 'Y' ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {menuId,
				 					 locale,
				 					 startPosition,
				 					 pageRows};
		List<ProjectVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size() > 0 ? vos : null; 
	}
	
	@Override
	public int getProjectsNum(String menuId, String locale) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM PROJECT WHERE MENU_ID = ? AND LOCALE = ? AND VISIBLE = 'Y'");
		Object[] obj = new Object[] {menuId,
				 					 locale};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}


	@Override
	public ProjectVO getProject(String id) {
		ProjectRowMapper rm = new ProjectRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROJECT WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<ProjectVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size() > 0 ? vos.get(0) : null; 
	}


	@Override
	public ProjectVO getLatestProject() {
		ProjectRowMapper rm = new ProjectRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROJECT ORDER BY CREATE_TIME DESC");
		List<ProjectVO> vos = this.jdbcTemplate.query(sql.toString(), rm);
		
		if(vos.size() > 0) {
			return vos.get(0);
		} else {
			return null;
		}
	}

}
