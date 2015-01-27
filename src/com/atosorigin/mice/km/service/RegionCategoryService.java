package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.vo.RegionCategoryVO;

public interface RegionCategoryService {
	public RegionCategoryVO getRegionCategory(String id);
	public List<RegionCategoryVO> getRegionCategories(int level, String parentId);
}
