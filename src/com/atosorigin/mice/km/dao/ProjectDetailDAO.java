package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.ProjectDetailVO;

public interface ProjectDetailDAO {
	public int insert(ProjectDetailVO vo);
	public List<ProjectDetailVO> getProjectDetails(String projectId);
	
	public int getProjectDetailsNum(String projectId, String localizedDataId);
}
