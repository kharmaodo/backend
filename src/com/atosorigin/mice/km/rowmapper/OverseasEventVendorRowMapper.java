package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.OverseasEventVendorVO;

public class OverseasEventVendorRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		OverseasEventVendorVO vo = new OverseasEventVendorVO();
		vo.setOverseasEventId(rs.getString("OVERSEAS_EVENT_ID"));
		vo.setVendorId(rs.getString("VENDOR_ID"));
		
		return vo;
	}

}
