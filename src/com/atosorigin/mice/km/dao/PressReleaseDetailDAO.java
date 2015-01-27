package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.PressReleaseDetailVO;

public interface PressReleaseDetailDAO {
	public int insert(PressReleaseDetailVO pressReleaseDetailVO);
	public int delete(String pressReleaseId);
	
	public List<PressReleaseDetailVO> getPressReleaseDetails(String pressReleaseId);
}
