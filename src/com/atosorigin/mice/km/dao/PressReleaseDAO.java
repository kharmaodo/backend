package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.bean.PressReleaseBean;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public interface PressReleaseDAO {
	public int insert(PressReleaseVO pressReleaseVO);
	public int update(PressReleaseVO pressReleaseVO);
	
	public List<PressReleaseVO> getPressReleases(String description, String from, String to, int startPosition, int pageRows); 
	public int getPressReleasesNum(String description, String from, String to); 
	
	public PressReleaseVO getPressReleases(String id);
	
	public int getPhotoNum();
}
