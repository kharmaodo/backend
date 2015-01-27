package com.atosorigin.mice.km.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atosorigin.mice.km.vo.MembersVO;

public class MemberRowMapper implements RowMapper {  
    public Object mapRow(ResultSet rs, int index) throws SQLException {  
        MembersVO mvo = new MembersVO();
        mvo.setAccount(rs.getString("ACCOUNT"));
        mvo.setActivated(rs.getString("ACTIVATED"));
        mvo.setActivateString(rs.getString("ACTIVATE_STRING"));
        mvo.setAddress(rs.getString("ADDRESS"));
        mvo.setConfirmSent(rs.getString("CONFIRM_SENT"));
        mvo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
        mvo.setEducationCategoryId(rs.getString("EDUCATION_CATEGORY_ID"));
        mvo.setEmail(rs.getString("EMAIL"));
        mvo.setGender(rs.getString("GENDER"));
        mvo.setGroupId(rs.getString("GROUP_ID"));
        mvo.setId(rs.getString("ID"));
        mvo.setIdentifivation(rs.getString("IDENTIFICATION"));
        mvo.setLocaleId(rs.getString("LOCALE_ID"));
        mvo.setName(rs.getString("NAME"));
        mvo.setPassword(rs.getString("PASSWORD"));
        mvo.setPhone(rs.getString("PHONE"));
        mvo.setPositionCategoryId(rs.getString("POSITION_CATEGORY_ID"));
        mvo.setReceiveInfo(rs.getString("RECEIVE_INFO"));
        mvo.setRegionCategoryId(rs.getString("REGION_CATEGORY_ID"));
        mvo.setVendorCategoryId(rs.getString("VENDOR_CATEGORY_ID"));
        mvo.setVendorChecked(rs.getString("VENDOR_CHECKED"));
        mvo.setVendorId(rs.getString("VENDOR_ID"));
        return mvo;  
    }  
}  
