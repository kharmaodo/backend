package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.WiflyVO;

public class WiflyRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		WiflyVO wvo = new WiflyVO();
		wvo.setAccount(rs.getString("ACCOUNT"));
		wvo.setPassword(rs.getString("PASSWORD"));
		wvo.setCardUserId(rs.getString("CARD_USER_ID"));
		wvo.setAppliedDate(rs.getTime("APPLIED_DATE"));
		return wvo;
	}

}
