package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.RegionTaiwanVO;

public class RegionTaiwanRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		RegionTaiwanVO vo = new RegionTaiwanVO();
		
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setId(rs.getString("ID"));
		return vo;
	}

}
