package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.PressReleaseDetailVO;

public class ReleaseRowDetailMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		PressReleaseDetailVO vo = new PressReleaseDetailVO();
		
		vo.setContent(rs.getString("CONTENT"));
		vo.setId(rs.getString("ID"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setPressReleaseId(rs.getString("PRESS_RELEASE_ID"));
		vo.setTopic(rs.getString("TOPIC"));
		vo.setVisible(rs.getString("VISIBLE"));
		
		return vo;
	}

}
