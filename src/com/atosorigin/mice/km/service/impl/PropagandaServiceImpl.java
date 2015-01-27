package com.atosorigin.mice.km.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.common.KmResource;
import com.atosorigin.mice.km.dao.PropagandaDAO;
import com.atosorigin.mice.km.service.PropagandaService;
import com.atosorigin.mice.km.util.SendMailUtil;
import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.PropagandaVO;

public class PropagandaServiceImpl extends KmResource implements PropagandaService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private PropagandaDAO propagandaDAO;
	private SendMailUtil sendMailUtil;
	
	public void setPropagandaDAO(PropagandaDAO propagandaDAO) {
		this.propagandaDAO = propagandaDAO;
	}
	
	public void setSendMailUtil(SendMailUtil sendMailUtil) {
		this.sendMailUtil = sendMailUtil;
	}

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
	
	@Override
	public PropagandaVO getPropaganda(String id) {
		return this.propagandaDAO.getPropaganda(id);
	}

	@Override
	public Pager getPropagandas(String from, String to, String applyOrg, int approvedStatus, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List vos = this.propagandaDAO.getPropagandas(from, to, applyOrg, approvedStatus, startPosition, Constants.PAGE_ROWS);
		int total = this.propagandaDAO.getPropagandaNum(from, to, applyOrg, approvedStatus);
		pager.setCurrentPage(currentPage);
		pager.setObjList(vos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("prop.htm?act=doList");
		pager.setTotal(total);
		return pager;
	}
	
	
	private int approvePass(String[] ids) {
		int row = 0;
		for(int i=0; i<ids.length; i++) {
			PropagandaVO vo = this.propagandaDAO.getPropaganda(ids[i]);
			vo.setApprovalStatus(1);
			vo.setApprovedDate(new Date());
			row += this.propagandaDAO.update(vo);
			//send approved mail
			sendMailPass(vo);
		}
		return row;
	}

	private int approveReject(String[] ids) {
		int row = 0;
		for(int i=0; i<ids.length; i++) {
			PropagandaVO vo = this.propagandaDAO.getPropaganda(ids[i]);
			vo.setApprovalStatus(2);
			vo.setApprovedDate(new Date());
			row += this.propagandaDAO.update(vo);
			//send rejected mail
			sendMailReject(vo);
		}
		return row;
	}
	
	private void sendMailPass(PropagandaVO vo) {
		//Object[] obj = new Object[]{vo.getId()};
		Object[] obj1 = new Object[]{vo.getSerialNumber()};
		String text = this.getValue("prop.application.email.approve.content");
		String subject = this.getValue("prop.application.email.approve.title", obj1);
		this.sendMailUtil.sendTextMail(vo.getEmail(), subject, text);
	}
	
	private void sendMailReject(PropagandaVO vo) {
		Object[] obj1 = new Object[]{vo.getSerialNumber()};
		String text = this.getValue("prop.application.email.reject.content");
		String subject = this.getValue("prop.application.email.reject.title", obj1);
		this.sendMailUtil.sendTextMail(vo.getEmail(), subject, text);
	}

}
