package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.CaseStudyVO;

public class CaseStudyRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		CaseStudyVO csvo = new CaseStudyVO();
		csvo.setId(rs.getString("ID"));
		csvo.setDescription(rs.getString("DESCRIPTION"));
		csvo.setPhoto(rs.getString("PHOTO"));
		csvo.setRank(rs.getInt("RANK"));
		csvo.setStartDate(rs.getTimestamp("START_DATE"));
		csvo.setEndDate(rs.getTimestamp("END_DATE"));
		csvo.setCaseStudyCategoryId(rs.getString("CASE_STUDY_CATEGORY_ID"));
		return csvo;
	}

}
