package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.CiApplicationVO;

public interface CiApplicationDAO {
	public List<CiApplicationVO> getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, 
			                                       int approvalStatus, String sort, int startPosition, int pageRows);
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus);
	public CiApplicationVO getCiApplication(String id);
	public int update(CiApplicationVO cavo);
	public List<CiApplicationVO> getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, String sort,
            int startPosition, int pageRows);
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan, String applyOrg);
	
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, int approvalStatus, String sort, int startPosition, int pageRows);
	public int getCiApplicationsImgNum(String applyOrg, int approvalStatus);
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, String sort, int startPosition, int pageRows);
	public int getCiApplicationsImgNum(String applyOrg);
	
	public int getForTask(String applyBoft, String applyMeetTaiwan);
	public int getForTask();
	
	public List<CiApplicationVO> getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to,
            String sort, int startPosition, int pageRows);
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to);
	public List<CiApplicationVO> getCiApplications(String applyBoft, String applyMeettaiwan, String applyOrg, String from, String to,
            String sort, int startPosition, int pageRows);
	public int getCiApplicationsNum(String applyBoft, String applyMeettaiwan, String applyOrg, String from, String to);
	
	
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, int approvalStatus, String from, String to, String sort, int startPosition, int pageRows);
	public int getCiApplicationsImgNum(String applyOrg, int approvalStatus, String from, String to);
	public List<CiApplicationVO> getCiApplicationsImg(String applyOrg, String from, String to, String sort, int startPosition, int pageRows);
	public int getCiApplicationsImgNum(String applyOrg, String from, String to);
	
	public List<CiApplicationVO> getCiApplicationsExcel(String applyBoft, String applyMeettaiwan, String applyOrg, int approvalStatus, String from, String to);
	public List<CiApplicationVO> getCiApplicationsExcel(String applyBoft, String applyMeettaiwan, String applyOrg, String from, String to);
	
	public List<CiApplicationVO> getCiApplicationsImgExcel(String applyOrg, int approvalStatus, String from, String to);
	public List<CiApplicationVO> getCiApplicationsImgExcel(String applyOrg, String from, String to);
	
}
