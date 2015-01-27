package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.IndustryNewsBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;
import com.atosorigin.mice.km.vo.IndustryNewsVO;

public interface IndustryNewsService {
	public int insert(IndustryNewsVO industryNewsVO, List<IndustryNewsDetailVO> industryNewsDetailVOs);
	public int update(IndustryNewsVO industryNewsVO, List<IndustryNewsDetailVO> industryNewsDetailVOs);
	
	public Pager getIndustryNews(String description, int currentPage);
	public IndustryNewsBean getIndustryNews(String id);
	
	public Pager getIndustryNewsForApprove(int currentPage);
	
	public int getPhotoNum();
}
