package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.IndustryNewsDAO;
import com.atosorigin.mice.km.rowmapper.IndustryNewsRowMapper;
import com.atosorigin.mice.km.vo.IndustryNewsVO;

public class IndustryNewsDAOImpl implements IndustryNewsDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(IndustryNewsVO industryNewsVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO INDUSTRY_NEWS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[] {industryNewsVO.getId(),
									 industryNewsVO.getDescription(), 
									 industryNewsVO.getUrl(), 
									 industryNewsVO.getSource(), 
									 industryNewsVO.getPublishDate(),
									 industryNewsVO.getShelveDate(), 
									 industryNewsVO.getUnshelveDate(), 
									 industryNewsVO.getShowPlace(), 
									 industryNewsVO.getActivated(),
									 industryNewsVO.getVerified(), 
									 industryNewsVO.getCreator(), 
									 industryNewsVO.getCreateDate(), 
									 industryNewsVO.getModifier(),
									 industryNewsVO.getModifyDate(), 
									 industryNewsVO.getVerifier(), 
									 industryNewsVO.getVerifyDate(), 
									 industryNewsVO.getVerifyNote(),
									 industryNewsVO.getOwnerId(), 
									 industryNewsVO.getPhoto(), 
									 industryNewsVO.getIndustryNewsCategoryId(),
									 null,
									 null,
									 null};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(IndustryNewsVO industryNewsVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE INDUSTRY_NEWS SET DESCRIPTION = ?, ");
		sql.append("                         URL = ?, ");
		sql.append("                         SOURCE = ?, ");
		sql.append("                         PUBLISH_DATE = ?, ");
		sql.append("                         SHELVE_DATE = ?, ");
		sql.append("                         UNSHELVE_DATE = ?, ");
		sql.append("                         SHOW_PLACE = ?, ");
		sql.append("                         ACTIVATED = ?, ");
		sql.append("                         VERIFIED = ?, ");
		sql.append("                         CREATOR = ?, ");
		sql.append("                         CREATE_DATE = ?, ");
		sql.append("                         MODIFIER = ?, ");
		sql.append("                         MODIFY_DATE = ?, ");
		sql.append("                         VERIFIER = ?, ");
		sql.append("                         VERIFY_DATE = ?, ");
		sql.append("                         VERiFY_NOTE = ?, ");
		sql.append("                         OWNER_ID = ?, ");
		sql.append("                         PHOTO = ?, ");
		sql.append("                         INDUSTRY_NEWS_CATEGORY_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {industryNewsVO.getDescription(), 
									 industryNewsVO.getUrl(), 
									 industryNewsVO.getSource(), 
									 industryNewsVO.getPublishDate(),
									 industryNewsVO.getShelveDate(), 
									 industryNewsVO.getUnshelveDate(), 
									 industryNewsVO.getShowPlace(), 
									 industryNewsVO.getActivated(),
									 industryNewsVO.getVerified(), 
									 industryNewsVO.getCreator(), 
									 industryNewsVO.getCreateDate(), 
									 industryNewsVO.getModifier(),
									 industryNewsVO.getModifyDate(), 
									 industryNewsVO.getVerifier(), 
									 industryNewsVO.getVerifyDate(), 
									 industryNewsVO.getVerifyNote(),
									 industryNewsVO.getOwnerId(), 
									 industryNewsVO.getPhoto(), 
									 industryNewsVO.getIndustryNewsCategoryId(),
									 industryNewsVO.getId()};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<IndustryNewsVO> getIndustryNews(String description, int startPosition, int pageRows) {
		description = "%" + description + "%";
		IndustryNewsRowMapper rm = new IndustryNewsRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM INDUSTRY_NEWS ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		sql.append("ORDER BY CREATE_DATE DESC LIMIT ?, ?");
		Object[] obj = new Object[]{description, startPosition, pageRows};
		List<IndustryNewsVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getIndustryNewsNum(String description) {
		description = "%" + description + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM INDUSTRY_NEWS ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		Object[] obj = new Object[]{description};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public List<IndustryNewsVO> getIndustryNews(int startPosition, int pageRows) {
		IndustryNewsRowMapper rm = new IndustryNewsRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM INDUSTRY_NEWS ");
		sql.append("ORDER BY CREATE_DATE DESC LIMIT ?, ?");
		Object[] obj = new Object[]{startPosition, pageRows};
		List<IndustryNewsVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getIndustryNewsNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM INDUSTRY_NEWS ");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public IndustryNewsVO getIndustryNews(String id) {
		IndustryNewsRowMapper rm = new IndustryNewsRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM INDUSTRY_NEWS WHERE ID = ?");
		Object[] obj = new Object[]{id};
		List<IndustryNewsVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

	@Override
	public List<IndustryNewsVO> getIndustryNewsForApprove(int startPosition, int pageRows) {
		IndustryNewsRowMapper rm = new IndustryNewsRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM INDUSTRY_NEWS WHERE CREATOR = 'Guest' AND ACTIVATED IS NULL ORDER BY CREATE_DATE LIMIT ?, ?");
		Object[] obj = new Object[]{startPosition, pageRows};
		List<IndustryNewsVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getIndustryNewsForApproveNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM INDUSTRY_NEWS WHERE CREATOR = 'Guest' AND ACTIVATED IS NULL");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}
	
	@Override
	public int getPhotoNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM INDUSTRY_NEWS WHERE DATE_FORMAT(CREATE_DATE, '%Y-%m-%d') = CURDATE() AND PHOTO IS NOT NULL");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

}
