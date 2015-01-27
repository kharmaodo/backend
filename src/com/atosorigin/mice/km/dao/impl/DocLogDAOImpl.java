package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.DocLogDAO;
import com.atosorigin.mice.km.rowmapper.DocLogRowMapper;
import com.atosorigin.mice.km.vo.DocLogVO;

public class DocLogDAOImpl implements DocLogDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<DocLogVO> getDocLog() {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT * FROM DOC_LOG");
		List<DocLogVO> dlvos = this.jdbcTemplate.query(sql.toString(), dlrm);
		if(dlvos.size() == 0) {
			return null;
		} else {
			return dlvos;
		}
	}

	public List<DocLogVO> getDocLog(String from, String to, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT * FROM DOC_LOG WHERE CREATE_TIME >= ? AND CREATE_TIME <= ? LIMIT ?, ?");
		Object[] obj = new Object[] {from,
									 to,
				                     startPosition,
				                     pageRows};
		List<DocLogVO> dlvos = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
		if(dlvos.size() == 0) {
			return null;
		} else {
			return dlvos;
		}
	}

	public List<DocLogVO> getDocLog(String from, String to, String account, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT * FROM DOC_LOG ");
		sql.append("WHERE CREATE_TIME >= ? AND CREATE_TIME <= ? AND ACCOUNT = ? ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {from,
									 to,
									 account,
				                     startPosition,
				                     pageRows};
		List<DocLogVO> dlvos = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
		if(dlvos.size() == 0) {
			return null;
		} else {
			return dlvos;
		}
	}

	public int insert(DocLogVO dlvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DOC_LOG (FROM_IP, ");
		sql.append("                     ACCOUNT, ");
		sql.append("                     WHAT, ");
		sql.append("                     CREATE_TIME) ");
		sql.append("VALUES (?,?,?,?)");
		Object[] obj = new Object[] {dlvo.getFromIp(),
				                     dlvo.getAccount(),
				                     dlvo.getWhat(),
				                     dlvo.getCreateTime()};
		int row = jdbcTemplate.update(sql.toString(), obj);				  
		logger.info("DOC_LOG:insert " + row + " row(s) :ID = " + dlvo.getAccount());
		return row;
	}
	
	public int getDocLogNum(String from, String to) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT count(*) FROM DOC_LOG WHERE CREATE_TIME >= ? AND CREATE_TIME <= ?");
		Object[] obj = new Object[] {from,
									 to};
		int rows = this.jdbcTemplate.queryForInt(sql.toString(), obj);
		
		return rows;
	}
	
	public int getDocLogNum(String from, String to, String account) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT count(*) FROM DOC_LOG WHERE CREATE_TIME >= ? AND CREATE_TIME <= ? AND ACCOUNT = ?");
		Object[] obj = new Object[] {from,
									 to,
									 account};
		int rows = this.jdbcTemplate.queryForInt(sql.toString(), obj);
		
		return rows;
	}
	
	public List<DocLogVO> getDocLog(String account, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT * FROM DOC_LOG ");
		sql.append("WHERE ACCOUNT = ? ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {account,
				                     startPosition,
				                     pageRows};
		List<DocLogVO> dlvos = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
		if(dlvos.size() == 0) {
			return null;
		} else {
			return dlvos;
		}
	}
	
	public int getDocLogNum(String account) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT COUNT(*) FROM DOC_LOG ");
		sql.append("WHERE ACCOUNT = ? ");
		Object[] obj = new Object[] {account};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<DocLogVO> getDocLogDoWhat(String account, String what, int startPosition, int pageRows) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT * FROM DOC_LOG ");
		sql.append("WHERE ACCOUNT = ? AND WHAT = ? ");
		sql.append("ORDER BY CREATE_TIME DESC ");
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {account,
				                     what,
				                     startPosition,
				                     pageRows};
		List<DocLogVO> dlvos = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
		if(dlvos.size() == 0) {
			return null;
		} else {
			return dlvos;
		}
	}

	@Override
	public int getDocLogDoWhatNum(String account, String what) {
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT COUNT(*) FROM DOC_LOG ");
		sql.append("WHERE ACCOUNT = ? AND WHAT = ? ");
		Object[] obj = new Object[] {account,
				                     what};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	@Override
	public List<DocLogVO> queryDocLog(String from, String to, String keyword, String sort,
			int startPosition, int pageRows) {
		keyword = "%" + keyword + "%";
		StringBuilder sql = new StringBuilder();
		DocLogRowMapper dlrm = new DocLogRowMapper();
		sql.append("SELECT * FROM DOC_LOG ");
		sql.append("WHERE CREATE_TIME >= ? ");
		sql.append("AND CREATE_TIME <= ? ");
		sql.append("AND (ACCOUNT LIKE ? OR WHAT LIKE ?) ");
		if("11".equals(sort)) {
			sql.append("ORDER BY CREATE_TIME DESC ");
		} else {
			sql.append("ORDER BY CREATE_TIME ");
		}
		sql.append("LIMIT ?, ?");
		Object[] obj = new Object[] {from,
									 to,
									 keyword,
									 keyword,
				                     startPosition,
				                     pageRows};
		List<DocLogVO> dlvos = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
		if(dlvos.size() == 0) {
			return null;
		} else {
			return dlvos;
		}
	}

	@Override
	public int queryDocLogNum(String from, String to, String keyword) {
		keyword = "%" + keyword + "%";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM DOC_LOG ");
		sql.append("WHERE CREATE_TIME >= ? ");
		sql.append("AND CREATE_TIME <= ? ");
		sql.append("AND (ACCOUNT LIKE ? OR WHAT LIKE ?) ");
		Object[] obj = new Object[] {from,
				 					 to,
				 					 keyword,
				 					 keyword};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
