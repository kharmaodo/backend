package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.LocalizedDataVO;

public interface LocalizedDataDAO {
	public int insert(LocalizedDataVO localizedDataVO);
	public int update(LocalizedDataVO localizedDataVO);
	public int delete(String localizedDataId);
	
	public List<LocalizedDataVO> getLocalizeData(String id);
	
	public List<LocalizedDataVO> getLocalizeDataLike(String id);
	
	public List<LocalizedDataVO> getLocalizeDataLike(String id, String locale);

}
