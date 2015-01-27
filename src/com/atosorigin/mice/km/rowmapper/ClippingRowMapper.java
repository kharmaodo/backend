package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.ClippingVO;

public class ClippingRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		ClippingVO vo = new ClippingVO();
		vo.setId(rs.getInt("ID"));
		vo.setTitle(rs.getString("TITLE"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setType(rs.getString("TYPE"));
		vo.setIssueNo(rs.getString("ISSUE_NO"));
		vo.setPublishDate(rs.getTimestamp("PUBLISH_DATE"));
		vo.setUrl(rs.getString("URL"));
		vo.setPath(rs.getString("PATH"));
		vo.setAttachmentId(rs.getString("ATTACHMENT_ID"));
		vo.setPublisher(rs.getString("PUBLISHER"));
		vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		vo.setCreator(rs.getString("CREATOR"));
		vo.setVisible(rs.getString("VISIBLE"));
		return vo;
	}

}
