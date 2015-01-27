package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.AttachmentExtVO;

public class AttachmentExtRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		AttachmentExtVO aevo = new AttachmentExtVO();
		aevo.setId(rs.getInt("ID"));
		aevo.setAttachmentId(rs.getString("ATTACHMENT_ID"));
		aevo.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
		aevo.setApproval1(rs.getString("APPROVAL_1"));
		aevo.setApproval2(rs.getString("APPROVAL_2"));
		aevo.setApproval3(rs.getString("APPROVAL_3"));
		aevo.setGroupId(rs.getString("GROUP_IDS"));
		aevo.setDownloadable(rs.getString("DOWNLOADABLE"));
		aevo.setCopyRight(rs.getString("COPY_RIGHT"));
		aevo.setCreateTime(rs.getDate("CREATE_TIME"));
		aevo.setCreateBy(rs.getString("CREATE_BY"));
		aevo.setModifyTime(rs.getDate("MODIFY_TIME"));
		aevo.setModifyBy(rs.getString("MODIFY_BY"));
		return aevo;
	}

}
