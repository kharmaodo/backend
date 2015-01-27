package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.EpaperVO;

public class EpaperRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		EpaperVO vo = new EpaperVO();
		vo.setId(rs.getString("ID"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setVolume(rs.getInt("VOLUME"));
		vo.setPublishDate(rs.getTimestamp("PUBLISH_DATE"));
		vo.setPhoto(rs.getString("PHOTO"));
		vo.setUrl(rs.getString("URL"));
		vo.setPath(rs.getString("PATH"));
		vo.setLocale(rs.getString("LOCALE"));
		return vo;
	}

}
