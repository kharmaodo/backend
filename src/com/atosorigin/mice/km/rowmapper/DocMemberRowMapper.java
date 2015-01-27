package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.DocMembersVO;

public class DocMemberRowMapper implements RowMapper {  
    public Object mapRow(ResultSet rs, int index) throws SQLException {  
        DocMembersVO dmvo = new DocMembersVO();
        dmvo.setAccount(rs.getString("ACCOUNT"));
        dmvo.setActivated(rs.getString("ACTIVATED"));
        dmvo.setActivateString(rs.getString("ACTIVATE_STRING"));
        dmvo.setAddress(rs.getString("ADDRESS"));
        dmvo.setConfirmSent(rs.getString("CONFIRM_SENT"));
        dmvo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        dmvo.setEducationCategoryId(rs.getString("EDUCATION_CATEGORY_ID"));
        dmvo.setEmail(rs.getString("EMAIL"));
        dmvo.setGender(rs.getString("GENDER"));
        dmvo.setGroupId(rs.getString("GROUP_ID"));
        dmvo.setId(rs.getInt("ID"));
        dmvo.setIdentifivation(rs.getString("IDENTIFICATION"));
        dmvo.setLocaleId(rs.getString("LOCALE_ID"));
        dmvo.setName(rs.getString("NAME"));
        dmvo.setPassword(rs.getString("PASSWORD"));
        dmvo.setPhone(rs.getString("PHONE"));
        dmvo.setPositionCategoryId(rs.getString("POSITION_CATEGORY_ID"));
        dmvo.setReceiveInfo(rs.getString("RECEIVE_INFO"));
        dmvo.setRegionCategoryId(rs.getString("REGION_CATEGORY_ID"));
        dmvo.setVendorCategoryId(rs.getString("VENDOR_CATEGORY_ID"));
        dmvo.setVendorChecked(rs.getString("VENDOR_CHECKED"));
        dmvo.setVendorId(rs.getString("VENDOR_ID"));
        return dmvo;  
    }  
}  
