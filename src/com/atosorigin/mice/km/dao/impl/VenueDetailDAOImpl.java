package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.VenueDetailDAO;
import com.atosorigin.mice.km.rowmapper.VenueDetailRowMapper;
import com.atosorigin.mice.km.vo.VenueDetailVO;

public class VenueDetailDAOImpl implements VenueDetailDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(VenueDetailVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO VENUE_DETAIL VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {
				vo.getId(),
				vo.getVisible(),
				vo.getName(),
				vo.getAddress(),
				vo.getParking(),
				vo.getHotel(),
				vo.getMrtStation(),
				vo.getBusStop(),
				vo.getHsrStation(),
				vo.getTrainStation(),
				vo.getHospital(),
				vo.getLocale(),
				vo.getUrl(),
				vo.getVenueId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int delete(String venueId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM VENUE_DETAIL WHERE VENUE_ID = ?"); 
		Object[] obj = new Object[] {venueId};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public List<VenueDetailVO> getVenueDetail(String venueId) {
		VenueDetailRowMapper rm = new VenueDetailRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENUE_DETAIL WHERE VENUE_ID = ? ");
		Object[] obj = new Object[]{venueId};
		List<VenueDetailVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}

}
