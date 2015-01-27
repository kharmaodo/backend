package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.ClippingVO;

public interface ClippingDAO {
	public int insert(ClippingVO clippingVO);
	public int update(ClippingVO clippingVO);
	
	public ClippingVO getClipping(String id);
	public List<ClippingVO> getClippings(String keyword, int startPosition, int pageRows);
	public int getClippingsNum(String keyword);

}
