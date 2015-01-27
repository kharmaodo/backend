package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.bean.DocumentBean;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public class DocumentListAORowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		DocumentBean db = new DocumentBean();
		db.setDocumentId(rs.getString("DOCUMENT_ID"));
		db.setDescription(rs.getString("DESCRIPTION"));
		db.setActivated(rs.getString("ACTIVATED"));
		db.setCreateDate(rs.getTimestamp("CREATE_DATE"));
		db.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
		db.setGroupIds(rs.getString("GROUP_IDS"));
		db.setParentId(rs.getString("PARENT_ID"));
		db.setCategoerName(rs.getString("DC_NAME"));
		return db;
	}

}
