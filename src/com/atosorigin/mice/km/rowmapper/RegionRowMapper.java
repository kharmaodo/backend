package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.RegionVO;

public class RegionRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		RegionVO vo = new RegionVO();
		vo.setId(rs.getInt("id"));
		vo.setName(rs.getString("name"));
		return vo;
	}

}
