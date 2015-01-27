package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.EpaperVO;

public interface EpaperService {
	public int insert(EpaperVO vo);
	public int update(EpaperVO vo);
	
	public EpaperVO getEpaper(String id);
	public Pager getEpapers(String from, String to, String description, int currentPage);
}
