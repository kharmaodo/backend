package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.OverseasEventDetailVO;

public class OverseasEventDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		OverseasEventDetailVO vo = new OverseasEventDetailVO();
		vo.setEventName(rs.getString("EVENT_NAME"));
		vo.setHostCity(rs.getString("HOST_CITY"));
		vo.setId(rs.getString("ID"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setOverseasEventId(rs.getString("OVERSEAS_EVENT_ID"));
		return vo;
	}

}
