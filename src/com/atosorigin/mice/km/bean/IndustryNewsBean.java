package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;
import com.atosorigin.mice.km.vo.IndustryNewsVO;

public class IndustryNewsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -927103776330800336L;
	private IndustryNewsVO industryNewsVO;
	private List<IndustryNewsDetailVO> industryNewsDetailVOs;
	public IndustryNewsVO getIndustryNewsVO() {
		return industryNewsVO;
	}
	public void setIndustryNewsVO(IndustryNewsVO industryNewsVO) {
		this.industryNewsVO = industryNewsVO;
	}
	public List<IndustryNewsDetailVO> getIndustryNewsDetailVOs() {
		return industryNewsDetailVOs;
	}
	public void setIndustryNewsDetailVOs(
			List<IndustryNewsDetailVO> industryNewsDetailVOs) {
		this.industryNewsDetailVOs = industryNewsDetailVOs;
	}
	
}
