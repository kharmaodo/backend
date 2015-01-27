package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.RegionDAO;
import com.atosorigin.mice.km.vo.RegionVO;


public class RegionDAOTest extends BaseTest {
	private RegionDAO dao;

	public void testGetDocLog() {
		dao = (RegionDAO)applicationContext.getBean("regionDAO");
		List<RegionVO> result = dao.getAll();
		System.out.println("result.size() = " + result.size());
	}
}
