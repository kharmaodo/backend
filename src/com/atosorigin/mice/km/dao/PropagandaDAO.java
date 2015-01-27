package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.PropagandaVO;

public interface PropagandaDAO {
	public int update(PropagandaVO propagandaVO);
	public PropagandaVO getPropaganda(String id);
	public List<PropagandaVO> getPropagandas(String from, String to, String applyOrg, int approvedStatus, int startPosition, int pageRows);
	public int getPropagandaNum(String from, String to, String applyOrg, int approvedStatus);
}
