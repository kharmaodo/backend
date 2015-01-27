package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.PlayerVO;

public interface PlayerDAO {
	public List<PlayerVO> getAll();
	public List<PlayerVO> getByRegionId(int regionId);
}
