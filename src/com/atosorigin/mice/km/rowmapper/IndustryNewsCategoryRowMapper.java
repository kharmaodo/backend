package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.IndustryNewsCategoryVO;

public class IndustryNewsCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		IndustryNewsCategoryVO vo = new IndustryNewsCategoryVO();
		
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setId(rs.getString("ID"));
		
		return vo;
	}

}
