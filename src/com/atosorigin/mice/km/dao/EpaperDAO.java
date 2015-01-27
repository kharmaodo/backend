package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.EpaperVO;

public interface EpaperDAO {
	public int insert(EpaperVO vo);
	public int update(EpaperVO vo);
	
	public EpaperVO getEpaper(String id);
	public List<EpaperVO> getEpapers(String from, String to, String description, int startPosition, int pageRows);
	public int getEpapersNum(String from, String to, String description);
	
}
