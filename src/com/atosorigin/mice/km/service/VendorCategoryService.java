package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.vo.VendorCategoryVO;

public interface VendorCategoryService {
	public int insert(VendorCategoryVO vcvo);
	public int update(VendorCategoryVO vcvo);
	public List<VendorCategoryVO> getVendorCategory(int levelIndex);
	public List<VendorCategoryVO> getVendorCategory(int levelIndex, String parentId);
}
