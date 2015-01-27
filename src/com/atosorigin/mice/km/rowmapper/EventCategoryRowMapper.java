package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.EventCategoryVO;

public class EventCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		EventCategoryVO vo = new EventCategoryVO();
		vo.setCalendarId(rs.getString("CALENDAR_ID"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setId(rs.getString("ID"));
		vo.setRank(rs.getInt("RANK"));
		return vo;
	}

}
