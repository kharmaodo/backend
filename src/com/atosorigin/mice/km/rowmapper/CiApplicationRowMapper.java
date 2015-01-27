package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.CiApplicationVO;

public class CiApplicationRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CiApplicationVO cavo = new CiApplicationVO();
		cavo.setId(rs.getString("ID"));
		cavo.setSerialNumber(rs.getString("SERIAL_NUMBER"));
		cavo.setApplyOrg(rs.getString("APPLY_ORG"));
		cavo.setApplyDate(rs.getTimestamp("APPLY_DATE"));
		cavo.setPersonInCharge(rs.getString("PERSON_IN_CHARGE"));
		cavo.setPersonInChargePosition(rs.getString("PERSON_IN_CHARGE_POSITION"));
		cavo.setPersonInChargePhone(rs.getString("PERSON_IN_CHARGE_PHONE"));
		cavo.setPersonInChargeFax(rs.getString("PERSON_IN_CHARGE_FAX"));
		cavo.setApplyContact(rs.getString("APPLY_CONTACT"));
		cavo.setApplyContactPosition(rs.getString("APPLY_CONTACT_POSITION"));
		cavo.setApplyContactPhone(rs.getString("APPLY_CONTACT_PHONE"));
		cavo.setApplyContactFax(rs.getString("APPLY_CONTACT_FAX"));
		cavo.setApplyContactEmail(rs.getString("APPLY_CONTACT_EMAIL"));
		cavo.setEventName(rs.getString("EVENT_NAME"));
		cavo.setStartDate(rs.getTimestamp("START_DATE"));
		cavo.setEndDate(rs.getTimestamp("END_DATE"));
		cavo.setEventLocation(rs.getString("EVENT_LOCATION"));
		cavo.setUsagePrintCheck(rs.getString("USAGE_PRINT_CHECK"));
		cavo.setUsagePrintCount(rs.getString("USAGE_PRINT_COUNT"));
		cavo.setUsagePrintNote(rs.getString("USAGE_PRINT_NOTE"));
		cavo.setUsageInternetCheck(rs.getString("USAGE_INTERNET_CHECK"));
		cavo.setUsageInternetCount(rs.getString("USAGE_INTERNET_COUNT"));
		cavo.setUsageInternetNote(rs.getString("USAGE_INTERNET_NOTE"));
		cavo.setUsageFilmCheck(rs.getString("USAGE_FILM_CHECK"));
		cavo.setUsageFilmCount(rs.getString("USAGE_FILM_COUNT"));
		cavo.setUsageFilmNote(rs.getString("USAGE_FILM_NOTE"));
		cavo.setUsageOthersCheck(rs.getString("USAGE_OTHERS_CHECK"));
		cavo.setUsageOthersCount(rs.getString("USAGE_OTHERS_COUNT"));
		cavo.setUsageOthersNote(rs.getString("USAGE_OTHERS_NOTE"));
		cavo.setApplyBoft(rs.getString("APPLY_BOFT"));
		cavo.setApplyMeettaiwan(rs.getString("APPLY_MEETTAIWAN"));
		cavo.setApprovedStatus(rs.getInt("APPROVAL_STATUS"));
		cavo.setApprovedDate(rs.getTimestamp("APPROVED_DATE"));
		cavo.setDeleted(rs.getString("DELETED"));
		
		return cavo;
	}

}
