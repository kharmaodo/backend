package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.vo.DocLogVO;

public class DocLogRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		DocLogVO dlvo = new DocLogVO();
		dlvo.setId(rs.getInt("ID"));
		dlvo.setAccount(rs.getString("ACCOUNT"));
		dlvo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		dlvo.setFromIp(rs.getString("FROM_IP"));
		dlvo.setWhat(rs.getString("WHAT"));
		return dlvo;
	}

}
