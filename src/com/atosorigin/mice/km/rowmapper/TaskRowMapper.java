package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.bean.TaskBean;

public class TaskRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		TaskBean tb = new TaskBean();
		tb.setDescription(rs.getString("CONFERENCE_NAME"));
		tb.setNum(rs.getInt("NUM"));
		return tb;
	}

}
