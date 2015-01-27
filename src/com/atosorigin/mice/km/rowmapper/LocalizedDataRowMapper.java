package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class LocalizedDataRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		LocalizedDataVO ldvo = new LocalizedDataVO();
		ldvo.setId(rs.getString("ID"));
		ldvo.setLocaleName(rs.getString("LOCALE_NAME"));
		ldvo.setName(rs.getString("NAME"));
		return ldvo;
	}

}
