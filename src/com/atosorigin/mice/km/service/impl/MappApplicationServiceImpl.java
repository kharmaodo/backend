package com.atosorigin.mice.km.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.common.KmResource;
import com.atosorigin.mice.km.dao.MappApplicationDAO;
import com.atosorigin.mice.km.service.MappApplicationService;
import com.atosorigin.mice.km.util.RandomStringUtil;
import com.atosorigin.mice.km.util.SendMailUtil;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public class MappApplicationServiceImpl extends KmResource implements MappApplicationService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private MappApplicationDAO mappApplicationDAO;
	private SendMailUtil sendMailUtil;
	

	public MappApplicationDAO getMappApplicationDAO() {
		return mappApplicationDAO;
	}

	public void setMappApplicationDAO(MappApplicationDAO mappApplicationDAO) {
		this.mappApplicationDAO = mappApplicationDAO;
	}

	public SendMailUtil getSendMailUtil() {
		return sendMailUtil;
	}

	public void setSendMailUtil(SendMailUtil sendMailUtil) {
		this.sendMailUtil = sendMailUtil;
	}

	@Override
	public MappApplicationVO getMappApplication(String id) {
		return this.mappApplicationDAO.getMappApplication(id);
	}

	@Override
	public Pager getMappApplications(String campiagn, String applyOrganization, String status, String sort, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List mappApplicationVOs = new ArrayList();
		int total = 0;
		
		if(campiagn.isEmpty() && applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplications(sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsNum();
		} else if(campiagn.isEmpty() && applyOrganization.isEmpty() && !status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByStatus(status, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsByStatusNum(status);
		} else if(campiagn.isEmpty() && !applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByApply(applyOrganization, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsByApplyNum(applyOrganization);
		} else if(!campiagn.isEmpty() && applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByCampaign(campiagn, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsByCampaignNum(campiagn);
		} else if(campiagn.isEmpty() && !applyOrganization.isEmpty() && !status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByAS(applyOrganization, status, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsByASNum(applyOrganization, status);
		} else if(!campiagn.isEmpty() && !applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByCA(campiagn, applyOrganization, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsByCANum(campiagn, applyOrganization);
		} else if(!campiagn.isEmpty() && applyOrganization.isEmpty() && !status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByCS(campiagn, status, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsByCSNum(campiagn, status);
		} else {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplications(campiagn, applyOrganization, status, sort, startPosition, Constants.PAGE_ROWS);
			total = this.mappApplicationDAO.getMappApplicationsNum(campiagn, applyOrganization, status);
		}
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(mappApplicationVOs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("mapp.htm?act=doList");
		pager.setTotal(total);
		// TODO Auto-generated method stub
		return pager;
	}

	@Override
	public int approve(String isPass, String[] ids, String approvalBy) {
		int row = 0;
		if("Y".equals(isPass)) {
			row = approvePass(ids, approvalBy);
		} else {
			row = approveReject(ids, approvalBy);
		}
		return row;
	}

	@Override
	public int getForTask() {
		return this.mappApplicationDAO.getForTask();
	}
	
	private int approvePass(String[] ids, String approvalBy) {
		int row = 0;
		RandomStringUtil rsu = new RandomStringUtil();
		for(int i=0; i<ids.length; i++) {
			MappApplicationVO vo = this.mappApplicationDAO.getMappApplication(ids[i]);
			
			//send approved mail
			if(this.mappApplicationDAO.isMember(vo.getEmail())) {
				sendMailPass(vo);
			} else {
				vo.setPassword(rsu.generateString(6));
				sendMailPassWithIdPw(vo);
			}
			
			vo.setStatus("1");
			vo.setApprovalDate(new Date());
			vo.setApprovalBy(approvalBy);
			row += this.mappApplicationDAO.update(vo);
			
		}
		return row;
	}

	private int approveReject(String[] ids, String approvalBy) {
		int row = 0;
		for(int i=0; i<ids.length; i++) {
			MappApplicationVO vo = this.mappApplicationDAO.getMappApplication(ids[i]);
			vo.setStatus("2");
			vo.setApprovalDate(new Date());
			vo.setApprovalBy(approvalBy);
			row += this.mappApplicationDAO.update(vo);
			//send rejected mail
			sendMailReject(vo);
		}
		return row;
	}

	private void sendMailPass(MappApplicationVO vo) {
		NumberFormat nf = new DecimalFormat("0000");
		String newId = "MAPP-" + nf.format(vo.getId());
		Object[] obj = new Object[]{vo.getCampaignTW(), vo.getCampaignEN()};
		Object[] obj1 = new Object[]{newId};
		String text = this.getValue("mapp.application.email.approve.content", obj);
		String subject = this.getValue("mapp.application.email.approve.title", obj1);
		this.getSendMailUtil().sendTextMail(vo.getEmail(), subject, text);
	}
	
	private void sendMailPassWithIdPw(MappApplicationVO vo) {
		NumberFormat nf = new DecimalFormat("0000");
		String newId = "MAPP-" + nf.format(vo.getId());
		Object[] obj = new Object[]{vo.getCampaignTW(), vo.getCampaignEN(), vo.getEmail(), vo.getPassword()};
		Object[] obj1 = new Object[]{newId};
		String text = this.getValue("mapp.application.email.approve.id.pwd.content", obj);
		String subject = this.getValue("mapp.application.email.approve.id.pwd.title", obj1);
		this.getSendMailUtil().sendTextMail(vo.getEmail(), subject, text);
	}
	
	private void sendMailReject(MappApplicationVO vo) {
		NumberFormat nf = new DecimalFormat("0000");
		String newId = "MAPP-" + nf.format(vo.getId());
		Object[] obj = new Object[]{vo.getCampaignTW(), vo.getCampaignEN()};
		Object[] obj1 = new Object[]{newId};
		String text = this.getValue("mapp.application.email.reject.content", obj);
		String subject = this.getValue("mapp.application.email.reject.title", obj1);
		this.getSendMailUtil().sendTextMail(vo.getEmail(), subject, text);
	}

	@Override
	public List<MappApplicationVO> getMappApplicationsExcel(String campiagn, String applyOrganization, String status) {
		
		List<MappApplicationVO> mappApplicationVOs = new ArrayList();
		
		if(campiagn.isEmpty() && applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsExcel();
		} else if(campiagn.isEmpty() && applyOrganization.isEmpty() && !status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByStatusExcel(status);
		} else if(campiagn.isEmpty() && !applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByApplyExcel(applyOrganization);
		} else if(!campiagn.isEmpty() && applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByCampaignExcel(campiagn);
		} else if(campiagn.isEmpty() && !applyOrganization.isEmpty() && !status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByASExcel(applyOrganization, status);
		} else if(!campiagn.isEmpty() && !applyOrganization.isEmpty() && status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByCAExcel(campiagn, applyOrganization);
		} else if(!campiagn.isEmpty() && applyOrganization.isEmpty() && !status.isEmpty()) {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsByCSExcel(campiagn, status);
		} else {
			mappApplicationVOs = this.mappApplicationDAO.getMappApplicationsExcel(campiagn, applyOrganization, status);
		}
		
		return mappApplicationVOs;
	}
}
