package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.OverseasEventDetailVO;
import com.atosorigin.mice.km.vo.OverseasEventVO;
import com.atosorigin.mice.km.vo.RegionCategoryVO;

public class OverseasEventBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8778342837796906L;
	private OverseasEventVO overseasEventVO;
	private RegionCategoryVO regionCategoryVO;
	private List<OverseasEventDetailVO> overseasEventDetailVOs;
	public OverseasEventVO getOverseasEventVO() {
		return overseasEventVO;
	}
	public void setOverseasEventVO(OverseasEventVO overseasEventVO) {
		this.overseasEventVO = overseasEventVO;
	}
	public List<OverseasEventDetailVO> getOverseasEventDetailVOs() {
		return overseasEventDetailVOs;
	}
	public void setOverseasEventDetailVOs(
			List<OverseasEventDetailVO> overseasEventDetailVOs) {
		this.overseasEventDetailVOs = overseasEventDetailVOs;
	}
	public RegionCategoryVO getRegionCategoryVO() {
		return regionCategoryVO;
	}
	public void setRegionCategoryVO(RegionCategoryVO regionCategoryVO) {
		this.regionCategoryVO = regionCategoryVO;
	}
	
	
}
