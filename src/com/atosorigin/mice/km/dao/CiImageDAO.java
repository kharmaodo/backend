package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.CiImageVO;

public interface CiImageDAO {
	public List<CiImageVO> getCiImages(String serialNumber);

}
