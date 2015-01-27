package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.EventVO;

public class EventRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		EventVO vo = new EventVO();
		vo.setActivated(rs.getString("ACTIVATED"));
		vo.setCountries(rs.getInt("COUNTRIES"));
		vo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
		vo.setCreator(rs.getString("CREATOR"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setEndDate(rs.getTimestamp("END_DATE"));
		vo.setEventCategoryId(rs.getString("EVENT_CATEGORY_ID"));
		vo.setEventEntryId(rs.getString("EVENT_ENTRY_ID"));
		vo.setId(rs.getString("ID"));
		vo.setInfo(rs.getString("INFO"));
		vo.setLogo(rs.getString("LOGO"));
		vo.setModifier(rs.getString("MODIFIER"));
		vo.setModifyDate(rs.getTimestamp("MODIFY_DATE"));
		vo.setOverseas(rs.getString("OVERSEAS"));
		vo.setOwnerId(rs.getString("OWNER_ID"));
		vo.setPeople(rs.getInt("PEOPLE"));
		vo.setPhoto(rs.getString("PHOTO"));
		vo.setPotentialShow(rs.getInt("POTENTIAL_SHOW"));
		vo.setRegionTaiwanId(rs.getString("REGION_TAIWAN_ID"));
		vo.setSource(rs.getString("SOURCE"));
		vo.setStartDate(rs.getTimestamp("START_DATE"));
		vo.setUrl(rs.getString("URL"));
		vo.setVerified(rs.getString("VERIFIED"));
		vo.setVerifier(rs.getString("VERIFIER"));
		vo.setVerifyDate(rs.getTimestamp("VERIFY_DATE"));
		vo.setVerifyNote(rs.getString("VERIFY_NOTE"));
		vo.setContact(rs.getString("CONTACT"));
		vo.setContactEmail(rs.getString("CONTACT_EMAIL"));
		vo.setContactTel(rs.getString("CONTACT_TEL"));
		return vo;
	}

}
