package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.ClippingDAO;
import com.atosorigin.mice.km.rowmapper.ClippingRowMapper;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.ClippingVO;

public class ClippingDAOImpl implements ClippingDAO {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(ClippingVO clippingVO) {
		StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO CLIPPING (TITLE, ");
        sql.append("                      DESCRIPTION, ");
        sql.append("                      TYPE, ");
        sql.append("                      ISSUE_NO, ");
        sql.append("                      PUBLISH_DATE, ");
        sql.append("                      URL, ");
        sql.append("                      PATH, ");
        sql.append("                      ATTACHMENT_ID, ");
        sql.append("                      PUBLISHER, ");
        sql.append("                      CREATE_TIME, ");
        sql.append("                      CREATOR, ");
        sql.append("                      VISIBLE) ");
        sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
        Object[] obj = new Object[] {clippingVO.getTitle(),
        							 clippingVO.getDescription(),
        							 clippingVO.getType(),
        							 clippingVO.getIssueNo(),
        							 clippingVO.getPublishDate(),
        							 clippingVO.getUrl(),
        							 clippingVO.getPath(),
        							 clippingVO.getAttachmentId(),
        							 clippingVO.getPublisher(),
        							 clippingVO.getCreateTime(),
        							 clippingVO.getCreator(),
        							 clippingVO.getVisible()};
        return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(ClippingVO clippingVO) {
		StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE CLIPPING SET TITLE = ?, ");
        sql.append("                    DESCRIPTION = ?, ");
        sql.append("                    TYPE = ?, ");
        sql.append("                    ISSUE_NO = ?, ");
        sql.append("                    PUBLISH_DATE = ?, ");
        sql.append("                    URL = ?, ");
        sql.append("                    PATH = ?, ");
        sql.append("                    ATTACHMENT_ID = ?, ");
        sql.append("                    PUBLISHER = ?, ");
        sql.append("                    CREATE_TIME = ?, ");
        sql.append("                    CREATOR = ?, ");
        sql.append("                    VISIBLE = ? ");
        sql.append("WHERE ID = ?");
        Object[] obj = new Object[] {clippingVO.getTitle(),
        							 clippingVO.getDescription(),
        							 clippingVO.getType(),
        							 clippingVO.getIssueNo(),
        							 clippingVO.getPublishDate(),
        							 clippingVO.getUrl(),
        							 clippingVO.getPath(),
        							 clippingVO.getAttachmentId(),
        							 clippingVO.getPublisher(),
        							 clippingVO.getCreateTime(),
        							 clippingVO.getCreator(),
        							 clippingVO.getVisible(),
        							 clippingVO.getId()};
        return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public ClippingVO getClipping(String id) {
		ClippingRowMapper rm = new ClippingRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CLIPPING WHERE ID = ? AND VISIBLE = 'Y'");
		Object[] obj = new Object[] {id};
		List<ClippingVO> list = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		if(list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<ClippingVO> getClippings(String keyword, int startPosition, int pageRows) {
		keyword = "%" + keyword + "%";
		ClippingRowMapper rm = new ClippingRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CLIPPING WHERE (TITLE LIKE ? OR DESCRIPTION LIKE ? OR PUBLISHER LIKE ? ) AND VISIBLE = 'Y' ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {keyword,
				                     keyword,
				                     keyword,
				                     startPosition,
				                     pageRows};
		List<ClippingVO> list = this.jdbcTemplate.query(sql.toString(), obj, rm);
		
		if(list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}


	@Override
	public int getClippingsNum(String keyword) {
		keyword = "%" + keyword + "%";
		ClippingRowMapper rm = new ClippingRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM CLIPPING WHERE (TITLE LIKE ? OR DESCRIPTION LIKE ? OR PUBLISHER LIKE ? ) AND VISIBLE = 'Y' ");
		Object[] obj = new Object[] {keyword,
				                     keyword,
				                     keyword};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
