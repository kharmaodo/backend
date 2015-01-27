package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.ProjectVO;

public class ProjectRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		ProjectVO vo = new ProjectVO();
		vo.setCreateBy(rs.getString("CREATE_BY"));
		vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setId(rs.getString("ID"));
		vo.setLocale(rs.getString("LOCALE"));
		vo.setMenuId(rs.getString("MENU_ID"));
		vo.setVisible(rs.getString("VISIBLE"));
		return vo;
	}

}
