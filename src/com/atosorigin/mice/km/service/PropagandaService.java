package com.atosorigin.mice.km.service;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.PropagandaVO;

public interface PropagandaService {
	public int approve(String isPass, String[] ids);
	public PropagandaVO getPropaganda(String id);
	public Pager getPropagandas(String from, String to, String applyOrg, int approvedStatus, int currentPage);

}
