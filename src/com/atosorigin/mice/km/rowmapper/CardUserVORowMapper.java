package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.CardUserVO;

public class CardUserVORowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CardUserVO cvo = new CardUserVO();
		cvo.setId(rs.getString("ID"));
		cvo.setName(rs.getString("NAME"));
		cvo.setNationality(rs.getString("NATIONALITY"));
		cvo.setConferenceId(rs.getInt("CONFERENCE_ID"));
		cvo.setStayFrom(rs.getTimestamp("STAY_FROM"));
		cvo.setStayTo(rs.getTimestamp("STAY_TO"));
		cvo.setOrganization(rs.getString("ORGANIZATION"));
		cvo.setEmail(rs.getString("EMAIL"));
		cvo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
		cvo.setStatus(rs.getString("STATUS"));
		cvo.setVerifier(rs.getString("VERIFIER"));
		cvo.setVerifiedDate(rs.getTimestamp("VERIFIED_DATE"));
		cvo.setStayDays(rs.getInt("STAY_DAYS"));
		cvo.setLocale(rs.getString("LOCALE"));
		cvo.setGender(rs.getString("GENDER"));
		return cvo;
	}

}
