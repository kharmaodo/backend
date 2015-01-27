package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.MembersDAO;
import com.atosorigin.mice.km.rowmapper.DocMemberRowMapper;
import com.atosorigin.mice.km.rowmapper.MemberRowMapper;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.MembersVO;

public class MembersDAOImpl implements MembersDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(MembersVO mvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO MEMBERS (ID, ");
		sql.append("		             ACCOUNT, ");
		sql.append("		             PASSWORD, ");
		sql.append("		             EMAIL, ");
		sql.append("					 GENDER, ");
		sql.append("					 ACTIVATED, ");
		sql.append("					 GROUP_ID, ");
		sql.append("					 CREATE_TIME) ");
		sql.append("VALUES (?,?,MD5(?),?,?,?,?,?)");
		
		Object[] obj = new Object[] {mvo.getId(),
				 					 mvo.getAccount(),
				 					 mvo.getPassword(),
				 					 mvo.getEmail(),
				 					 mvo.getGender(),
				 					 mvo.getActivated(),
				 					 mvo.getGroupId(),
				 					 mvo.getCreateTime()};

		return this.jdbcTemplate.update(sql.toString(), obj);

	}

	@Override
	public int getMemberNum(String account) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MEMBERS WHERE EMAIL = ?");
		Object[] obj = new Object[] {account};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public int update(String account, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MEMBERS SET PASSWORD = MD5(?) WHERE ACCOUNT = ?");
		
		Object[] obj = new Object[] {password,
				 					 account};

		return this.jdbcTemplate.update(sql.toString(), obj);

	}

	@Override
	public List<MembersVO> getMember(int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		MemberRowMapper mrm = new MemberRowMapper();
		sql.append("SELECT * FROM MEMBERS ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {startPosition,
				                     pageRows};
		List<MembersVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		if(mvos == null) {
			return null;
		} else {
			return mvos;
		}
	}

	@Override
	public List<MembersVO> getMemberBlur(String email, String name,
			int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		MemberRowMapper mrm = new MemberRowMapper();
		email = "%" + email + "%";
		name = "%" + name + "%";
		sql.append("SELECT * FROM MEMBERS ");
		sql.append("WHERE EMAIL LIKE ? AND NAME LIKE ? ");
		sql.append("ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {email,
				                     name,
									 startPosition,
				                     pageRows};
		List<MembersVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		if(mvos == null) {
			return null;
		} else {
			return mvos;
		}
	}

	@Override
	public List<MembersVO> getMemberBlur(String email, String name,
			String groupId, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		MemberRowMapper mrm = new MemberRowMapper();
		email = "%" + email + "%";
		name = "%" + name + "%";
		sql.append("SELECT * FROM MEMBERS ");
		sql.append("WHERE GROUP_ID = ? AND ACCOUNT LIKE ? AND NAME LIKE ? ");
		sql.append("ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {groupId,
                    				 email,
                    				 name,
                    				 startPosition,
                    				 pageRows};
		
		List<MembersVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		if(mvos == null) {
			return null;
		} else {
			return mvos;
		}
	}

	@Override
	public int getMemberBlurNum(String email, String name) {
		StringBuilder sql = new StringBuilder();
		email = "%" + email + "%";
		name = "%" + name + "%";
		sql.append("SELECT COUNT(*) FROM MEMBERS ");
		sql.append("WHERE EMAIL LIKE ? AND NAME LIKE ? ");
		Object[] obj = new Object[] {email,
				                     name};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public int getMemberBlurNum(String email, String name, String groupId) {
		StringBuilder sql = new StringBuilder();
		email = "%" + email + "%";
		name = "%" + name + "%";
		sql.append("SELECT COUNT(*) FROM MEMBERS ");
		sql.append("WHERE GROUP_ID = ? AND ACCOUNT LIKE ? AND NAME LIKE ? ");
		Object[] obj = new Object[] {groupId,
                    				 email,
                    				 name};
		
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public int getMemberNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM MEMBERS");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public int update(MembersVO mvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MEMBERS SET ACCOUNT = ?, ");
		sql.append("                   PASSWORD = ?, ");
		sql.append("                   EMAIL = ?, ");
		sql.append("                   NAME = ?, ");
		sql.append("                   IDENTIFICATION = ?, ");
		sql.append("                   GENDER = ?, ");
		sql.append("                   EDUCATION_CATEGORY_ID = ?, ");
		sql.append("                   ADDRESS = ?, ");
		sql.append("                   PHONE = ?, ");
		sql.append("                   REGION_CATEGORY_ID = ?, ");
		sql.append("                   VENDOR_CATEGORY_ID = ?, ");
		sql.append("                   POSITION_CATEGORY_ID = ?, ");
		sql.append("                   LOCALE_ID = ?, ");
		sql.append("                   GROUP_ID = ?, ");
		sql.append("                   RECEIVE_INFO = ?, ");
		sql.append("                   VENDOR_CHECKED = ?, ");
		sql.append("                   CONFIRM_SENT = ?, ");
		sql.append("                   ACTIVATE_STRING = ?, ");
		sql.append("                   ACTIVATED = ?, ");
		sql.append("				   CREATE_TIME = ?, ");
		sql.append("                   VENDOR_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {mvo.getAccount(),
				  					 mvo.getPassword(),
				  					 mvo.getEmail(),
				  					 mvo.getName(),
				  					 mvo.getIdentifivation(),
				  					 mvo.getGender(),
				  					 mvo.getEducationCategoryId(),
				  					 mvo.getAddress(),
				  					 mvo.getPhone(),
				  					 mvo.getRegionCategoryId(),
				  					 mvo.getVendorCategoryId(),
				  					 mvo.getPositionCategoryId(),
				  					 mvo.getLocaleId(),
				  					 mvo.getGroupId(),
				  					 mvo.getReceiveInfo(),
				  					 mvo.getVendorChecked(),
				  					 mvo.getConfirmSent(),
				  					 mvo.getActivateString(),
				  					 mvo.getActivated(),
				  					 mvo.getCreateTime(),
				  					 mvo.getVendorId(),
				  					 mvo.getId()};
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("MEMBERS:update " + row + " row(s):Account = " + mvo.getAccount());
		return row;
	}

	@Override
	public MembersVO getMember(String id) {
		StringBuilder sql = new StringBuilder();
		MemberRowMapper mrm = new MemberRowMapper();
		sql.append("SELECT * FROM MEMBERS ");
		sql.append("WHERE ID = ? ");
		Object[] obj = new Object[] {id};
		
		List<MembersVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		if(mvos == null) {
			return null;
		} else {
			return mvos.get(0);
		}
	}

	@Override
	public List<MembersVO> getMemberBlurNotAssigned(String email,
			String name, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		MemberRowMapper mrm = new MemberRowMapper();
		email = "%" + email + "%";
		name = "%" + name + "%";
		sql.append("SELECT * FROM MEMBERS ");
		sql.append("WHERE GROUP_ID is NULL AND ACCOUNT LIKE ? AND NAME LIKE ? ");
		sql.append("ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {email,
				                     name,
									 startPosition,
				                     pageRows};
		List<MembersVO> mvos = this.jdbcTemplate.query(sql.toString(), obj, mrm);
		if(mvos == null) {
			return null;
		} else {
			return mvos;
		}
	}

	@Override
	public int getMemberBlurNotAssignedNum(String email, String name) {
		StringBuilder sql = new StringBuilder();
		email = "%" + email + "%";
		name = "%" + name + "%";
		sql.append("SELECT COUNT(*) FROM MEMBERS ");
		sql.append("WHERE GROUP_ID is NULL AND ACCOUNT LIKE ? AND NAME LIKE ? ");
		Object[] obj = new Object[] {email,
				                     name};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
