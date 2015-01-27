package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.PlayerVO;

public class PlayerRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		PlayerVO vo = new PlayerVO();
//		vo.setDob(rs.getTimestamp("dob"));
		vo.setFirstname(rs.getString("firstname"));
		vo.setId(rs.getInt("id"));
		vo.setLastname(rs.getString("lastname"));
		vo.setNumber(rs.getInt("number"));
		vo.setPosition(rs.getString("position"));
		vo.setRegion(rs.getInt("region"));
		return vo;
	}

}
