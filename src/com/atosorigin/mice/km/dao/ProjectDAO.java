package com.atosorigin.mice.km.dao;

import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.vo.ProjectVO;

public interface ProjectDAO {
	public int insert(ProjectVO vo);
	public int update(ProjectVO vo);
	public ProjectVO getProject(String menuId, String locale, Date createTime);
	public List<ProjectVO> getProjects(String menuId, String locale, int startPosition, int pageRows);
	public int getProjectsNum(String menuId, String locale);
	public ProjectVO getProject(String id);
	
	public ProjectVO getLatestProject();
}
