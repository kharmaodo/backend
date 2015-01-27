package com.atosorigin.mice.km.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.rowmapper.AttachmentExtRowMapper;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;

public class AttachmentExtDAOImpl implements AttachmentExtDAO {
private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public int insert(AttachmentExtVO aevo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ATTACHMENT_EXT (ATTACHMENT_ID, ");
		sql.append("						     APPROVAL_STATUS, ");
		sql.append("						     APPROVAL_1, ");
		sql.append("						     APPROVAL_2, ");
		sql.append("						     APPROVAL_3, ");
		sql.append("						     GROUP_IDS, ");
		sql.append("						     DOWNLOADABLE, ");
		sql.append("						     COPY_RIGHT, ");
		sql.append("                     		 CREATE_TIME, ");
		sql.append("                     		 CREATE_BY, ");
		sql.append("                     		 MODIFY_TIME, ");
		sql.append("                     		 MODIFY_BY) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[] {aevo.getAttachmentId(),
									 aevo.getApprovalStatus(),
									 aevo.getApproval1(),
									 aevo.getApproval2(),
									 aevo.getApproval3(),
									 aevo.getGroupId(),
									 aevo.getDownloadable(),
									 aevo.getCopyRight(),
									 aevo.getCreateTime(),
									 aevo.getCreateBy(),
									 aevo.getModifyTime(),
									 aevo.getModifyBy()};
		
		 return this.jdbcTemplate.update(sql.toString(), obj);
	}

	
	@Override
	public int update(AttachmentExtVO aevo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ATTACHMENT_EXT SET APPROVAL_STATUS = ?, ");
		sql.append("						  APPROVAL_1 = ?, ");
		sql.append("						  APPROVAL_2 = ?, ");
		sql.append("						  APPROVAL_3 = ?, ");
		sql.append("						  GROUP_IDS = ?, ");
		sql.append("						  DOWNLOADABLE = ?, ");
		sql.append("						  COPY_RIGHT = ?, ");
		sql.append("                     	  CREATE_TIME = ?, ");
		sql.append("                     	  CREATE_BY = ?, ");
		sql.append("                     	  MODIFY_TIME = ?, ");
		sql.append("                     	  MODIFY_BY = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[] {aevo.getApprovalStatus(),
									 aevo.getApproval1(),
									 aevo.getApproval2(),
									 aevo.getApproval3(),
									 aevo.getGroupId(),
									 aevo.getDownloadable(),
									 aevo.getCopyRight(),
									 aevo.getCreateTime(),
									 aevo.getCreateBy(),
									 aevo.getModifyTime(),
									 aevo.getModifyBy(),
									 aevo.getId()};
		
		 return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public AttachmentExtVO getAttattachmentExtVO(String attachmentId) {
		AttachmentExtRowMapper rm = new AttachmentExtRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ATTACHMENT_EXT ");
		sql.append("WHERE ATTACHMENT_ID = ? ");
		
		Object[] obj = new Object[] {attachmentId};

		List<AttachmentExtVO> list = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		if(list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public int getAttachmentExtNum(Date today) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String from = sdf.format(today) + " 00:00:00";
		String to = sdf.format(today) + " 23:59:59";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ATTACHMENT_EXT ");
		sql.append("WHERE MODIFY_TIME >= ? AND MODIFY_TIME <= ?");
		Object[] obj = new Object[] {from, to};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	
}
