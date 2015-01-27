package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;

public class IndustryNewsDetailRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
		
		vo.setId(rs.getString("ID"));
		vo.setVisible(rs.getString("VISIBLE"));
		vo.setTopic(rs.getString("TOPIC"));
		vo.setContent(rs.getString("CONTENT"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setIndustryNewsId(rs.getString("INDUSTRY_NEWS_ID"));
		
		return vo;
	}

}
