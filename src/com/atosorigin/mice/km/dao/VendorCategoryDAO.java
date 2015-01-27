package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.VendorCategoryVO;

public interface VendorCategoryDAO {
	public List<VendorCategoryVO> getByLevelIndex(int levelIndex);
	public int insert(VendorCategoryVO vcvo);
	public int update(VendorCategoryVO vcvo);
	public VendorCategoryVO getById(String id);
	public List<VendorCategoryVO> getByLevelIndex(int levelIndex, String parentId);
}
