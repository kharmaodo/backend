package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.bean.AttachmentComboBean;

public class AttachmentComboRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		AttachmentComboBean avb = new AttachmentComboBean();
		avb.setActivated(rs.getString("ACTIVATED"));
		avb.setApproval1(rs.getString("APPROVAL1"));
		avb.setApproval2(rs.getString("APPROVAL2"));
		avb.setApproval3(rs.getString("APPROVAL3"));
		avb.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
		avb.setAttachmentExtId(rs.getInt("ATTACHMENT_EXT_ID"));
		avb.setAttachmentId(rs.getString("ATTACHMENT_ID"));
		avb.setCategoryGroup(rs.getInt("CATEGORY_GROUP"));
		avb.setCopyRight(rs.getString("COPY_RIGHT"));
		avb.setCreateBy(rs.getString("CREATE_BY"));
		avb.setCreateDate(rs.getDate("CREATE_DATE"));
		avb.setCreateTime(rs.getDate("CREATE_TIME"));
		avb.setCreator(rs.getString("CREATOR"));
		avb.setDocumentCategoryDescription(rs.getString("DOCUMENT_CATEGORY_DESCRIPTION"));
		avb.setDocumentCategoryId(rs.getString("DOCUMENT_CATEGORY_ID"));
		avb.setDocumentDescription(rs.getString("DOCUMENT_DESCRIPTION"));
		avb.setDocumentId(rs.getString("DOCUMENT_ID"));
		avb.setDownloadable(rs.getString("DOWNLOADABLE"));
		avb.setGroupIds(rs.getString("GROUP_IDS"));
		avb.setIssuuId(rs.getString("ISSUU_ID"));
		avb.setLevelIndex(rs.getInt("LEVEL_INDEX"));
		avb.setModifier(rs.getString("MODIFIER"));
		avb.setModifyBy(rs.getString("MODIFY_BY"));
		avb.setModifyDate(rs.getDate("MODIFY_DATE"));
		avb.setModifyTime(rs.getDate("MODIFY_TIME"));
		avb.setOriginName(rs.getString("ORIGIN_NAME"));
		avb.setOwnerId(rs.getString("OWNER_ID"));
		avb.setParentId(rs.getString("PARENT_ID"));
		avb.setPath(rs.getString("PATH"));
		avb.setPressReleaseId(rs.getString("PRESS_RELEASE_ID"));
		avb.setRank(rs.getInt("RANK"));
		avb.setTitle(rs.getString("TITLE"));
		avb.setType(rs.getString("TYPE"));
		avb.setVerified(rs.getString("VERIFIED"));
		avb.setVerifier(rs.getString("VERIFIER"));
		avb.setVerifyDate(rs.getDate("VERIFY_DATE"));
		avb.setVerifyNote(rs.getString("VERIFY_NOTE"));
		return avb;
	}

}
