package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.GroupsVO;

public class GroupsRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		GroupsVO gvo = new GroupsVO();
		gvo.setId(rs.getString("ID"));
		gvo.setName(rs.getString("NAME"));
		return gvo;
	}

}
