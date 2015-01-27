package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.CaseStudyDetailVO;

public class CaseStudyDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
		csdvo.setId(rs.getString("ID"));
		csdvo.setVisible(rs.getString("VISIBLE"));
		csdvo.setTitle(rs.getString("TITLE"));
		csdvo.setUrl(rs.getString("URL"));
		csdvo.setLocation(rs.getString("LOCATION"));
		csdvo.setOrganizer(rs.getString("ORGANIZER"));
		csdvo.setShortDescription(rs.getString("SHORT_DESCRIPTION"));
		csdvo.setLocale(rs.getString("LOCALE"));
		csdvo.setContent(rs.getString("CONTENT"));
		csdvo.setCaseStudyId(rs.getString("CASE_STUDY_ID"));
		csdvo.setYoutubeId(rs.getString("YOUTUBE_ID"));
		csdvo.setAttendee(rs.getString("ATTENDEE"));
		
		return csdvo;
	}

}
