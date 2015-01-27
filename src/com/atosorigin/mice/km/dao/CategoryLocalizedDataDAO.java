package com.atosorigin.mice.km.dao;

import com.atosorigin.mice.km.vo.CategoryLocalizedDataVO;

public interface CategoryLocalizedDataDAO {
	public int insert(CategoryLocalizedDataVO categoryLocalizedDataVO);
	public int update(CategoryLocalizedDataVO categoryLocalizedDataVO);
	public int delete(String localizedDataId);

}
