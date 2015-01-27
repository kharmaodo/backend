package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.OverseasEventDAO;
import com.atosorigin.mice.km.rowmapper.OverseasEventRowMapper;
import com.atosorigin.mice.km.vo.OverseasEventVO;

public class OverseasEventDAOImpl implements OverseasEventDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(OverseasEventVO overseasEventVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO OVERSEAS_EVENT VALUES (?, ?, ?, ?, ?, ?)");
		Object[] obj = new Object[]{overseasEventVO.getId(),
				                    overseasEventVO.getDescription(),
				                    overseasEventVO.getRegionCategoryId(),
				                    overseasEventVO.getStartDate(),
				                    overseasEventVO.getEndDate(),
				                    overseasEventVO.getVendorName()};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(OverseasEventVO overseasEventVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE OVERSEAS_EVENT SET DESCRIPTION = ?, ");
		sql.append("                         REGION_CATEGORY_ID = ?, ");
		sql.append("                         START_DATE = ?, ");
		sql.append("                         END_DATE = ?, ");
		sql.append("                         VENDOR_NAME = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[]{overseasEventVO.getDescription(),
				                    overseasEventVO.getRegionCategoryId(),
				                    overseasEventVO.getStartDate(),
				                    overseasEventVO.getEndDate(),
				                    overseasEventVO.getVendorName(),
				                    overseasEventVO.getId()};
		
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	
	@Override
	public int delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM OVERSEAS_EVENT WHERE ID = ?");
		Object[] obj = new Object[]{id};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	

	@Override
	public OverseasEventVO getOverseasEvent(String id) {
		OverseasEventRowMapper rm = new OverseasEventRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM OVERSEAS_EVENT WHERE ID = ?");
		Object[] obj = new Object[]{id};
		List<OverseasEventVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() == 0 ? null : vos.get(0);
	}

	@Override
	public List<OverseasEventVO> getOverseasEvents(String description, String from, String to, int startPosition, int pageRows) {
		OverseasEventRowMapper rm = new OverseasEventRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM OVERSEAS_EVENT WHERE ");
		sql.append("DATE_FORMAT(START_DATE,'%Y-%m-%d') >= ? AND ");
		sql.append("DATE_FORMAT(START_DATE,'%Y-%m-%d') <= ? ");
		if(!description.isEmpty()) {
			sql.append(" AND DESCRIPTION LIKE '%" + description + "%' ");
		}
		sql.append("ORDER BY START_DATE DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[]{from,
				                    to,
				                    startPosition,
				                    pageRows};
		List<OverseasEventVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() == 0 ? null : vos;
	}

	@Override
	public int getOverseasEventsNum(String description, String from, String to) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM OVERSEAS_EVENT WHERE ");
		sql.append("DATE_FORMAT(START_DATE,'%Y-%m-%d') >= ? AND ");
		sql.append("DATE_FORMAT(START_DATE,'%Y-%m-%d') <= ? ");
		if(!description.isEmpty()) {
			sql.append(" AND DESCRIPTION LIKE '%" + description + "%' ");
		}
		Object[] obj = new Object[]{from,
				                    to};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
