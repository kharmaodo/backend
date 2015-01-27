package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.AttachmentVO;

public class AttachmentRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		AttachmentVO avo = new AttachmentVO();
		avo.setId(rs.getString("ID"));
		avo.setTitle(rs.getString("TITLE"));
		avo.setOriginName(rs.getString("ORIGIN_NANME"));
		avo.setType(rs.getString("TYPE"));
		avo.setPath(rs.getString("PATH"));
		avo.setPressReleaseId(rs.getString("PRESS_RELEASE_ID"));
		avo.setCategoryGroup(rs.getInt("CATEGORY_GROUP"));
		return avo; 
	}

}
