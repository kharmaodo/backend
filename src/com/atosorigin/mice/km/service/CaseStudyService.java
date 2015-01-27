package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.CaseStudyBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;
import com.atosorigin.mice.km.vo.CaseStudyVO;

public interface CaseStudyService {
	public int insert(CaseStudyVO caseStudyVO, List<CaseStudyDetailVO> caseStudyDetailVos);
	public int update(CaseStudyVO caseStudyVO, List<CaseStudyDetailVO> caseStudyDetailVos);
	public Pager getCaseStudyVOs(String description, String caseStudyCategoryId, int currentPage);
	public CaseStudyBean getCaseStudyVO(String id);	
}
