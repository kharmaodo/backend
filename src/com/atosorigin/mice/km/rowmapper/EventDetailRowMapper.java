package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.EventDetailVO;

public class EventDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		EventDetailVO vo = new EventDetailVO();
		vo.setCountry(rs.getString("COUNTRY"));
		vo.setEventId(rs.getString("EVENT_ID"));
		vo.setId(rs.getString("ID"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setName(rs.getString("NAME"));
		vo.setOrganization(rs.getString("ORGANIZATION"));
		vo.setPlace(rs.getString("PLACE"));
		vo.setTopic(rs.getString("TOPIC"));
		vo.setUrl(rs.getString("URL"));
		vo.setVisible(rs.getString("VISIBLE"));
		vo.setContent(rs.getString("CONTENT"));
		return vo;
	}

}
