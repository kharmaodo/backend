package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.vo.LocalizedDataVO;

public interface LocalizedDataService {
	public List<LocalizedDataVO> getLocalizedDataVOs(String id);
	public List<LocalizedDataVO> getLocalizedDataVOsLike(String id);
	
	public List<LocalizedDataVO> getLocalizedDataVOsLike(String id, String locale);

}
