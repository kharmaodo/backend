package com.atosorigin.mice.km.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.CiAppImageBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.common.KmResource;
import com.atosorigin.mice.km.dao.CiApplicationDAO;
import com.atosorigin.mice.km.dao.CiImageDAO;
import com.atosorigin.mice.km.service.CiApplicationService;
import com.atosorigin.mice.km.util.SendMailUtil;
import com.atosorigin.mice.km.vo.CiApplicationVO;

public class CiApplicationServiceImpl extends KmResource implements CiApplicationService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private CiApplicationDAO ciApplicationDAO;
	private CiImageDAO ciImageDAO;
	private SendMailUtil sendMailUtil;

	@Override
	public int approve(String isPass, String[] ids) {
		int row = 0;
		if("Y".equals(isPass)) {
			row = approvePass(ids);
		} else {
			row = approveReject(ids);
		}
		return row;
	}

	private int approvePass(String[] ids) {
		int row = 0;
		for(int i=0; i<ids.length; i++) {
			CiApplicationVO cavo = this.ciApplicationDAO.getCiApplication(ids[i]);
			cavo.setApprovedStatus(1);
			cavo.setApprovedDate(new Date());
			row += this.ciApplicationDAO.update(cavo);
			//send approved mail
			if(cavo.getSerialNumber().contains("IMG")) {
				sendMailPassImg(cavo);
			} else {
				sendMailPass(cavo);
			}
		}
		return row;
	}

	private int approveReject(String[] ids) {
		int row = 0;
		for(int i=0; i<ids.length; i++) {
			CiApplicationVO cavo = this.ciApplicationDAO.getCiApplication(ids[i]);
			cavo.setApprovedStatus(2);
			cavo.setApprovedDate(new Date());
			row += this.ciApplicationDAO.update(cavo);
			//send rejected mail
			if(cavo.getSerialNumber().contains("IMG")) {
				sendMailRejectImg(cavo);
			} else {
				sendMailReject(cavo);
			}
		}
		return row;
	}

	@Override
	public CiApplicationVO getCiApplication(String id) {
		return this.ciApplicationDAO.getCiApplication(id);
	}

	@Override
	public Pager getCiApplications(String applyBoft,
			String applyMeettaiwan, String applyOrg, int approvalStatus, String sort, 
			int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List cavos;
		int total;
		
		if(approvalStatus == 9) {
			cavos = this.getCiApplicationDAO().getCiApplications(applyBoft, applyMeettaiwan, 
					applyOrg, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsNum(applyBoft, applyMeettaiwan, applyOrg);
		} else {
			cavos = this.getCiApplicationDAO().getCiApplications(applyBoft, applyMeettaiwan, 
					applyOrg, approvalStatus, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsNum(applyBoft, applyMeettaiwan, applyOrg, approvalStatus);
		}
		
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(cavos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("ciapp.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}
	
	
	private void sendMailPass(CiApplicationVO cavo) {
		Object[] obj = new Object[]{cavo.getId()};
		Object[] obj1 = new Object[]{cavo.getSerialNumber()};
		String text = this.getValue("ci.application.email.approve.content", obj);
		String subject = this.getValue("ci.application.email.approve.title", obj1);
		this.getSendMailUtil().sendTextMail(cavo.getApplyContactEmail(), subject, text);
	}
	
	private void sendMailReject(CiApplicationVO cavo) {
		Object[] obj1 = new Object[]{cavo.getSerialNumber()};
		String text = this.getValue("ci.application.email.reject.content");
		String subject = this.getValue("ci.application.email.reject.title", obj1);
		this.getSendMailUtil().sendTextMail(cavo.getApplyContactEmail(), subject, text);
	}
	
	private void sendMailPassImg(CiApplicationVO cavo) {
		Object[] obj = new Object[]{cavo.getId(), cavo.getId()};
		Object[] obj1 = new Object[]{cavo.getSerialNumber()};
		String text = this.getValue("ci.application.email.approve.content.img", obj);
		String subject = this.getValue("ci.application.email.approve.title.img", obj1);
		this.getSendMailUtil().sendTextMail(cavo.getApplyContactEmail(), subject, text);
	}
	
	private void sendMailRejectImg(CiApplicationVO cavo) {
		Object[] obj1 = new Object[]{cavo.getSerialNumber()};
		String text = this.getValue("ci.application.email.reject.content.img");
		String subject = this.getValue("ci.application.email.reject.title.img", obj1);
		this.getSendMailUtil().sendTextMail(cavo.getApplyContactEmail(), subject, text);
	}
	
	@Override
	public Pager getCiApplicationsImg(String applyOrg, int approvalStatus, String sort,
			int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List<CiApplicationVO> cavos = new ArrayList<CiApplicationVO>();
		List caibs = new ArrayList();
		int total;
		
		if(approvalStatus == 9) {
			cavos = this.getCiApplicationDAO().getCiApplicationsImg(applyOrg, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsImgNum(applyOrg);
		} else {
			cavos = this.getCiApplicationDAO().getCiApplicationsImg(applyOrg, approvalStatus, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsImgNum(applyOrg, approvalStatus);
		}
		
		if(cavos != null) {
			for(CiApplicationVO cavo : cavos) {
				CiAppImageBean caib = new CiAppImageBean();
				caib.setCavo(cavo);
				caib.setCivos(this.getCiImageDAO().getCiImages(cavo.getSerialNumber()));
				caibs.add(caib);
			}
		}

		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(caibs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("ciapp.htm?act=doListImg");
		pager.setTotal(total);
		return pager;
	}
	

	public CiApplicationDAO getCiApplicationDAO() {
		return ciApplicationDAO;
	}

	public void setCiApplicationDAO(CiApplicationDAO ciApplicationDAO) {
		this.ciApplicationDAO = ciApplicationDAO;
	}

	public SendMailUtil getSendMailUtil() {
		return sendMailUtil;
	}

	public void setSendMailUtil(SendMailUtil sendMailUtil) {
		this.sendMailUtil = sendMailUtil;
	}

	public CiImageDAO getCiImageDAO() {
		return ciImageDAO;
	}

	public void setCiImageDAO(CiImageDAO ciImageDAO) {
		this.ciImageDAO = ciImageDAO;
	}

	@Override
	public int getForTask(String applyBoft, String applyMeetTaiwan) {
		return this.getCiApplicationDAO().getForTask(applyBoft, applyMeetTaiwan);
	}
	
	@Override
	public int getForTask() {
		return this.getCiApplicationDAO().getForTask();
	}

	@Override
	public Pager getCiApplications(String applyBoft, String applyMeettaiwan,
			String applyOrg, int approvalStatus, String from, String to, String sort,
			int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List cavos;
		int total;
		
		if(approvalStatus == 9) {  //全部
			cavos = this.getCiApplicationDAO().getCiApplications(applyBoft, applyMeettaiwan, 
					applyOrg, from, to, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsNum(applyBoft, applyMeettaiwan, applyOrg, from, to);
		} else {
			cavos = this.getCiApplicationDAO().getCiApplications(applyBoft, applyMeettaiwan, 
					applyOrg, approvalStatus, from, to, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsNum(applyBoft, applyMeettaiwan, applyOrg, approvalStatus, from, to);
		}
		
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(cavos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("ciapp.htm?act=doList");
		pager.setTotal(total);
		
		return pager;
	}
	
	@Override
	public Pager getCiApplicationsImg(String applyOrg, int approvalStatus, String from, String to, String sort,
			int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List<CiApplicationVO> cavos = new ArrayList<CiApplicationVO>();
		List caibs = new ArrayList();
		int total;
		
		if(approvalStatus == 9) {
			cavos = this.getCiApplicationDAO().getCiApplicationsImg(applyOrg, from, to, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsImgNum(applyOrg, from, to);
		} else {
			cavos = this.getCiApplicationDAO().getCiApplicationsImg(applyOrg, approvalStatus, from, to, sort, startPosition, Constants.PAGE_ROWS);
			total = this.getCiApplicationDAO().getCiApplicationsImgNum(applyOrg, approvalStatus, from, to);
		}
		
		if(cavos != null) {
			for(CiApplicationVO cavo : cavos) {
				CiAppImageBean caib = new CiAppImageBean();
				caib.setCavo(cavo);
				caib.setCivos(this.getCiImageDAO().getCiImages(cavo.getSerialNumber()));
				caibs.add(caib);
			}
		}

		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(caibs);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("ciapp.htm?act=doListImg");
		pager.setTotal(total);
		return pager;
	}
	
	
	@Override
	public List<CiApplicationVO> getCiApplicationsExcel(String applyBoft, String applyMeettaiwan,
			String applyOrg, int approvalStatus, String from, String to) {
		List cavos;
		
		if(approvalStatus == 9) {  //全部
			cavos = this.getCiApplicationDAO().getCiApplicationsExcel(applyBoft, applyMeettaiwan, 
					applyOrg, from, to);
		} else {
			cavos = this.getCiApplicationDAO().getCiApplicationsExcel(applyBoft, applyMeettaiwan, 
					applyOrg, approvalStatus, from, to);
		}
		
		return cavos;
	}
	
	@Override
	public List<CiApplicationVO> getCiApplicationsImgExcel(String applyOrg, int approvalStatus, String from, String to) {
		List cavos;
		
		if(approvalStatus == 9) {  //全部
			cavos = this.getCiApplicationDAO().getCiApplicationsImgExcel(applyOrg, from, to);
		} else {
			cavos = this.getCiApplicationDAO().getCiApplicationsImgExcel(applyOrg, approvalStatus, from, to);
		}
		
		return cavos;
	}

}
