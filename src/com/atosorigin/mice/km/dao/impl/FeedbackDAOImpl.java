package com.atosorigin.mice.km.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.FeedbackDAO;
import com.atosorigin.mice.km.rowmapper.FeedbackRowMapper;
import com.atosorigin.mice.km.rowmapper.VideoRowMapper;
import com.atosorigin.mice.km.vo.FeedbackVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class FeedbackDAOImpl implements FeedbackDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<FeedbackVO> getFeedbacks(Date from, Date to, String responseType, String keyword, int startPosition, int pageRows) {
		keyword = "%" + keyword + "%";
		FeedbackRowMapper rm = new FeedbackRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FEEDBACK ");
		sql.append("WHERE VISIBLE = 'Y' AND ");
		if("Y".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NOT NULL AND ");
		}
		if("N".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NULL AND ");
		}
		sql.append("CREATE_TIME >= ? AND ");
		sql.append("CREATE_TIME <= ? AND ");
		sql.append("(TOPIC LIKE ? OR CONTENT LIKE ? OR NAME LIKE ?) ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ?");
		
		Object[] obj = new Object[] {from,
				                     to,
				                     keyword,
				                     keyword,
				                     keyword,
				                     startPosition,
				                     pageRows};
	
		List<FeedbackVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getFeedbacksNum(Date from, Date to, String responseType, String keyword) {
		keyword = "%" + keyword + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM FEEDBACK ");
		sql.append("WHERE VISIBLE = 'Y' AND ");
		if("Y".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NOT NULL AND ");
		}
		if("N".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NULL AND ");
		}
		sql.append("CREATE_TIME >= ? AND ");
		sql.append("CREATE_TIME <= ? AND ");
		sql.append("(TOPIC LIKE ? OR CONTENT LIKE ? OR NAME LIKE ?) ");
		
		Object[] obj = new Object[] {from,
				                     to,
				                     keyword,
				                     keyword,
				                     keyword};
	
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public List<FeedbackVO> getFeedbacks(Date from, Date to, String responseType, int startPosition, int pageRows) {
		FeedbackRowMapper rm = new FeedbackRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FEEDBACK ");
		sql.append("WHERE VISIBLE = 'Y' AND ");
		if("Y".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NOT NULL AND ");
		}
		if("N".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NULL AND ");
		}
		sql.append("CREATE_TIME >= ? AND ");
		sql.append("CREATE_TIME <= ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ?");
		
		Object[] obj = new Object[] {from,
				                     to,
				                     startPosition,
				                     pageRows};
	
		List<FeedbackVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getFeedbacksNum(Date from, Date to, String responseType) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM FEEDBACK ");
		sql.append("WHERE VISIBLE = 'Y' AND ");
		if("Y".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NOT NULL AND ");
		}
		if("N".equals(responseType)) {
			sql.append("RESPONSE_TIME IS NULL AND ");
		}
		sql.append("CREATE_TIME >= ? AND ");
		sql.append("CREATE_TIME <= ? AND ");
		sql.append("(TOPIC LIKE ? OR CONTENT LIKE ? OR NAME LIKE ?) ");
		
		Object[] obj = new Object[] {from,
				                     to};
	
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public FeedbackVO getFeedback(int id) {
		FeedbackRowMapper rm = new FeedbackRowMapper();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM FEEDBACK WHERE ID = ?");
		
		Object[] obj = new Object[] {id};
	
		List<FeedbackVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		return vos.size() > 0 ? vos.get(0) : null;
	}
	
	@Override
	public int update(FeedbackVO feedbackVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE FEEDBACK SET CATEGORY = ?, ");
		sql.append("                    COUNTRY = ?, ");
		sql.append("                    NAME = ?, "); 
		sql.append("                    EMAIL = ?, ");
		sql.append("                    PHONE = ?, ");
		sql.append("					TOPIC = ?, ");
		sql.append("					CONTENT = ?, ");
		sql.append("				    RESPONSE_BY = ?, ");
		sql.append("					RESPONSE_TIME = ?, ");
		sql.append("					RESPONSE_CONTENT = ?, ");
		sql.append("					VISIBLE = ? ");
		sql.append("WHERE ID = ?");
		
		Object[] obj = new Object[] {feedbackVO.getCategory(),
									 feedbackVO.getCountry(),
									 feedbackVO.getName(),
									 feedbackVO.getEmail(),
									 feedbackVO.getPhone(),
									 feedbackVO.getTopic(),
									 feedbackVO.getContent(),
									 feedbackVO.getResponseBy(),
									 feedbackVO.getResponseTime(),
									 feedbackVO.getResponseContent(),
									 feedbackVO.getVisible(),
									 feedbackVO.getId()};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

}
