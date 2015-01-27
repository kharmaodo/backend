package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.DocMembersDAO;
import com.atosorigin.mice.km.rowmapper.DocMemberRowMapper;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class DocMembersDAOImpl implements DocMembersDAO {
	
	private static Logger logger = Logger.getLogger(DocMembersDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int update(int id, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DOC_MEMBERS SET PASSWORD = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {password,
				  					 id};
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("DOC_MEMBERS:update password " + row + " row(s):ID = " + id);
		return row;
	}

	public int delete(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM DOC_MEMBERS WHERE ID = ?");
		Object[] obj = new Object[] {id};
		int row = jdbcTemplate.update(sql.toString(), obj);			
		logger.debug("DOC_MEMBERS:delete " + row + " row(s):ID = " + id);
		return row;
	}

	public DocMembersVO getDocMember(int id) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT * FROM DOC_MEMBERS WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos.size() == 0) {
			return null;
		} else {
			return dmvos.get(0);
		}
	}

	public DocMembersVO getDocMember(String account) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT * FROM DOC_MEMBERS WHERE ACCOUNT = ?");
		Object[] obj = new Object[] {account};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos.size() == 0) {
			return null;
		} else {
			return dmvos.get(0);
		}
	}

	public DocMembersVO getDocMember(String account, String password) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT * FROM DOC_MEMBERS WHERE ACCOUNT = ? AND PASSWORD = ?");
		Object[] obj = new Object[] {account,
				                     password};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos.size() == 0) {
			return null;
		} else {
			return dmvos.get(0);
		}
	}

	public int insert(DocMembersVO dmvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DOC_MEMBERS (ACCOUNT, ");
		sql.append("                         PASSWORD, ");
		sql.append("                         EMAIL, ");
		sql.append("                         NAME, ");
		sql.append("                         IDENTIFICATION, ");
		sql.append("                         GENDER, ");
		sql.append("                         EDUCATION_CATEGORY_ID, ");
		sql.append("                         ADDRESS, ");
		sql.append("                         PHONE, ");
		sql.append("                         REGION_CATEGORY_ID, ");
		sql.append("                         VENDOR_CATEGORY_ID, ");
		sql.append("                         POSITION_CATEGORY_ID, ");
		sql.append("                         LOCALE_ID, ");
		sql.append("                         GROUP_ID, ");
		sql.append("                         RECEIVE_INFO, ");
		sql.append("                         VENDOR_CHECKED, ");
		sql.append("                         CONFIRM_SENT, ");
		sql.append("                         ACTIVATE_STRING, ");
		sql.append("                         ACTIVATED, ");
		sql.append("						 CREATE_TIME, ");
		sql.append("                         VENDOR_ID) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[] {dmvo.getAccount(),
				  					 dmvo.getPassword(),
				  					 dmvo.getEmail(),
				  					 dmvo.getName(),
				  					 dmvo.getIdentifivation(),
				  					 dmvo.getGender(),
				  					 dmvo.getEducationCategoryId(),
				  					 dmvo.getAddress(),
				  					 dmvo.getPhone(),
				  					 dmvo.getRegionCategoryId(),
				  					 dmvo.getVendorCategoryId(),
				  					 dmvo.getPositionCategoryId(),
				  					 dmvo.getLocaleId(),
				  					 dmvo.getGroupId(),
				  					 dmvo.getReceiveInfo(),
				  					 dmvo.getVendorChecked(),
				  					 dmvo.getConfirmSent(),
				  					 dmvo.getActivateString(),
				  					 dmvo.getActivated(),
				  					 dmvo.getCreateTime(),
				  					 dmvo.getVendorId()};
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("DOC_MEMBERS:insert " + row + " row(s) :ID = " + dmvo.getAccount());
		return row;

	}

	public int update(DocMembersVO dmvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DOC_MEMBERS SET ACCOUNT = ?, ");
		sql.append("                       PASSWORD = ?, ");
		sql.append("                       EMAIL = ?, ");
		sql.append("                       NAME = ?, ");
		sql.append("                       IDENTIFICATION = ?, ");
		sql.append("                       GENDER = ?, ");
		sql.append("                       EDUCATION_CATEGORY_ID = ?, ");
		sql.append("                       ADDRESS = ?, ");
		sql.append("                       PHONE = ?, ");
		sql.append("                       REGION_CATEGORY_ID = ?, ");
		sql.append("                       VENDOR_CATEGORY_ID = ?, ");
		sql.append("                       POSITION_CATEGORY_ID = ?, ");
		sql.append("                       LOCALE_ID = ?, ");
		sql.append("                       GROUP_ID = ?, ");
		sql.append("                       RECEIVE_INFO = ?, ");
		sql.append("                       VENDOR_CHECKED = ?, ");
		sql.append("                       CONFIRM_SENT = ?, ");
		sql.append("                       ACTIVATE_STRING = ?, ");
		sql.append("                       ACTIVATED = ?, ");
		sql.append("					   CREATE_TIME = ?, ");
		sql.append("                       VENDOR_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {dmvo.getAccount(),
				  					 dmvo.getPassword(),
				  					 dmvo.getEmail(),
				  					 dmvo.getName(),
				  					 dmvo.getIdentifivation(),
				  					 dmvo.getGender(),
				  					 dmvo.getEducationCategoryId(),
				  					 dmvo.getAddress(),
				  					 dmvo.getPhone(),
				  					 dmvo.getRegionCategoryId(),
				  					 dmvo.getVendorCategoryId(),
				  					 dmvo.getPositionCategoryId(),
				  					 dmvo.getLocaleId(),
				  					 dmvo.getGroupId(),
				  					 dmvo.getReceiveInfo(),
				  					 dmvo.getVendorChecked(),
				  					 dmvo.getConfirmSent(),
				  					 dmvo.getActivateString(),
				  					 dmvo.getActivated(),
				  					 dmvo.getCreateTime(),
				  					 dmvo.getVendorId(),
				  					 dmvo.getId()};
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("DOC_MEMBERS:update " + row + " row(s):Account = " + dmvo.getAccount());
		return row;
	}
	
	public List<DocMembersVO> getDocMember(int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT * FROM DOC_MEMBERS ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {startPosition,
				                     pageRows};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos == null) {
			return null;
		} else {
			return dmvos;
		}
	}
	
	public int getDocMemberNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) FROM DOC_MEMBERS");
		int rows = this.jdbcTemplate.queryForInt(sql.toString());
		return rows;
	}
	
	public int active(int id, String activeString) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DOC_MEMBERS SET ACTIVATED = 'Y' ");
		sql.append("WHERE ID = ? AND ACTIVATE_STRING = ? AND ACTIVATED = 'N'");
		Object[] obj = new Object[] {id,
				  					 activeString};
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.debug("DOC_MEMBERS:activate " + row + " row(s):ID = " + id);
		return row;
	}
	
	public List<DocMembersVO> getDocMemberBlur(String account, String name, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		account = "%" + account + "%";
		name = "%" + name + "%";
		sql.append("SELECT * FROM DOC_MEMBERS ");
		sql.append("WHERE ACCOUNT LIKE ? AND (NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?) ");
		sql.append("ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {account,
				                     name,
				                     name,
									 startPosition,
				                     pageRows};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos == null) {
			return null;
		} else {
			return dmvos;
		}
	}
	
	public List<DocMembersVO> getDocMemberBlurNotAssigned(String account, String name, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		account = "%" + account + "%";
		name = "%" + name + "%";
		sql.append("SELECT * FROM DOC_MEMBERS ");
		sql.append("WHERE GROUP_ID is NULL AND ACCOUNT LIKE ? AND (NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?) ");
		sql.append("ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {account,
				                     name,
				                     name,
									 startPosition,
				                     pageRows};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos == null) {
			return null;
		} else {
			return dmvos;
		}
	}
	
	public List<DocMembersVO> getDocMemberBlur(String account, String name, String groupId, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		account = "%" + account + "%";
		name = "%" + name + "%";
		sql.append("SELECT * FROM DOC_MEMBERS ");
		sql.append("WHERE GROUP_ID = ? AND ACCOUNT LIKE ? AND (NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?) ");
		sql.append("ORDER BY CREATE_TIME DESC LIMIT ?, ?");
		Object[] obj = new Object[] {groupId,
                    				 account,
                    				 name,
                    				 name,
                    				 startPosition,
                    				 pageRows};
		
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos == null) {
			return null;
		} else {
			return dmvos;
		}
	}
	
	public int getDocMemberBlurNum(String account, String name) {
		account = "%" + account + "%";
		name = "%" + name + "%";
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT count(*) FROM DOC_MEMBERS WHERE ACCOUNT LIKE ? AND (NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?)");
		Object[] obj = new Object[] {account,
				                     name,
				                     name};
		int rows = this.jdbcTemplate.queryForInt(sql.toString(), obj);
		return rows;
	}
	
	public int getDocMemberBlurNum(String account, String name, String groupId) {
		account = "%" + account + "%";
		name = "%" + name + "%";
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT count(*) FROM DOC_MEMBERS WHERE GROUP_ID = ? AND ACCOUNT LIKE ? AND (NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?)");
		Object[] obj = new Object[] {groupId,
				                     account,
				                     name,
				                     name};
		int rows = this.jdbcTemplate.queryForInt(sql.toString(), obj);
		return rows;
	}
	
	public int getDocMemberBlurNotAssignedNum(String account, String name) {
		account = "%" + account + "%";
		name = "%" + name + "%";
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT count(*) FROM DOC_MEMBERS WHERE GROUP_ID IS NULL AND ACCOUNT LIKE ? AND (NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?)");
		Object[] obj = new Object[] {account,
				                     name,
				                     name};
		int rows = this.jdbcTemplate.queryForInt(sql.toString(), obj);
		return rows;
	}

	@Override
	public DocMembersVO getDocMember(String account, String password, String activated) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		sql.append("SELECT * FROM DOC_MEMBERS WHERE ACCOUNT = ? AND PASSWORD = ? AND ACTIVATED = ? AND GROUP_ID IS NOT NULL");
		Object[] obj = new Object[] {account,
				                     password,
				                     activated};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos.size() == 0) {
			return null;
		} else {
			return dmvos.get(0);
		}
	}

	@Override
	public int getDocMemberNotAssignedNum() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM DOC_MEMBERS WHERE GROUP_ID IS NULL");
		return this.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public List<DocMembersVO> getDocMemberBykey(String keyword, int startPosition) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		keyword = "%" + keyword + "%";
		sql.append("SELECT * FROM DOC_MEMBERS WHERE GROUP_ID = '5' AND ");
		sql.append("(NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?) ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {keyword,
									 keyword,
									 startPosition,
									 Constants.VENDOR_PAGE_ROWS};
		List<DocMembersVO> dmvos = this.jdbcTemplate.query(sql.toString(), obj, dmrm);
		if(dmvos == null) {
			return null;
		} else {
			return dmvos;
		}
	}
	
	@Override
	public int getDocMemberBykeyNum(String keyword) {
		StringBuilder sql = new StringBuilder();
		DocMemberRowMapper dmrm = new DocMemberRowMapper();
		keyword = "%" + keyword + "%";
		sql.append("SELECT COUNT(*) FROM DOC_MEMBERS WHERE GROUP_ID = '5' AND ");
		sql.append("(NAME LIKE ? OR VENDOR_CATEGORY_ID LIKE ?)");
		Object[] obj = new Object[] {keyword,
									 keyword};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
}
