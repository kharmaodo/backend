package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.FeedbackVO;

public class FeedbackRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		FeedbackVO vo = new FeedbackVO();
		vo.setId(rs.getInt("ID"));
		vo.setCategory(rs.getString("CATEGORY"));
		vo.setCountry(rs.getString("COUNTRY"));
		vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		vo.setEmail(rs.getString("EMAIL"));
		vo.setName(rs.getString("NAME"));
		vo.setPhone(rs.getString("PHONE"));
		vo.setResponseBy(rs.getString("RESPONSE_BY"));
		vo.setResponseContent(rs.getString("RESPONSE_CONTENT"));
		vo.setResponseTime(rs.getTimestamp("RESPONSE_TIME"));
		vo.setTopic(rs.getString("TOPIC"));
		vo.setVisible(rs.getString("VISIBLE"));
		
		return vo;
	}

}
