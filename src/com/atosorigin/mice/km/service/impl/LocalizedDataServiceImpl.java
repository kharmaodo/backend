package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.LocalizedDataDAO;
import com.atosorigin.mice.km.service.LocalizedDataService;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class LocalizedDataServiceImpl implements LocalizedDataService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private LocalizedDataDAO localizedDataDAO;
	
	public LocalizedDataDAO getLocalizedDataDAO() {
		return localizedDataDAO;
	}

	public void setLocalizedDataDAO(LocalizedDataDAO localizedDataDAO) {
		this.localizedDataDAO = localizedDataDAO;
	}

	@Override
	public List<LocalizedDataVO> getLocalizedDataVOs(String id) {
		return this.localizedDataDAO.getLocalizeData(id);
	}

	@Override
	public List<LocalizedDataVO> getLocalizedDataVOsLike(String id) {
		return this.localizedDataDAO.getLocalizeDataLike(id);
	}

	@Override
	public List<LocalizedDataVO> getLocalizedDataVOsLike(String id, String locale) {
		return this.localizedDataDAO.getLocalizeDataLike(id, locale);
	}

}
