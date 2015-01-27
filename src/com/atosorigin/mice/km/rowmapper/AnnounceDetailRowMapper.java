package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.AnnouncementDetailVO;

public class AnnounceDetailRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		AnnouncementDetailVO vo = new AnnouncementDetailVO();
		
		vo.setContent(rs.getString("CONTENT"));
		vo.setId(rs.getString("ID"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setAnnouncementId(rs.getString("ANNOUNCEMENT_ID"));
		vo.setTopic(rs.getString("TOPIC"));
		vo.setVisible(rs.getString("VISIBLE"));
		
		return vo;
	}
}
