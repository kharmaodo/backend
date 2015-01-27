package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.VenueCategoryDAO;
import com.atosorigin.mice.km.service.VenueCategoryService;
import com.atosorigin.mice.km.vo.VenueCategoryVO;

public class VenueCategoryServiceImpl implements VenueCategoryService {
private Logger logger = Logger.getLogger(this.getClass());
	
	private VenueCategoryDAO venueCategoryDAO;
	
	public VenueCategoryDAO getVenueCategoryDAO() {
		return venueCategoryDAO;
	}

	public void setVenueCategoryDAO(VenueCategoryDAO venueCategoryDAO) {
		this.venueCategoryDAO = venueCategoryDAO;
	}




	@Override
	public List<VenueCategoryVO> getVenueCategoryies() {
		return this.venueCategoryDAO.getVenueCategories();
	}

}
