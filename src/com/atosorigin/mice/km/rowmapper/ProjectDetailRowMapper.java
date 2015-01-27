package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.ProjectDetailVO;

public class ProjectDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		ProjectDetailVO vo = new ProjectDetailVO();
		vo.setId(rs.getInt("ID"));
		vo.setLocalizedDataId(rs.getString("LOCALIZED_DATA_ID"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setContent(rs.getString("CONTENT"));
		vo.setProjectId(rs.getString("PROJECT_ID"));
		return vo;
	}

}
