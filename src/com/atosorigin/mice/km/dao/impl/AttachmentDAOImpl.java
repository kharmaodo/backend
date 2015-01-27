package com.atosorigin.mice.km.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.rowmapper.AttachmentRowMapper;
import com.atosorigin.mice.km.vo.AttachmentVO;

public class AttachmentDAOImpl implements AttachmentDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(AttachmentVO avo) {
		StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO ATTACHMENT (ID, ");
        sql.append("                        TITLE, ");
        sql.append("                        ORIGIN_NANME, ");
        sql.append("                        TYPE, ");
        sql.append("                        PATH, ");
        sql.append("                        PRESS_RELEASE_ID, ");
        sql.append("                        CATEGORY_GROUP) ");
        sql.append("VALUES (?,?,?,?,?,?,?)");
        Object[] obj = new Object[] {avo.getId(),
        							 avo.getTitle(),
        							 avo.getOriginName(),
        							 avo.getType(),
        							 avo.getPath(),
        							 avo.getPressReleaseId(),
        							 avo.getCategoryGroup()};
        return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	
	public int update(AttachmentVO avo) {
		StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE ATTACHMENT SET TITLE = ?, ");
        sql.append("                      ORIGIN_NANME = ?, ");
        sql.append("                      TYPE = ?, ");
        sql.append("                      PATH = ?, ");
        sql.append("                      PRESS_RELEASE_ID = ?, ");
        sql.append("                      CATEGORY_GROUP = ? ");
        sql.append("WHERE ID = ?");
        Object[] obj = new Object[] {avo.getTitle(),
        							 avo.getOriginName(),
        							 avo.getType(),
        							 avo.getPath(),
        							 avo.getPressReleaseId(),
        							 avo.getCategoryGroup(),
        							 avo.getId()};
        return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	public AttachmentVO getAttachment(String id) {
		AttachmentRowMapper rm = new AttachmentRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ATTACHMENT WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<AttachmentVO> list = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		if(list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
		
	}
	
	
}
