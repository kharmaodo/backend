package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.ProjectDetailDAO;
import com.atosorigin.mice.km.rowmapper.ProjectDetailRowMapper;
import com.atosorigin.mice.km.vo.ProjectDetailVO;

public class ProjectDetailDAOImpl implements ProjectDetailDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(ProjectDetailVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PROJECT_DETAIL VALUES (?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {vo.getId(),
									 vo.getLocalizedDataId(),
									 vo.getDescription(),
									 vo.getContent(),
									 vo.getProjectId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<ProjectDetailVO> getProjectDetails(String projectId) {
		ProjectDetailRowMapper rm = new ProjectDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROJECT_DETAIL WHERE PROJECT_ID = ? ORDER BY ID");
		Object[] obj = new Object[] {projectId};
		List<ProjectDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		if(vos.size() > 0) {
			return vos;
		} else {
			return null;
		}
	}

	@Override
	public int getProjectDetailsNum(String projectId, String localizedDataId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM PROJECT_DETAIL WHERE PROJECT_ID = ? AND LOCALIZED_DATA_ID = ?");
		Object[] obj = new Object[]{projectId, localizedDataId};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
