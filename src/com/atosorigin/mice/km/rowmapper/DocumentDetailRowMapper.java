package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.DocumentDetailVO;

public class DocumentDetailRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		DocumentDetailVO ddvo = new DocumentDetailVO();
		ddvo.setId(rs.getString("ID"));
		ddvo.setVisible(rs.getString("VISIBLE"));
		ddvo.setTopic(rs.getString("TOPIC"));
		ddvo.setSource(rs.getString("SOURCE"));
		ddvo.setLocale(rs.getString("LOCALE"));
		ddvo.setDocumentId(rs.getString("DOCUMENT_ID"));
		return ddvo;
	}

}
