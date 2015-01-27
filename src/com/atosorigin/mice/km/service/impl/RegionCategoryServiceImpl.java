package com.atosorigin.mice.km.service.impl;

import java.util.List;

import com.atosorigin.mice.km.dao.RegionCategoryDAO;
import com.atosorigin.mice.km.service.RegionCategoryService;
import com.atosorigin.mice.km.vo.RegionCategoryVO;

public class RegionCategoryServiceImpl implements RegionCategoryService {
	
	private RegionCategoryDAO regionCategoryDAO;
	
	public void setRegionCategoryDAO(RegionCategoryDAO regionCategoryDAO) {
		this.regionCategoryDAO = regionCategoryDAO;
	}

	@Override
	public RegionCategoryVO getRegionCategory(String id) {
		return this.regionCategoryDAO.getRegionCategory(id);
	}

	@Override
	public List<RegionCategoryVO> getRegionCategories(int level, String parentId) {
		if(parentId == null) {
			return this.regionCategoryDAO.getRegionCategories(level);
		} else {
			return this.regionCategoryDAO.getRegionCategories(parentId);
		}
	}

}
