package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.VendorCategoryVO;

public class VendorCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		VendorCategoryVO vcvo = new VendorCategoryVO();
		vcvo.setId(rs.getString("ID"));
		vcvo.setDescription(rs.getString("DESCRIPTION"));
		vcvo.setLevelIndex(rs.getInt("LEVEL_INDEX"));
		vcvo.setParentId(rs.getString("PARENT_ID"));
		return vcvo;
	}

}
