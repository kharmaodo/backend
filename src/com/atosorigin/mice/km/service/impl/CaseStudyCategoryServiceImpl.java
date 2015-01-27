package com.atosorigin.mice.km.service.impl;

import java.util.List;

import com.atosorigin.mice.km.dao.CaseStudyCategoryDAO;
import com.atosorigin.mice.km.service.CaseStudyCategoryService;
import com.atosorigin.mice.km.vo.CaseStudyCategoryVO;

public class CaseStudyCategoryServiceImpl implements CaseStudyCategoryService {
	private CaseStudyCategoryDAO caseStudyCategoryDAO;
	
	public CaseStudyCategoryDAO getCaseStudyCategoryDAO() {
		return caseStudyCategoryDAO;
	}

	public void setCaseStudyCategoryDAO(CaseStudyCategoryDAO caseStudyCategoryDAO) {
		this.caseStudyCategoryDAO = caseStudyCategoryDAO;
	}

	@Override
	public List<CaseStudyCategoryVO> getCaseStudyCategoryVOs() {
		return this.getCaseStudyCategoryDAO().getCaseStudyCategoryVOs();
	}

}
