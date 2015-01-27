package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.DocumentCategoryVO;

public class DocumentCategoryRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		DocumentCategoryVO dcvo = new DocumentCategoryVO();
		dcvo.setId(rs.getString("ID"));
		dcvo.setDescription(rs.getString("DESCRIPTION"));
		dcvo.setLevelIndex(rs.getInt("LEVEL_INDEX"));
		dcvo.setCategoryGroup(rs.getInt("CATEGORY_GROUP"));
		dcvo.setRank(rs.getInt("RANK"));
		dcvo.setParentId(rs.getString("PARENT_ID"));
		return dcvo;
	}

}
