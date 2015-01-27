package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.PropagandaDAO;
import com.atosorigin.mice.km.rowmapper.PropagandaRowMapper;
import com.atosorigin.mice.km.vo.PropagandaVO;

public class PropagandaDAOImpl implements PropagandaDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int update(PropagandaVO propagandaVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PROPAGANDA SET APPROVAL_STATUS = ?, APPROVED_DATE = ?, DELETED = ? WHERE ID = ?");
		Object[] obj = new Object[] {propagandaVO.getApprovalStatus(),
									 propagandaVO.getApprovedDate(),
									 propagandaVO.getDeleted(),
									 propagandaVO.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public PropagandaVO getPropaganda(String id) {
		PropagandaRowMapper rm = new PropagandaRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROPAGANDA WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<PropagandaVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

	@Override
	public List<PropagandaVO> getPropagandas(String from, String to, String applyOrg, int approvedStatus, int startPosition, int pageRows) {
		PropagandaRowMapper rm = new PropagandaRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PROPAGANDA WHERE DATE_FORMAT(APPLY_DATE,'%Y-%m-%d') >= ? AND DATE_FORMAT(APPLY_DATE,'%Y-%m-%d') <= ? ");
		if(!applyOrg.isEmpty()) {
			sql.append("AND APPLY_ORG LIKE '%" + applyOrg + "%' ");
		}
		if(approvedStatus != 9) {
			sql.append("AND APPROVAL_STATUS = " + approvedStatus + " ");
		}
		sql.append("ORDER BY APPLY_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {from, to, startPosition, pageRows};
		List<PropagandaVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getPropagandaNum(String from, String to, String applyOrg, int approvedStatus) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM PROPAGANDA WHERE DATE_FORMAT(APPLY_DATE,'%Y-%m-%d') >= ? AND DATE_FORMAT(APPLY_DATE,'%Y-%m-%d') <= ? ");
		if(!applyOrg.isEmpty()) {
			sql.append("AND APPLY_ORG LIKE '%" + applyOrg + "%' ");
		}
		if(approvedStatus != 9) {
			sql.append("AND APPROVAL_STATUS = " + approvedStatus + " ");
		}
		Object[] obj = new Object[] {from, to};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
