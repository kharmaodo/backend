package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.VenueDetailVO;

public class VenueDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		VenueDetailVO vo = new VenueDetailVO();
		vo.setId(rs.getString("ID"));
		vo.setVisible(rs.getString("VISIBLE"));
		vo.setName(rs.getString("NAME"));
		vo.setAddress(rs.getString("ADDRESS"));
		vo.setParking(rs.getString("PARKING"));
		vo.setHotel(rs.getString("HOTEL"));
		vo.setMrtStation(rs.getString("MRT_STATION"));
		vo.setBusStop(rs.getString("BUS_STOP"));
		vo.setHsrStation(rs.getString("HSR_STATION"));
		vo.setTrainStation(rs.getString("TRAIN_STATION"));
		vo.setHospital(rs.getString("HOSPITAL"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setUrl(rs.getString("URL"));
		vo.setVenueId(rs.getString("VENUE_ID"));
		return vo;
	}
}
