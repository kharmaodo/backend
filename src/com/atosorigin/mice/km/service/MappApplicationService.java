package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public interface MappApplicationService {
	public Pager getMappApplications(String campiagn, String applyOrganization, String status, String sort, int currentPage);
	public int approve(String isPass, String[] ids, String approvalBy);
	public MappApplicationVO getMappApplication(String id); 
	public int getForTask();
	
	public List<MappApplicationVO> getMappApplicationsExcel(String campiagn, String applyOrganization, String status);
}
