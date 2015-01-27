package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.CaseStudyDetailVO;
import com.atosorigin.mice.km.vo.CaseStudyVO;

public class CaseStudyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5141397052839046056L;
	private CaseStudyVO caseStudyVO;
	private List<CaseStudyDetailVO> caseStudyDetailVOs;
	public CaseStudyVO getCaseStudyVO() {
		return caseStudyVO;
	}
	public void setCaseStudyVO(CaseStudyVO caseStudyVO) {
		this.caseStudyVO = caseStudyVO;
	}
	public List<CaseStudyDetailVO> getCaseStudyDetailVOs() {
		return caseStudyDetailVOs;
	}
	public void setCaseStudyDetailVOs(List<CaseStudyDetailVO> caseStudyDetailVOs) {
		this.caseStudyDetailVOs = caseStudyDetailVOs;
	}
	
	

}
