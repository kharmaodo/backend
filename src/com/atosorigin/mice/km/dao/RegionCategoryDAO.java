package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.RegionCategoryVO;

public interface RegionCategoryDAO {
	public RegionCategoryVO getRegionCategory(String id);
	public List<RegionCategoryVO> getRegionCategories(int level);
	public List<RegionCategoryVO> getRegionCategories(String parentId);
}
