package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.bean.VideoBean;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class VideoRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		VideoVO videoVO = new VideoVO();
		
		videoVO.setDescription(rs.getString("DESCRIPTION"));
		videoVO.setId(rs.getString("ID"));
		videoVO.setKeywords(rs.getString("KEYWORDS"));
		videoVO.setTitle(rs.getString("TITLE"));
		videoVO.setUploadDate(rs.getTimestamp("UPLOAD_DATE"));
		videoVO.setVerified(rs.getString("VERIFIED"));
		videoVO.setYoutubeId(rs.getString("YOUTUBE_ID"));
		
		return videoVO;
	}

}
