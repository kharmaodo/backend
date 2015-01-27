package com.atosorigin.mice.km.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.VenueBean;
import com.atosorigin.mice.km.bean.VenueListBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.LocalizedDataDAO;
import com.atosorigin.mice.km.dao.RegionTaiwanDAO;
import com.atosorigin.mice.km.dao.VenueCategoryDAO;
import com.atosorigin.mice.km.dao.VenueDAO;
import com.atosorigin.mice.km.dao.VenueDetailDAO;
import com.atosorigin.mice.km.service.VenueService;
import com.atosorigin.mice.km.vo.LocalizedDataVO;
import com.atosorigin.mice.km.vo.RegionTaiwanVO;
import com.atosorigin.mice.km.vo.VenueCategoryVO;
import com.atosorigin.mice.km.vo.VenueDetailVO;
import com.atosorigin.mice.km.vo.VenueVO;

public class VenueServiceImpl implements VenueService {
private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private VenueDAO venueDAO;
	private VenueDetailDAO venueDetailDAO;
	private VenueCategoryDAO venueCategoryDAO;
	private RegionTaiwanDAO regionTaiwanDAO;
	private LocalizedDataDAO localizedDataDAO;
	
	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setVenueDAO(VenueDAO venueDAO) {
		this.venueDAO = venueDAO;
	}

	public void setVenueDetailDAO(VenueDetailDAO venueDetailDAO) {
		this.venueDetailDAO = venueDetailDAO;
	}
	
	public void setVenueCategoryDAO(VenueCategoryDAO venueCategoryDAO) {
		this.venueCategoryDAO = venueCategoryDAO;
	}

	public void setRegionTaiwanDAO(RegionTaiwanDAO regionTaiwanDAO) {
		this.regionTaiwanDAO = regionTaiwanDAO;
	}

	public void setLocalizedDataDAO(LocalizedDataDAO localizedDataDAO) {
		this.localizedDataDAO = localizedDataDAO;
	}

	public VenueServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	@Override
	public int insert(VenueVO vo, List<VenueDetailVO> vos) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		vo.setId(uuid);
			int rowM = this.venueDAO.insert(vo);
			int rowD = 0;
			for(VenueDetailVO dvo : vos) {
				String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
				dvo.setId(uuidDetail);
				dvo.setVenueId(uuid);
				rowD += this.venueDetailDAO.insert(dvo);
			}
			if(rowM == 1 && rowD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}

	@Override
	public int update(VenueVO vo, List<VenueDetailVO> vos) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
			int rowM = this.venueDAO.update(vo);
			int rowD = 0;
			if(this.venueDetailDAO.delete(vo.getId()) > 0) {
				for(VenueDetailVO dvo : vos) {
					String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
					dvo.setId(uuidDetail);
					dvo.setVenueId(vo.getId());
					rowD += this.venueDetailDAO.insert(dvo);
				}
			}
			if(rowM == 1 && rowD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return rows;
	}

	@Override
	public VenueBean getVenue(String id) {
		VenueVO vo = this.venueDAO.getVenue(id);
		List<VenueDetailVO> vos = this.venueDetailDAO.getVenueDetail(id);
		VenueBean bean = new VenueBean();
		bean.setVenueVO(vo);
		bean.setVenueDetailVOs(vos);
		return bean;
	}

	@Override
	public Pager getVenue(String description, String venueCategoryId, String region, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		
		List<VenueVO> vos = this.venueDAO.getVenue(description, venueCategoryId, region, startPosition, Constants.PAGE_ROWS);
		int total = this.venueDAO.getVenueNum(description, venueCategoryId, region);
		
		List<VenueCategoryVO> vcvos = this.venueCategoryDAO.getVenueCategories();
		List<LocalizedDataVO> ldvos =  this.localizedDataDAO.getLocalizeDataLike("COUNTY", "zh_TW");
		List<RegionTaiwanVO> rtvos = this.regionTaiwanDAO.getRegionTaiwanVOs();
		
		Map<String, String> vcMap = new HashMap<String, String>();
		Map<String, String> ldMap = new HashMap<String, String>();
		Map<String, String> rtMap = new HashMap<String, String>();
		
		for(VenueCategoryVO vcvo : vcvos) {
			vcMap.put(vcvo.getId(), vcvo.getDescription());
		}
		
		for(LocalizedDataVO ldvo : ldvos) {
			ldMap.put(ldvo.getId(), ldvo.getName());
		}
		
		for(RegionTaiwanVO rtvo : rtvos) {
			rtMap.put(rtvo.getId(), rtvo.getDescription());
		}
		
		List result = new ArrayList();
		
		if(vos != null) {
			for(VenueVO vvo : vos) {
				VenueListBean bean = new VenueListBean();
				bean.setId(vvo.getId());
				bean.setDescription(vvo.getDescription());
				bean.setVenueCategoryName(vcMap.get(vvo.getVenueCategoryId()));
				bean.setRegionName(rtMap.get(vvo.getRegion()));
				bean.setCountyName(ldMap.get(vvo.getCountyCategoryId() + "TW"));
				bean.setRoom(vvo.getRoom());
				bean.setTel(vvo.getTel());
				result.add(bean);
			}
		} else {
			result = null;
		}
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(result);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("venue.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}

}
