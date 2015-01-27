package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.ProjectDetailVO;
import com.atosorigin.mice.km.vo.ProjectVO;

public class ProjectBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9201131642197693562L;
	private ProjectVO projectVO;
	private List<ProjectDetailVO> projectDetailVOs;
	public ProjectVO getProjectVO() {
		return projectVO;
	}
	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}
	public List<ProjectDetailVO> getProjectDetailVOs() {
		return projectDetailVOs;
	}
	public void setProjectDetailVOs(List<ProjectDetailVO> projectDetailVOs) {
		this.projectDetailVOs = projectDetailVOs;
	}
	
	

}
