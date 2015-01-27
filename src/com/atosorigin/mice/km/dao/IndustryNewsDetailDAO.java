package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;

public interface IndustryNewsDetailDAO {
	public int insert(IndustryNewsDetailVO industryNewsDetailVO);
	public int delete(String industryNewsId);
	public List<IndustryNewsDetailVO> getIndustryNewsDetails(String industryNewsId);
}
