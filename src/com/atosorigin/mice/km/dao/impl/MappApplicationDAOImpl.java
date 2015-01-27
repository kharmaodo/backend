package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.MappApplicationDAO;
import com.atosorigin.mice.km.rowmapper.CiApplicationRowMapper;
import com.atosorigin.mice.km.rowmapper.MappApplicationRowMapper;
import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public class MappApplicationDAOImpl implements MappApplicationDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;


	@Override
	public int getForTask() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION WHERE STATUS = 0"); 
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public MappApplicationVO getMappApplication(String id) {
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE ID = ? ");
		
		Object[] obj = new Object[] {id}; 

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		
		return mvos.size() == 0 ? null : mvos.get(0);
	}

	@Override
	public List<MappApplicationVO> getMappApplications(String campaign, String applyOrganization, String status, String sort, int startPosition,
			int pageRows) {
		campaign = "%" + campaign + "%";
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND APPLY_ORGANIZATION LIKE ? ");
		sql.append("AND STATUS = ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
									 applyOrganization,
				                     status,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsNum(String campaign, String applyOrganization, String status) {
		campaign = "%" + campaign + "%";
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND APPLY_ORGANIZATION LIKE ? ");
		sql.append("AND STATUS = ? ");
		
		Object[] obj = new Object[] {campaign, 
				 					 campaign,
				 					 applyOrganization,
				 					 status};
		
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByAS(String applyOrganization, String status, String sort, int startPosition,
			int pageRows) {
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE APPLY_ORGANIZATION LIKE ? ");
		sql.append("AND STATUS = ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyOrganization,
				                     status,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsByASNum(String applyOrganization,
			String status) {
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE APPLY_ORGANIZATION LIKE ? ");
		sql.append("AND STATUS = ? ");
		Object[] obj = new Object[] {applyOrganization,
				 					 status};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByApply(String applyOrganization, String sort, int startPosition, int pageRows) {
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE APPLY_ORGANIZATION LIKE ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyOrganization,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsByApplyNum(String applyOrganization) {
		applyOrganization = "%" + applyOrganization + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE APPLY_ORGANIZATION LIKE ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {applyOrganization};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByCA(String campaign, String applyOrganization, String sort, int startPosition, int pageRows) {
		campaign = "%" + campaign + "%";
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND APPLY_ORGANIZATION LIKE ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
									 applyOrganization,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsByCANum(String campaign, String applyOrganization) {
		campaign = "%" + campaign + "%";
		applyOrganization = "%" + applyOrganization + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND APPLY_ORGANIZATION LIKE ? ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
									 applyOrganization};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByCS(String campaign, String status, String sort, int startPosition, int pageRows) {
		campaign = "%" + campaign + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND STATUS = ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
				                     status,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsByCSNum(String campaign, String status) {
		campaign = "%" + campaign + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND STATUS = ? ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
				                     status};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByCampaign(String campaign, String sort, int startPosition, int pageRows) {
		campaign = "%" + campaign + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsByCampaignNum(String campaign) {
		campaign = "%" + campaign + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByStatus(String status, String sort, int startPosition, int pageRows) {
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE STATUS = ? ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {status,
				                     startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsByStatusNum(String status) {
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		sql.append("WHERE STATUS = ? ");
		
		Object[] obj = new Object[] {status};

		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public List<MappApplicationVO> getMappApplications(String sort, int startPosition, int pageRows) {
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME ");
		} else {
			sql.append("ORDER BY CREATE_TIME DESC ");
		}
		sql.append("LIMIT ?, ? ");
		
		Object[] obj = new Object[] {startPosition,
				                     pageRows};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public int getMappApplicationsNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION ");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}


	@Override
	public int update(MappApplicationVO mappApplicationVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MAPP_APPLICATION SET STATUS = ?, "); 
		sql.append("		                  	APPROVAL_DATE = ?, ");
		sql.append("		                  	APPROVAL_BY = ?, ");
		sql.append("		                  	PASSWORD = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {mappApplicationVO.getStatus(), 
									 mappApplicationVO.getApprovalDate(),
									 mappApplicationVO.getApprovalBy(),
									 mappApplicationVO.getPassword(),
                					 mappApplicationVO.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public boolean isMember(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MAPP_APPLICATION WHERE EMAIL = ? AND STATUS = '1'"); 
		Object[] obj = new Object[] {email}; 
		return this.jdbcTemplate.queryForInt(sql.toString(), obj) > 0 ? true : false;
	}
	
	
	
	
	@Override
	public List<MappApplicationVO> getMappApplicationsExcel(String campaign, String applyOrganization, String status) {
		campaign = "%" + campaign + "%";
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND APPLY_ORGANIZATION LIKE ? ");
		sql.append("AND STATUS = ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
									 applyOrganization,
				                     status};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByASExcel(String applyOrganization, String status) {
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE APPLY_ORGANIZATION LIKE ? ");
		sql.append("AND STATUS = ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {applyOrganization,
				                     status};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByApplyExcel(String applyOrganization) {
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE APPLY_ORGANIZATION LIKE ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {applyOrganization};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByCAExcel(String campaign, String applyOrganization) {
		campaign = "%" + campaign + "%";
		applyOrganization = "%" + applyOrganization + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND APPLY_ORGANIZATION LIKE ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
									 applyOrganization};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByCSExcel(String campaign, String status) {
		campaign = "%" + campaign + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("AND STATUS = ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign,
				                     status};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByCampaignExcel(String campaign) {
		campaign = "%" + campaign + "%";
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE (CAMPAIGN_TW LIKE ? OR CAMPAIGN_TW LIKE ?) ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {campaign, 
									 campaign};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsByStatusExcel(String status) {
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("WHERE STATUS = ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		Object[] obj = new Object[] {status};

		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		return mvos.size() == 0 ? null : mvos;
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsExcel() {
		MappApplicationRowMapper mrm = new MappApplicationRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MAPP_APPLICATION ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		
		List<MappApplicationVO> mvos = this.jdbcTemplate.query(sql.toString(), mrm);
		return mvos.size() == 0 ? null : mvos;
	}
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
}
