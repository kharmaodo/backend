package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.VenueVO;

public class VenueRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		VenueVO vo = new VenueVO();
		vo.setId(rs.getString("ID"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setTel(rs.getString("TEL"));
		vo.setUrl(rs.getString("URL"));
		vo.setOwnRoom(rs.getString("OWN_ROOM"));
		vo.setRoom(rs.getInt("ROOM"));
		vo.setNearbyHotel(rs.getString("NEARBY_HOTEL"));
		vo.setOwnParking(rs.getString("OWN_PARKING"));
		vo.setNearbyParking(rs.getString("NEARBY_PARKING"));
		vo.setNearbyMrtStation(rs.getString("NEARBY_MRT_STATION"));
		vo.setNearbyBusStop(rs.getString("NEARBY_BUS_STOP"));
		vo.setNearbyHsrStation(rs.getString("NEARBY_HSR_STATION"));
		vo.setNearbyTrainStation(rs.getString("NEARBY_TRAIN_STATION"));
		vo.setNearbyHospital(rs.getString("NEARBY_HOSPITAL"));
		vo.setChineseFood(rs.getString("CHINESE_FOOD"));
		vo.setWesternFood(rs.getString("WESTERN_FOOD"));
		vo.setRefreshment(rs.getString("REFRESHMENT"));
		vo.setNoCateringService(rs.getString("NO_CATERING_SERVICE"));
		vo.setActivated(rs.getString("ACTIVATED"));
		vo.setVerified(rs.getString("VERIFIED"));
		vo.setCreator(rs.getString("CREATOR"));
		vo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
		vo.setModifier(rs.getString("MODIFIER"));
		vo.setModifyDate(rs.getTimestamp("MODIFY_DATE"));
		vo.setVerifier(rs.getString("VERIFIER"));
		vo.setVerifyDate(rs.getTimestamp("VERIFY_DATE"));
		vo.setVerifyNote(rs.getString("VERIFY_NOTE"));
		vo.setOwnerId(rs.getString("OWNER_ID"));
		vo.setVenueCategoryId(rs.getString("VENUE_CATEGORY_ID"));
		vo.setCountyCategoryId(rs.getString("COUNTY_CATEGORY_ID"));
		vo.setRegion(rs.getString("REGION"));
		vo.setLatitude(rs.getString("LATITUDE"));
		vo.setLongitude(rs.getString("LONGITUDE"));
		vo.setBooth(rs.getInt("BOOTH"));
		vo.setMeetingRoom(rs.getInt("MEETING_ROOM"));
		vo.setCapacity(rs.getInt("CAPACITY"));
		vo.setDivision(rs.getString("DIVISION"));
		vo.setDivisionRoom(rs.getInt("DIVISION_ROOM"));
		return vo;
	}
}









