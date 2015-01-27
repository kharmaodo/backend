package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.CaseStudyCategoryVO;
import com.atosorigin.mice.km.vo.WiflyVO;

public class CaseStudyCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		CaseStudyCategoryVO cscvo = new CaseStudyCategoryVO();
		cscvo.setId(rs.getString("ID"));
		cscvo.setDescription(rs.getString("DESCRIPTION"));
		return cscvo;
	}

}
