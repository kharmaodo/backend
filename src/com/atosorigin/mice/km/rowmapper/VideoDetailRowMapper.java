package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.VideoDetailVO;

public class VideoDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		VideoDetailVO videoDetailVO = new VideoDetailVO();
		
		videoDetailVO.setDescription(rs.getString("DESCRIPTION"));
		videoDetailVO.setLocale(rs.getString("LOCALE"));
		videoDetailVO.setId(rs.getString("ID"));
		videoDetailVO.setName(rs.getString("NAME"));
		videoDetailVO.setVideoId(rs.getString("VIDEO_ID"));
		
		return videoDetailVO;
	}

}
