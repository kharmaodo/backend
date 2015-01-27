package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.CaseStudyDetailVO;

public interface CaseStudyDetailDAO {
	public int insert(CaseStudyDetailVO caseStudyDetailVO);
	public int update(CaseStudyDetailVO caseStudyDetailVO);
	public int delete(String caseStudyId);
	
	public CaseStudyDetailVO getCaseStudyDetailVO(String id);
	public List<CaseStudyDetailVO> getCaseStudyDetailVOs(String caseStudyId);
}
