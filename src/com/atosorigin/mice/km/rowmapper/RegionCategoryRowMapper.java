package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.RegionCategoryVO;

public class RegionCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		RegionCategoryVO vo = new RegionCategoryVO();
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setId(rs.getString("ID"));
		vo.setLevel(rs.getInt("LEVEL"));
		vo.setParentId(rs.getString("PARENT_ID"));
		return vo;
	}

}
