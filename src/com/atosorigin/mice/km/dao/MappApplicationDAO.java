package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public interface MappApplicationDAO {
	public List<MappApplicationVO> getMappApplications(String sort, int startPosition, int pageRows);
	public int getMappApplicationsNum();
	
	public List<MappApplicationVO> getMappApplications(String campaign, String applyOrganization, 
			                                       String status, String sort, int startPosition, int pageRows);
	public int getMappApplicationsNum(String campaign, String applyOrganization, String status);
	
	public List<MappApplicationVO> getMappApplicationsByCampaign(String campaign, String sort, int startPosition, int pageRows);
	public int getMappApplicationsByCampaignNum(String campaign);
	
	public List<MappApplicationVO> getMappApplicationsByApply(String applyOrganization, String sort, int startPosition, int pageRows);
	public int getMappApplicationsByApplyNum(String applyOrganization);
	
	public List<MappApplicationVO> getMappApplicationsByStatus(String applyOrganization, String sort, int startPosition, int pageRows);
	public int getMappApplicationsByStatusNum(String applyOrganization);
	
	public List<MappApplicationVO> getMappApplicationsByCA(String campaign, String applyOrganization, String sort, int startPosition, int pageRows);
	public int getMappApplicationsByCANum(String campaign, String applyOrganization);
	
	public List<MappApplicationVO> getMappApplicationsByCS(String campaign, String status, String sort, int startPosition, int pageRows);
	public int getMappApplicationsByCSNum(String campaign, String status);
	
	public List<MappApplicationVO> getMappApplicationsByAS(String applyOrganization, String status, String sort, int startPosition, int pageRows);
	public int getMappApplicationsByASNum(String applyOrganization, String status);
	
	public MappApplicationVO getMappApplication(String id);
	public int update(MappApplicationVO mappApplicationVO);
	
	public int getForTask();
	
	public boolean isMember(String email);
	
	
	public List<MappApplicationVO> getMappApplicationsExcel(String campaign, String applyOrganization, String status);
	public List<MappApplicationVO> getMappApplicationsByCampaignExcel(String campaign);
	public List<MappApplicationVO> getMappApplicationsByApplyExcel(String applyOrganization);
	public List<MappApplicationVO> getMappApplicationsByStatusExcel(String applyOrganization);
	public List<MappApplicationVO> getMappApplicationsByCAExcel(String campaign, String applyOrganization);
	public List<MappApplicationVO> getMappApplicationsByCSExcel(String campaign, String status);
	public List<MappApplicationVO> getMappApplicationsByASExcel(String applyOrganization, String status);
	public List<MappApplicationVO> getMappApplicationsExcel();
}
