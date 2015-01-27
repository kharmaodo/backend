package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.ProjectBean;
import com.atosorigin.mice.km.vo.ProjectDetailVO;
import com.atosorigin.mice.km.vo.ProjectVO;

public interface ProjectService {
	public String insert(ProjectVO projectVO, List<ProjectDetailVO> projectDetailVOs);
	public int update(ProjectVO projectVO);
	public ProjectBean getProject(String id);
	public Pager getProjects(String menuId, String locale, int currentPage);
	
	public ProjectBean getLatestProject();
	
	public int getProjectDetailsNum(String projectId, String localizedDataId);
}
