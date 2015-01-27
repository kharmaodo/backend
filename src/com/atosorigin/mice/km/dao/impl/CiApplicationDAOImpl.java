package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.CiApplicationDAO;
import com.atosorigin.mice.km.rowmapper.CiApplicationRowMapper;
import com.atosorigin.mice.km.vo.CiApplicationVO;

public class CiApplicationDAOImpl implements CiApplicationDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CiApplicationVO getCiApplication(String id) {
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE ID = ? ");
		
		Object[] obj = new Object[] {id}; 

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos.get(0);
	}

	@Override
	public List<CiApplicationVO> getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, 
			                                 int approvalStatus, String sort, int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     approvalStatus,
				                     applyOrg,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	
	@Override
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus) {
		applyOrg = "%" + applyOrg + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     approvalStatus,
				                     applyOrg};

	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	
	@Override
	public int update(CiApplicationVO cavo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CI_APPLICATION SET APPROVAL_STATUS = ?, "); 
		sql.append("		                  APPROVED_DATE = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {cavo.getApprovedStatus(), 
                					 cavo.getApprovedDate(),
                					 cavo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<CiApplicationVO> getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, 
			                                 String sort, int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     applyOrg,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	
	@Override
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan, String applyOrg) {
		applyOrg = "%" + applyOrg + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     applyOrg};

	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, 
			int approvalStatus, String sort, int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {approvalStatus,
				                     applyOrg,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	@Override
	public int getCiApplicationsImgNum(String applyOrg, int approvalStatus) {
		applyOrg = "%" + applyOrg + "%"; 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		
		Object[] obj = new Object[] {approvalStatus,
				                     applyOrg};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
		
	}
	
	@Override
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, String sort,
			int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyOrg,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	@Override
	public int getCiApplicationsImgNum(String applyOrg) {
		applyOrg = "%" + applyOrg + "%"; 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		
		Object[] obj = new Object[] {applyOrg};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
		
	}

	@Override
	public int getForTask(String applyBoft, String applyMeetTaiwan) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION "); 
		sql.append("WHERE SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND APPLY_BOFT = ? "); 
		sql.append("AND APPROVAL_STATUS = 0 ");
		sql.append("AND (DELETED IS NULL OR DELETED = 'N')");
		
		Object[] obj = new Object[] {applyMeetTaiwan,
									 applyBoft};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public int getForTask() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION "); 
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND APPLY_BOFT = 'N' "); 
		sql.append("AND APPROVAL_STATUS = 0 ");
		sql.append("AND (DELETED IS NULL OR DELETED = 'N')");

		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public List<CiApplicationVO> getCiApplications(String applyBoft,
			String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to,
			String sort, int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     approvalStatus,
				                     applyOrg,
				                     from,
				                     to,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	@Override
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan,
			String applyOrg, int approvalStatus, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     approvalStatus,
				                     applyOrg,
				                     from,
				                     to};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public List<CiApplicationVO> getCiApplications(String applyBoft,
			String applyMeettaiwan, String applyOrg, String from, String to,
			String sort, int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     applyOrg,
				                     from,
				                     to,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	@Override
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan,
			String applyOrg, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     applyOrg,
				                     from,
				                     to};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	
	@Override
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, 
			int approvalStatus, String from, String to, String sort, int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {approvalStatus,
				                     applyOrg,
				                     from,
				                     to,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	@Override
	public int getCiApplicationsImgNum(String applyOrg, int approvalStatus, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		
		Object[] obj = new Object[] {approvalStatus,
				                     applyOrg,
				                     from,
				                     to};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
		
	}
	
	@Override
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, String from, String to, String sort,
			int startPosition, int pageRows) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY APPLY_DATE ");
		} else {
			sql.append("ORDER BY APPLY_DATE DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyOrg,
									 from,
									 to,
				                     startPosition,
				                     pageRows};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}

	@Override
	public int getCiApplicationsImgNum(String applyOrg, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CI_APPLICATION ");
		sql.append("WHERE SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		
		Object[] obj = new Object[] {applyOrg, from, to};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
		
	}
	
	
	@Override
	public List<CiApplicationVO> getCiApplicationsExcel(String applyBoft,
			String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		sql.append("ORDER BY APPLY_DATE  ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     approvalStatus,
				                     applyOrg,
				                     from,
				                     to};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}
	
	
	@Override
	public List<CiApplicationVO> getCiApplicationsExcel(String applyBoft,
			String applyMeettaiwan, String applyOrg, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = ? ");
		sql.append("AND APPLY_MEETTAIWAN = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'CI%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		sql.append("ORDER BY APPLY_DATE  ");
		
		Object[] obj = new Object[] {applyBoft, 
				                     applyMeettaiwan,
				                     applyOrg,
				                     from,
				                     to};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}
	
	@Override
	public List<CiApplicationVO> getCiApplicationsImgExcel(String applyOrg, int approvalStatus, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND APPROVAL_STATUS = ? ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		sql.append("ORDER BY APPLY_DATE  ");
		
		Object[] obj = new Object[] {approvalStatus,
				                     applyOrg,
				                     from,
				                     to};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}
	
	
	@Override
	public List<CiApplicationVO> getCiApplicationsImgExcel(String applyOrg, String from, String to) {
		applyOrg = "%" + applyOrg + "%"; 
		CiApplicationRowMapper carm = new CiApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CI_APPLICATION ");
		sql.append("WHERE APPLY_BOFT = 'N' ");
		sql.append("AND APPLY_MEETTAIWAN = 'Y' ");
		sql.append("AND (DELETED = 'N' OR DELETED IS NULL) ");
		sql.append("AND APPLY_ORG LIKE ? ");
		sql.append("AND SERIAL_NUMBER LIKE 'IMG%' ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') >= ? ");
		sql.append("AND DATE_FORMAT(APPLY_DATE, '%Y-%m-%d') <= ? ");
		sql.append("ORDER BY APPLY_DATE  ");
		
		Object[] obj = new Object[] {applyOrg,
				                     from,
				                     to};

		List<CiApplicationVO> cavos = this.jdbcTemplate.query(sql.toString(), obj, carm);
		
		if(cavos.size() == 0) {
			return null;
		}
		
		return cavos;
	}
	
	

}
