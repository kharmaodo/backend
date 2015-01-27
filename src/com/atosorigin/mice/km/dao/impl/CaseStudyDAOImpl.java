package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.CaseStudyBean;
import com.atosorigin.mice.km.dao.CaseStudyCategoryDAO;
import com.atosorigin.mice.km.dao.CaseStudyDAO;
import com.atosorigin.mice.km.rowmapper.CaseStudyDetailRowMapper;
import com.atosorigin.mice.km.rowmapper.CaseStudyRowMapper;
import com.atosorigin.mice.km.rowmapper.VideoRowMapper;
import com.atosorigin.mice.km.vo.CaseStudyCategoryVO;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;
import com.atosorigin.mice.km.vo.CaseStudyVO;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class CaseStudyDAOImpl implements CaseStudyDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public CaseStudyVO getCaseStudyVO(String id) {
		CaseStudyRowMapper csrm = new CaseStudyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<CaseStudyVO> caseStudyVOs = this.jdbcTemplate.query(sql.toString(), obj, csrm);
		
		return (caseStudyVOs.size() > 0) ? caseStudyVOs.get(0) : null;
	}

	@Override
	public List<CaseStudyVO> getCaseStudyVOs(int startPosition, int pageRows) {
		CaseStudyRowMapper csrm = new CaseStudyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY ");
		sql.append("ORDER BY START_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {startPosition, pageRows};
		List<CaseStudyVO> caseStudyVOs = this.jdbcTemplate.query(sql.toString(), obj, csrm);

		return (caseStudyVOs.size() > 0) ? caseStudyVOs : null;
	}
	
	@Override
	public int getCaseStudyVOsNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CASE_STUDY ");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}
	
	@Override
	public List<CaseStudyVO> getCaseStudyVOsByCaseStudyCategoryId(
			String caseStudyCategoryId, int startPosition, int pageRows) {
		CaseStudyRowMapper csrm = new CaseStudyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY ");
		sql.append("WHERE CASE_STUDY_CATEGORY_ID = ? ");
		sql.append("ORDER BY START_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {caseStudyCategoryId, startPosition, pageRows};
		List<CaseStudyVO> caseStudyVOs = this.jdbcTemplate.query(sql.toString(), obj, csrm);

		return (caseStudyVOs.size() > 0) ? caseStudyVOs : null;
	}

	@Override
	public int getCaseStudyVOsByCaseStudyCategoryIdNum(
			String caseStudyCategoryId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CASE_STUDY ");
		sql.append("WHERE CASE_STUDY_CATEGORY_ID = ? ");
		Object[] obj = new Object[] {caseStudyCategoryId};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<CaseStudyVO> getCaseStudyVOsByDescription(String description,
			int startPosition, int pageRows) {
		description = "%" + description + "%";
		CaseStudyRowMapper csrm = new CaseStudyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		sql.append("ORDER BY START_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {description, startPosition, pageRows};
		List<CaseStudyVO> caseStudyVOs = this.jdbcTemplate.query(sql.toString(), obj, csrm);

		return (caseStudyVOs.size() > 0) ? caseStudyVOs : null;
	}

	@Override
	public int getCaseStudyVOsByDescriptionNum(String description) {
		description = "%" + description + "%";
		CaseStudyRowMapper csrm = new CaseStudyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CASE_STUDY ");
		sql.append("WHERE DESCRIPTION LIKE ?");
		Object[] obj = new Object[] {description};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<CaseStudyVO> getCaseStudyVOs(String description,
			String caseStudyCategoryId, int startPosition, int pageRows) {
		description = "%" + description + "%";
		CaseStudyRowMapper csrm = new CaseStudyRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CASE_STUDY ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		sql.append("AND CASE_STUDY_CATEGORY_ID = ? ");
		sql.append("ORDER BY START_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {description, caseStudyCategoryId, startPosition, pageRows};
		List<CaseStudyVO> caseStudyVOs = this.jdbcTemplate.query(sql.toString(), obj, csrm);

		return (caseStudyVOs.size() > 0) ? caseStudyVOs : null;
	}

	@Override
	public int getCaseStudyVOsNum(String description, String caseStudyCategoryId) {
		description = "%" + description + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CASE_STUDY ");
		sql.append("WHERE DESCRIPTION LIKE ? ");
		sql.append("AND CASE_STUDY_CATEGORY_ID = ? ");
		Object[] obj = new Object[] {description, caseStudyCategoryId};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public int insert(CaseStudyVO caseStudyVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CASE_STUDY VALUES (?, ?, ?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {caseStudyVO.getId(),
									 caseStudyVO.getDescription(),
									 caseStudyVO.getPhoto(),
									 caseStudyVO.getRank(),
									 caseStudyVO.getStartDate(),
									 caseStudyVO.getEndDate(),
									 caseStudyVO.getCaseStudyCategoryId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	/*
	@Override
	public int update(CaseStudyVO caseStudyVO,
			List<CaseStudyDetailVO> caseStudyDetailVos) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		delete(caseStudyVO.getId());
			int rowC = this.update(caseStudyVO);
			int rowCD = 0;
			for(CaseStudyDetailVO caseStudyDetailVo : caseStudyDetailVos) {
				rowCD += this.insert(caseStudyDetailVo);
			}
			if(rowC == 1 && rowCD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}
	*/
		
	public int update(CaseStudyVO caseStudyVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CASE_STUDY SET DESCRIPTION = ?, ");
		sql.append("                      PHOTO = ?, ");
		sql.append("                      RANK = ?, ");
		sql.append("                      START_DATE = ?, ");
		sql.append("                      END_DATE = ?, ");
		sql.append("                      CASE_STUDY_CATEGORY_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {caseStudyVO.getDescription(),
									 caseStudyVO.getPhoto(),
									 caseStudyVO.getRank(),
									 caseStudyVO.getStartDate(),
									 caseStudyVO.getEndDate(),
									 caseStudyVO.getCaseStudyCategoryId(),
									 caseStudyVO.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

}
