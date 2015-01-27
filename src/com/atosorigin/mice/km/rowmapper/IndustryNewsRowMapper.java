package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.IndustryNewsVO;

public class IndustryNewsRowMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		IndustryNewsVO vo = new IndustryNewsVO();
		
		vo.setId(rs.getString("ID"));
		vo.setDescription(rs.getString("DESCRIPTION"));
		vo.setUrl(rs.getString("URL"));
		vo.setSource(rs.getString("SOURCE"));
		vo.setPublishDate(rs.getTimestamp("PUBLISH_DATE"));
		vo.setShelveDate(rs.getTimestamp("SHELVE_DATE"));
		vo.setUnshelveDate(rs.getTimestamp("UNSHELVE_DATE"));
		vo.setShowPlace(rs.getString("SHOW_PLACE"));
		vo.setActivated(rs.getString("ACTIVATED"));
		vo.setVerified(rs.getString("VERIFIED"));
		vo.setCreator(rs.getString("CREATOR"));
		vo.setCreateDate(rs.getTimestamp("CREATE_DATE"));
		vo.setModifier(rs.getString("MODIFIER"));
		vo.setModifyDate(rs.getTimestamp("MODIFY_DATE"));
		vo.setVerifier(rs.getString("VERIFIER"));
		vo.setVerifyDate(rs.getTimestamp("VERIFY_DATE"));
		vo.setVerifyNote(rs.getString("VERIFY_NOTE"));
		vo.setOwnerId(rs.getString("OWNER_ID"));
		vo.setPhoto(rs.getString("PHOTO"));
		vo.setIndustryNewsCategoryId(rs.getString("INDUSTRY_NEWS_CATEGORY_ID"));
		vo.setContact(rs.getString("CONTACT"));
		vo.setContactEmail(rs.getString("CONTACT_EMAIL"));
		vo.setContactTel(rs.getString("CONTACT_TEL"));
		return vo;
	}

}
