package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.CaseStudyDetailDAO;
import com.atosorigin.mice.km.rowmapper.CaseStudyDetailRowMapper;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;

public class CaseStudyDetailDAOImpl implements CaseStudyDetailDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(CaseStudyDetailVO caseStudyDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CASE_STUDY_DETAIL VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {caseStudyDetailVO.getId(),
									 caseStudyDetailVO.getVisible(),
									 caseStudyDetailVO.getTitle(),
									 caseStudyDetailVO.getUrl(),
									 caseStudyDetailVO.getLocation(),
									 caseStudyDetailVO.getOrganizer(),
									 caseStudyDetailVO.getShortDescription(),
									 caseStudyDetailVO.getLocale(),
									 caseStudyDetailVO.getContent(),
									 caseStudyDetailVO.getCaseStudyId(),
									 caseStudyDetailVO.getYoutubeId(),
									 caseStudyDetailVO.getAttendee()};
							
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(CaseStudyDetailVO caseStudyDetailVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CASE_STUDY_DETAIL SET VISIBLE = ?, ");
		sql.append("                             TITLE = ?, ");
		sql.append("                             URL = ?, ");
		sql.append("                             LOCATION = ?, ");
		sql.append("                             ORGANIZER = ?, ");
		sql.append("                             SHORT_DESCRIPTION = ?, ");
		sql.append("                             LOCALE = ?, ");
		sql.append("                             CONTENT = ?, ");
		sql.append("                             CASE_STUDY_ID = ?, ");
		sql.append("                             YOUTUBE_ID = ?, ");
		sql.append("                             ATTENDEE = ? ");
		sql.append("WHERE ID = ?"); 
		Object[] obj = new Object[] {caseStudyDetailVO.getVisible(),
									 caseStudyDetailVO.getTitle(),
									 caseStudyDetailVO.getUrl(),
									 caseStudyDetailVO.getLocation(),
									 caseStudyDetailVO.getOrganizer(),
									 caseStudyDetailVO.getShortDescription(),
									 caseStudyDetailVO.getLocale(),
									 caseStudyDetailVO.getContent(),
									 caseStudyDetailVO.getCaseStudyId(),
									 caseStudyDetailVO.getYoutubeId(),
									 caseStudyDetailVO.getAttendee(),
									 caseStudyDetailVO.getId()};
							
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int delete(String caseStudyId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CASE_STUDY_DETAIL WHERE CASE_STUDY_ID = ? ");
		Object[] obj = new Object[] {caseStudyId};
							
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public CaseStudyDetailVO getCaseStudyDetailVO(String id) {
		CaseStudyDetailRowMapper csdrm = new CaseStudyDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY_DETAIL WHERE CASE_STUDY_ID = ?");
		Object[] obj = new Object[] {id};
		List<CaseStudyDetailVO> caseStudyDetailVOs = this.jdbcTemplate.query(sql.toString(), obj, csdrm);
		
		return (caseStudyDetailVOs.size() > 0) ? caseStudyDetailVOs.get(0) : null;
	}

	@Override
	public List<CaseStudyDetailVO> getCaseStudyDetailVOs(String caseStudyId) {
		CaseStudyDetailRowMapper csdrm = new CaseStudyDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY_DETAIL WHERE CASE_STUDY_ID = ?");
		Object[] obj = new Object[] {caseStudyId};
		List<CaseStudyDetailVO> caseStudyDetailVOs = this.jdbcTemplate.query(sql.toString(), obj, csdrm);
		
		return (caseStudyDetailVOs.size() > 0) ? caseStudyDetailVOs : null;
	}

}
