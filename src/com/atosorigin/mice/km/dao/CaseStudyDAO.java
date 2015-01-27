package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.bean.CaseStudyBean;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;
import com.atosorigin.mice.km.vo.CaseStudyVO;

public interface CaseStudyDAO {
	public int insert(CaseStudyVO caseStudyVO);
	public int update(CaseStudyVO caseStudyVO);
	public List<CaseStudyVO> getCaseStudyVOs(int startPosition, int pageRows);
	public int getCaseStudyVOsNum();
	public List<CaseStudyVO> getCaseStudyVOsByDescription(String description, int startPosition, int pageRows);
	public int getCaseStudyVOsByDescriptionNum(String description);
	public List<CaseStudyVO> getCaseStudyVOsByCaseStudyCategoryId(String caseStudyCategoryId, int startPosition, int pageRows);
	public int getCaseStudyVOsByCaseStudyCategoryIdNum(String caseStudyCategoryId);
	public List<CaseStudyVO> getCaseStudyVOs(String description, String caseStudyCategoryid, int startPosition, int pageRows);
	public int getCaseStudyVOsNum(String description, String caseStudyCategoryid);
	public CaseStudyVO getCaseStudyVO(String id);	
}
