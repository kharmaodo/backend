package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.OverseasEventVO;

public class OverseasEventRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		OverseasEventVO vo = new OverseasEventVO();
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setEndDate(rs.getTimestamp("END_DATE"));
		vo.setId(rs.getString("ID"));
		vo.setRegionCategoryId(rs.getString("REGION_CATEGORY_ID"));
		vo.setStartDate(rs.getTimestamp("START_DATE"));
		//vo.setUrl(rs.getString("URL"));
		vo.setVendorName(rs.getString("VENDOR_NAME"));
		return vo;
	}

}
