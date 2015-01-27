package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.BackendNewsVO;

public class BackendNewsRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		BackendNewsVO bnvo = new BackendNewsVO();
		bnvo.setContent(rs.getString("CONTENT"));
		bnvo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		bnvo.setGroupIds(rs.getString("GROUP_IDS"));
		bnvo.setId(rs.getInt("ID"));
		bnvo.setModifyTime(rs.getTimestamp("MODIFY_TIME"));
		return bnvo;
	}

}
