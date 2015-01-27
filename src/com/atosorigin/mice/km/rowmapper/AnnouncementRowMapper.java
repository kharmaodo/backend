package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.AnnouncementVO;

public class AnnouncementRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		AnnouncementVO vo = new AnnouncementVO();
		
		vo.setActivated(rs.getString("ACTIVATED"));
		vo.setAttachmentId(rs.getString("ATTACHMENT_ID"));
		vo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
		vo.setCreator(rs.getString("CREATOR"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setId(rs.getString("ID"));
		vo.setModifier(rs.getString("MODIFIER"));
		vo.setModifyDate(rs.getTimestamp("MODIFY_DATE"));
		vo.setOwnerId(rs.getString("OWNER_ID"));
		vo.setPhoto(rs.getString("PHOTO"));
		vo.setPublishDate(rs.getTimestamp("PUBLISH_DATE"));
		vo.setShelveDate(rs.getTimestamp("SHELVE_DATE"));
		vo.setShowPlace(rs.getString("SHOW_PLACE"));
		vo.setSource(rs.getString("SOURCE"));
		vo.setUnshelveDate(rs.getTimestamp("UNSHELVE_DATE"));
		vo.setVerified(rs.getString("VERIFIED"));
		vo.setVerifier(rs.getString("VERIFIER"));
		vo.setVerifyDate(rs.getTimestamp("VERIFY_DATE"));
		vo.setVerifyNote(rs.getString("VERIFY_NOTE"));
		
		return vo;
	}
}
