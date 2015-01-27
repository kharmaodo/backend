package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.MappApplicationVO;

public class MappApplicationRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		MappApplicationVO vo = new MappApplicationVO();
		vo.setId(rs.getInt("ID"));
		vo.setApplyOrganization(rs.getString("APPLY_ORGANIZATION"));
		vo.setHostOrganization(rs.getString("HOST_ORGANIZATION"));
		vo.setCampaignTW(rs.getString("CAMPAIGN_TW"));
		vo.setCampaignEN(rs.getString("CAMPAIGN_EN"));
		vo.setPlace(rs.getString("PLACE"));
		vo.setStartDate(rs.getTimestamp("START_DATE"));
		vo.setEndDate(rs.getTimestamp("END_DATE"));
		vo.setAttendee(rs.getString("ATTENDEE"));
		vo.setCountry(rs.getString("COUNTRY"));
		vo.setTestDate(rs.getTimestamp("TEST_DATE"));
		vo.setLaunchDate(rs.getTimestamp("LAUNCH_DATE"));
		vo.setChinese(rs.getString("CHINESE"));
		vo.setEnglish(rs.getString("ENGLISH"));
		vo.setApplyCharger(rs.getString("APPLY_CHARGER"));
		vo.setContact(rs.getString("CONTACT"));
		vo.setPhone(rs.getString("PHONE"));
		vo.setEmail(rs.getString("EMAIL"));
		vo.setUrl(rs.getString("URL"));
		vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		vo.setStatus(rs.getString("STATUS"));
		vo.setApprovalDate(rs.getTimestamp("APPROVAL_DATE"));
		vo.setApprovalBy(rs.getString("APPROVAL_BY"));
		return vo;
	}

}
