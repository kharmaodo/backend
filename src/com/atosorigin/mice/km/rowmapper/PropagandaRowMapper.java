package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.PropagandaVO;

public class PropagandaRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		PropagandaVO vo = new PropagandaVO();
		vo.setId(rs.getString("ID"));
		vo.setSerialNumber(rs.getString("SERIAL_NUMBER"));
		vo.setApplyOrg(rs.getString("APPLY_ORG"));
		vo.setContact(rs.getString("CONTACT"));
		vo.setPhone(rs.getString("PHONE"));
		vo.setEmail(rs.getString("EMAIL"));
		vo.setApplyDate(rs.getTimestamp("APPLY_DATE"));
		vo.setMedicalConferenceCheck(rs.getString("MEDICAL_CONFERENCE_CHECK"));
		vo.setMedicalConferenceCount(rs.getString("MEDICAL_CONFERENCE_COUNT"));
		vo.setMedicalConferenceNote(rs.getString("MEDICAL_CONFERENCE_NOTE"));
		vo.setConferenceCheck(rs.getString("CONFERENCE_CHECK"));
		vo.setConferenceCount(rs.getString("CONFERENCE_COUNT"));
		vo.setConferenceNote(rs.getString("CONFERENCE_NOTE"));
		vo.setAssociationMeetingCheck(rs.getString("ASSOCIATION_MEETING_CHECK"));
		vo.setAssociationMeetingCount(rs.getString("ASSOCIATION_MEETING_COUNT"));
		vo.setAssociationMeetingNote(rs.getString("ASSOCIATION_MEETING_NOTE"));
		vo.setCorporateMeetingCheck(rs.getString("CORPORATE_MEETING_CHECK"));
		vo.setCorporateMeetingCount(rs.getString("CORPORATE_MEETING_COUNT"));
		vo.setCorporateMeetingNote(rs.getString("CORPORATE_MEETING_NOTE"));
		vo.setManualCheck(rs.getString("MANUAL_CHECK"));
		vo.setManualCount(rs.getString("MANUAL_COUNT"));
		vo.setManualNote(rs.getString("MANUAL_NOTE"));
		vo.setVideoCheck(rs.getString("VIDEO_CHECK"));
		vo.setVideoCount(rs.getString("VIDEO_COUNT"));
		vo.setVideoNote(rs.getString("VIDEO_NOTE"));
		vo.setOtherCheck(rs.getString("OTHER_CHECK"));
		vo.setOtherCount(rs.getString("OTHER_COUNT"));
		vo.setOtherNote(rs.getString("OTHER_NOTE"));
		vo.setApprovalStatus(rs.getInt("APPROVAL_STATUS"));
		vo.setApprovedDate(rs.getTimestamp("APPROVED_DATE"));
		vo.setDeleted(rs.getString("DELETED"));
		return vo;
	}

}
