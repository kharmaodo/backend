package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.CiApplicationVO;

public interface CiApplicationService {
	public CiApplicationVO getCiApplication(String id);
	public Pager getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus, String sort, int currentPage);
	public int approve(String isPass, String[] ids);
	
	public Pager getCiApplicationsImg(String applyOrg, int approvalStatus, String sort, int currentPage);
	
	public int getForTask(String applyBoft, String applyMeetTaiwan);
	public int getForTask();
	
	public Pager getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to, String sort, int currentPage);
	public Pager getCiApplicationsImg(String applyOrg, int approvalStatus, String from, String to, String sort, int currentPage);
	
	public List<CiApplicationVO> getCiApplicationsExcel(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to);
	public List<CiApplicationVO> getCiApplicationsImgExcel(String applyOrg, int approvalStatus, String from, String to);
}
