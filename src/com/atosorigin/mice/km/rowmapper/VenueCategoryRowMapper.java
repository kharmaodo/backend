package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.VenueCategoryVO;

public class VenueCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		VenueCategoryVO vo = new VenueCategoryVO();
		vo.setId(rs.getString("ID"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		return vo;
	}

}
