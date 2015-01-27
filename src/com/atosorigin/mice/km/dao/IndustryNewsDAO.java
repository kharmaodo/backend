package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.IndustryNewsVO;

public interface IndustryNewsDAO {
	public int insert(IndustryNewsVO industryNewsVO);
	public int update(IndustryNewsVO industryNewsVO);
	
	public List<IndustryNewsVO> getIndustryNews(String description, int startPosition, int pageRows);
	public int getIndustryNewsNum(String description);
	public List<IndustryNewsVO> getIndustryNews(int startPosition, int pageRows);
	public int getIndustryNewsNum();
	public IndustryNewsVO getIndustryNews(String id);
	
	public List<IndustryNewsVO> getIndustryNewsForApprove(int startPosition, int pageRows);
	public int getIndustryNewsForApproveNum();
	
	public int getPhotoNum();
}
