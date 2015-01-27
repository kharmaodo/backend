package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.IndustryNewsCategoryDAO;
import com.atosorigin.mice.km.service.IndustryNewsCategoryService;
import com.atosorigin.mice.km.vo.IndustryNewsCategoryVO;

public class IndustryNewsCategoryServiceImpl implements IndustryNewsCategoryService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private IndustryNewsCategoryDAO industryNewsCategoryDAO;
	
	public void setIndustryNewsCategoryDAO(
			IndustryNewsCategoryDAO industryNewsCategoryDAO) {
		this.industryNewsCategoryDAO = industryNewsCategoryDAO;
	}

	@Override
	public List<IndustryNewsCategoryVO> getIndustryNewsCategorys() {
		return this.industryNewsCategoryDAO.getIndustryNewsCategorys();
	}

}
