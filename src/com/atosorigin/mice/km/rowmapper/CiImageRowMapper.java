package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.CiImageVO;

public class CiImageRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CiImageVO civo = new CiImageVO();
		civo.setId(rs.getString("ID"));
		civo.setSerialNumber(rs.getString("SERIAL_NUMBER"));
		civo.setFileName(rs.getString("FILE_NAME"));
		civo.setDownloadString(rs.getString("DOWNLOAD_STRING"));
		civo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
		civo.setExpiredTime(rs.getTimestamp("EXPIRED_TIME"));
		return civo;
	}

}
