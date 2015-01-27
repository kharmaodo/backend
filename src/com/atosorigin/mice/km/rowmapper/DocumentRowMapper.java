package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.DocumentVO;

public class DocumentRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		DocumentVO dvo = new DocumentVO();
		dvo.setId(rs.getString("ID"));
		dvo.setDescription(rs.getString("DESCRIPTION"));
		dvo.setActivated(rs.getString("ACTIVATED"));
		dvo.setVerified(rs.getString("VERIFIED"));
		dvo.setCreator(rs.getString("CREATOR"));
		dvo.setCreateDate(rs.getDate("CREATE_DATE"));
		dvo.setModifier(rs.getString("MODIFIER"));
		dvo.setModifyDate(rs.getDate("MODIFY_DATE"));
		dvo.setVerifier(rs.getString("VERIFIER"));
		dvo.setVerifyDate(rs.getDate("VERIFY_DATE"));
		dvo.setVerifyNote(rs.getString("VERIFY_NOTE"));
		dvo.setOwnerId(rs.getString("OWNER_ID"));
		dvo.setDocumentCategoryId(rs.getString("DOCUMENT_CATEGORY_ID"));
		dvo.setAttachmentId(rs.getString("ATTACHMENT_ID"));
		dvo.setIssuuId(rs.getString("ISSUU_ID"));
		return dvo;
	}

}
