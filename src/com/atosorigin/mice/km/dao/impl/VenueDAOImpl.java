package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.bean.VenueBean;
import com.atosorigin.mice.km.dao.VenueDAO;
import com.atosorigin.mice.km.rowmapper.VenueRowMapper;
import com.atosorigin.mice.km.vo.VenueDetailVO;
import com.atosorigin.mice.km.vo.VenueVO;

public class VenueDAOImpl implements VenueDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(VenueVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO VENUE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append("                          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append("                          ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append("                          ?, ?, ?, ?, ?, ?, ?, ?)"); 
		Object[] obj = new Object[] {
				vo.getId(),
				vo.getDescription(),
				vo.getTel(),
				vo.getUrl(),
				vo.getOwnRoom(),
				vo.getRoom(),
				vo.getNearbyHotel(),
				vo.getOwnParking(),
				vo.getNearbyParking(),
				vo.getNearbyMrtStation(),
				vo.getNearbyBusStop(),
				vo.getNearbyHsrStation(),
				vo.getNearbyTrainStation(),
				vo.getNearbyHospital(),
				vo.getChineseFood(),
				vo.getWesternFood(),
				vo.getRefreshment(),
				vo.getNoCateringService(),
				vo.getActivated(),
				vo.getVerified(),
				vo.getCreator(),
				vo.getCreateDate(),
				vo.getModifier(),
				vo.getModifyDate(),
				vo.getVerifier(),
				vo.getVerifyDate(),
				vo.getVerifyNote(),
				vo.getOwnerId(),
				vo.getVenueCategoryId(),
				vo.getCountyCategoryId(),
				vo.getRegion(),
				vo.getLatitude(),
				vo.getLongitude(),
				vo.getBooth(),
				vo.getMeetingRoom(),
				vo.getCapacity(),
				vo.getDivision(),
				vo.getDivisionRoom()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(VenueVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE VENUE SET DESCRIPTION = ?, ");
		sql.append("                 TEL = ?, ");
		sql.append("                 URL = ?, ");
		sql.append("                 OWN_ROOM = ?, ");
		sql.append("                 ROOM = ?, ");
		sql.append("                 NEARBY_HOTEL = ?, ");
		sql.append("                 OWN_PARKING = ?, ");
		sql.append("                 NEARBY_PARKING = ?, ");
		sql.append("                 NEARBY_MRT_STATION = ?, ");
		sql.append("                 NEARBY_BUS_STOP = ?, ");
		sql.append("                 NEARBY_HSR_STATION = ?, ");
		sql.append("                 NEARBY_TRAIN_STATION = ?, ");
		sql.append("                 NEARBY_HOSPITAL = ?, ");
		sql.append("                 CHINESE_FOOD = ?, ");
		sql.append("                 WESTERN_FOOD = ?, ");
		sql.append("                 REFRESHMENT = ?, ");
		sql.append("                 NO_CATERING_SERVICE = ?, ");
		sql.append("                 ACTIVATED = ?, ");
		sql.append("                 VERIFIED = ?, ");
		sql.append("                 CREATOR = ?, ");
		sql.append("                 CREATE_DATE = ?, ");
		sql.append("                 MODIFIER = ?, ");
		sql.append("                 MODIFY_DATE = ?, ");
		sql.append("                 VERIFIER = ?, ");
		sql.append("                 VERIFY_DATE = ?, ");
		sql.append("                 VERIFY_NOTE = ?, ");
		sql.append("                 OWNER_ID = ?, ");
		sql.append("                 VENUE_CATEGORY_ID = ?, ");
		sql.append("                 COUNTY_CATEGORY_ID = ?, ");
		sql.append("                 REGION = ?, ");
		sql.append("                 LATITUDE = ?, ");
		sql.append("                 LONGITUDE = ?, ");
		sql.append("                 BOOTH = ?, ");
		sql.append("                 MEETING_ROOM = ?, ");
		sql.append("                 CAPACITY = ?, ");
		sql.append("                 DIVISION = ?, ");
		sql.append("                 DIVISION_ROOM = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {
				vo.getDescription(),
				vo.getTel(),
				vo.getUrl(),
				vo.getOwnRoom(),
				vo.getRoom(),
				vo.getNearbyHotel(),
				vo.getOwnParking(),
				vo.getNearbyParking(),
				vo.getNearbyMrtStation(),
				vo.getNearbyBusStop(),
				vo.getNearbyHsrStation(),
				vo.getNearbyTrainStation(),
				vo.getNearbyHospital(),
				vo.getChineseFood(),
				vo.getWesternFood(),
				vo.getRefreshment(),
				vo.getNoCateringService(),
				vo.getActivated(),
				vo.getVerified(),
				vo.getCreator(),
				vo.getCreateDate(),
				vo.getModifier(),
				vo.getModifyDate(),
				vo.getVerifier(),
				vo.getVerifyDate(),
				vo.getVerifyNote(),
				vo.getOwnerId(),
				vo.getVenueCategoryId(),
				vo.getCountyCategoryId(),
				vo.getRegion(),
				vo.getLatitude(),
				vo.getLongitude(),
				vo.getBooth(),
				vo.getMeetingRoom(),
				vo.getCapacity(),
				vo.getDivision(),
				vo.getDivisionRoom(),
				vo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public VenueVO getVenue(String id) {
		VenueRowMapper rm = new VenueRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENUE WHERE ID = ?");
		Object[] obj = new Object[]{id};
		List<VenueVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

	@Override
	public List<VenueVO> getVenue(String description, String venueCategoryId, String region, int startPosition, int pageRows){
		VenueRowMapper rm = new VenueRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM VENUE ");
		sql.append("WHERE ACTIVATED = 'Y' ");
		if(!description.isEmpty()) {
			description = "%" + description + "%";
			sql.append("AND DESCRIPTION LIKE '"+description+"' ");
		}
		if(!venueCategoryId.isEmpty()) {
			sql.append("AND VENUE_CATEGORY_ID = '"+venueCategoryId+"' ");
		}
		if(!region.isEmpty()) {
			sql.append("AND REGION = '"+region+"' ");
		}
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ? ");
		Object[] obj = new Object[]{startPosition,
				                    pageRows};
		List<VenueVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos : null;
	}
	
	@Override
	public int getVenueNum(String description, String venueCategoryId, String region) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM VENUE ");
		sql.append("WHERE ACTIVATED = 'Y' ");
		if(!description.isEmpty()) {
			description = "%" + description + "%";
			sql.append("AND DESCRIPTION LIKE '"+description+"' ");
		}
		if(!venueCategoryId.isEmpty()) {
			sql.append("AND VENUE_CATEGORY_ID = '"+venueCategoryId+"' ");
		}
		if(!region.isEmpty()) {
			sql.append("AND REGION = '"+region+"' ");
		}
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

}
