package com.atosorigin.mice.km.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.VendorCategoryEditForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.service.VendorCategoryService;
import com.atosorigin.mice.km.validator.VendorCategoryEditValidator;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.VendorCategoryVO;

public class VendorCategoryController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	private VendorCategoryService vendorCategoryService;
	private DocMembersService docMembersService;
	private DocLogService docLogService;
	private VendorCategoryEditValidator vendorCategoryEditValidator;
	private String insertForm;
	private String insertSuccess;
	private String listForm;
	private String listResult;
	private String updateForm;

	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new VendorCategoryEditForm());
		
		List<VendorCategoryVO> mainCategory = this.vendorCategoryService.getVendorCategory(1);
		model.addObject("main", mainCategory);
		
		List<VendorCategoryVO> subCategory = this.vendorCategoryService.getVendorCategory(2);
		model.addObject("sub", subCategory);
		
		return model;

	}
	
	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, VendorCategoryEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getVendorCategoryEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		VendorCategoryVO vcvo = new VendorCategoryVO();
		vcvo.setDescription(command.getDescription());
		
		if(command.getSubId().length() > 0) {
			VendorCategoryVO tmp = this.getVendorCategoryService().getVendorCategory(3, command.getSubId()).get(0);
			String origId = tmp.getId();
			String prefix = origId.substring(0,5);
			int suffix = Integer.parseInt(origId.substring(5,6));
			suffix++;
			String newId = prefix + suffix;
			vcvo.setId(newId);
			vcvo.setLevelIndex(3);
			vcvo.setParentId(command.getSubId());
		}
		
		if(command.getMainId().length() > 0 && command.getSubId().length() == 0) {
			VendorCategoryVO tmp = this.getVendorCategoryService().getVendorCategory(2, command.getMainId()).get(0);
			String origId = tmp.getId();
			String prefix = origId.substring(0,4);
			int suffix = Integer.parseInt(origId.substring(4,6));
			suffix += 10;
			String newId = prefix + suffix;
			vcvo.setId(newId);
			vcvo.setLevelIndex(2);
			vcvo.setParentId(command.getMainId());
		}
		
		if(command.getMainId().length() == 0) {
			VendorCategoryVO tmp = this.getVendorCategoryService().getVendorCategory(1, null).get(0);
			String origId = tmp.getId();
			String prefix = origId.substring(0,3);
			int suffix = Integer.parseInt(origId.substring(3,6));
			suffix += 100;
			String newId = prefix + suffix;
			vcvo.setId(newId);
			vcvo.setLevelIndex(1);
			vcvo.setParentId(null);
		}
		
		if(this.getVendorCategoryService().insert(vcvo) == 1) {
			//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增廠商目錄: " + command.getDescription());
			this.getDocLogService().insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		} else {
			model.addObject("command", command);
			return model;
		}
	}
/*
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new ConferenceListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request,
			HttpServletResponse response, ConferenceListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		int r = RequestUtils.getIntParameter(request, "r", 0);
		String sort = RequestUtils.getStringParameter(request, "s", "11");
		if(r == 1) {
			command = (ConferenceListForm)request.getSession().getAttribute("conference_list_form");
		} else {
			request.getSession().setAttribute("conference_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());

		// Validtor
		BindException errors = super.bindObject(request, command, this.getConferenceListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String fromDate;
		String toDate;
		if("".equals(command.getFrom())) {
			fromDate = "2011-01-01 00:00:00";
		} else {
			fromDate = command.getFrom() + " 00:00:00";
		}
		
		if("".equals(command.getTo())) {
			toDate = "2099-12-31 23:59:59";
		} else {
			toDate = command.getTo() + " 23:59:59";
		}
		
		Pager pager = this.getConferenceService().getConferenceBlur(command.getName(), 
				                                               fromDate, 
				                                               toDate, 
				                                               sort,
				                                               command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("sort", sort);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, ConferenceEditForm command)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		int id = RequestUtils.getIntParameter(request, "id", 0);
		
		ConferenceVO cvo = this.getConferenceService().getById(id);
		command.setAccount(cvo.getAccount());
		command.setAddress(cvo.getAddress());
		command.setCreateDate(cvo.getCreateDate());
		command.setEnd(sdf.format(cvo.getEndDatetime()));
		command.setId(cvo.getId());
		command.setModifiedBy(cvo.getModifiedBy());
		command.setModifiedDate(cvo.getModifiedDate());
		command.setName(cvo.getName());
		command.setStart(sdf.format(cvo.getStartDatetime()));
		command.setStatus(cvo.getStatus());
		command.setVisible(cvo.getVisible());
		if(cvo.getWiflyMax() == 9999) {
			command.setMax("");
		} else {
			command.setMax(String.valueOf(cvo.getWiflyMax()));
		}
		command.setLocale(cvo.getLocale());
		
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(cvo.getAccount(), "");

        model.addObject("command", command);
        model.addObject("dmvo", dmvo);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request,
			HttpServletResponse response, ConferenceEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getConferenceUpdateValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
	    ConferenceVO cvo = this.conferenceService.getById(command.getId());
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDatetime = sdf.parse(command.getStart());
		Date endDatetime = sdf.parse(command.getEnd());
		if(command.getAccount() != null) {
			cvo.setAccount(command.getAccount());
		}
	    cvo.setAddress(command.getAddress());
	    cvo.setEndDatetime(endDatetime);
	    cvo.setModifiedBy(dmvo.getAccount());
	    cvo.setModifiedDate(new Date());
	    cvo.setName(command.getName());
	    cvo.setStartDatetime(startDatetime);
	    cvo.setStatus(command.getStatus());
	    cvo.setVisible(command.getVisible());
	    if("".equals(command.getMax())) {
	    	cvo.setWiflyMax(9999);
	    } else {
	    	cvo.setWiflyMax(Integer.parseInt(command.getMax()));
	    }
	    cvo.setLocale(command.getLocale());

	    if(this.getConferenceService().update(cvo) == 1) {
	    	//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改會議  : " + cvo.getName());
			this.getDocLogService().insert(dlvo);
	    	String targetPage = request.getContextPath() + "/conference.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new ConferenceListForm());
	    } else {
	    	model.addObject("command", command);
			return model;
	    }
	}
*/
	public VendorCategoryService getVendorCategoryService() {
		return vendorCategoryService;
	}

	public void setVendorCategoryService(VendorCategoryService vendorCategoryService) {
		this.vendorCategoryService = vendorCategoryService;
	}

	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}

	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}

	public VendorCategoryEditValidator getVendorCategoryEditValidator() {
		return vendorCategoryEditValidator;
	}

	public void setVendorCategoryEditValidator(
			VendorCategoryEditValidator vendorCategoryEditValidator) {
		this.vendorCategoryEditValidator = vendorCategoryEditValidator;
	}

	public String getInsertForm() {
		return insertForm;
	}

	public void setInsertForm(String insertForm) {
		this.insertForm = insertForm;
	}

	public String getInsertSuccess() {
		return insertSuccess;
	}

	public void setInsertSuccess(String insertSuccess) {
		this.insertSuccess = insertSuccess;
	}

	public String getListForm() {
		return listForm;
	}

	public void setListForm(String listForm) {
		this.listForm = listForm;
	}

	public String getListResult() {
		return listResult;
	}

	public void setListResult(String listResult) {
		this.listResult = listResult;
	}

	public String getUpdateForm() {
		return updateForm;
	}

	public void setUpdateForm(String updateForm) {
		this.updateForm = updateForm;
	}
	

}
