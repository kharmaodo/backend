package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.VendorCategoryDAO;
import com.atosorigin.mice.km.service.VendorCategoryService;
import com.atosorigin.mice.km.vo.VendorCategoryVO;

public class VendorCategoryServiceImpl implements VendorCategoryService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private VendorCategoryDAO vendorCategoryDAO;
	

	public VendorCategoryDAO getVendorCategoryDAO() {
		return vendorCategoryDAO;
	}

	public void setVendorCategoryDAO(VendorCategoryDAO vendorCategoryDAO) {
		this.vendorCategoryDAO = vendorCategoryDAO;
	}

	@Override
	public int insert(VendorCategoryVO vcvo) {
		return this.getVendorCategoryDAO().insert(vcvo);
	}

	@Override
	public int update(VendorCategoryVO vcvo) {
		return this.getVendorCategoryDAO().update(vcvo);
	}

	@Override
	public List<VendorCategoryVO> getVendorCategory(int levelIndex) {
		return this.getVendorCategoryDAO().getByLevelIndex(levelIndex);
	}

	@Override
	public List<VendorCategoryVO> getVendorCategory(int levelIndex, String parentId) {
		return this.getVendorCategoryDAO().getByLevelIndex(levelIndex, parentId);
	}

}
