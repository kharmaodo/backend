package com.atosorigin.mice.km.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.dao.EpaperDAO;
import com.atosorigin.mice.km.rowmapper.EpaperRowMapper;
import com.atosorigin.mice.km.vo.EpaperVO;

public class EpaperDAOImpl implements EpaperDAO {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(EpaperVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO EPAPER VALUES (?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[]{vo.getId(),
									vo.getDescription(),
									vo.getVolume(),
									vo.getPublishDate(),
									vo.getPhoto(),
									vo.getUrl(),
									vo.getPath(),
									vo.getLocale()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public int update(EpaperVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE EPAPER SET DESCRIPTION = ?, ");
		sql.append("				  VOLUME = ?, ");
		sql.append("                  PUBLISH_DATE = ?, ");
		sql.append("                  PHOTO = ?, ");
		sql.append("                  URL = ?, ");
		sql.append("                  PATH = ?, ");
		sql.append("                  LOCALE = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[]{vo.getDescription(),
									vo.getVolume(),
									vo.getPublishDate(),
									vo.getPhoto(),
									vo.getUrl(),
									vo.getPath(),
									vo.getLocale(),
									vo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}

	@Override
	public EpaperVO getEpaper(String id) {
		EpaperRowMapper rm = new EpaperRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EPAPER WHERE ID = ?");
		Object[] obj = new Object[]{id};
		List<EpaperVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		return vos.size() > 0 ? vos.get(0) : null;
	}

	@Override
	public List<EpaperVO> getEpapers(String from, String to, String description, int startPosition, int pageRows) {
		EpaperRowMapper rm = new EpaperRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EPAPER ");
		sql.append("WHERE DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') >= ? ");
		sql.append("AND   DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') <= ? ");
		if(!description.isEmpty()) {
			sql.append("AND DESCRIPTION LIKE '%" + description + "%' ");
		}
		sql.append("ORDER BY PUBLISH_DATE DESC ");
		sql.append("LIMIT ?, ?");
		
		Object[] obj = new Object[]{from,
				                    to,
				                    startPosition,
				                    pageRows};
		
		List<EpaperVO> vos = this.jdbcTemplate.query(sql.toString(), obj, rm);
		// TODO Auto-generated method stub
		return vos.size() > 0 ? vos : null;
	}

	@Override
	public int getEpapersNum(String from, String to, String description) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM EPAPER ");
		sql.append("WHERE DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') >= ? ");
		sql.append("AND   DATE_FORMAT(PUBLISH_DATE,'%Y-%m-%d') <= ? ");
		if(!description.isEmpty()) {
			sql.append("AND DESCRIPTION LIKE '%" + description + "%' ");
		}
		Object[] obj = new Object[]{from,
				                    to};
		
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

}
