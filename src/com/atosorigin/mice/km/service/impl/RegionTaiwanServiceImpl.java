package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.RegionTaiwanDAO;
import com.atosorigin.mice.km.service.RegionTaiwanService;
import com.atosorigin.mice.km.vo.RegionTaiwanVO;

public class RegionTaiwanServiceImpl implements RegionTaiwanService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private RegionTaiwanDAO regionTaiwanDAO;
	
	public void setRegionTaiwanDAO(RegionTaiwanDAO regionTaiwanDAO) {
		this.regionTaiwanDAO = regionTaiwanDAO;
	}



	@Override
	public List<RegionTaiwanVO> getRegionTaiwanVOs() {
		return this.regionTaiwanDAO.getRegionTaiwanVOs();
	}

}
