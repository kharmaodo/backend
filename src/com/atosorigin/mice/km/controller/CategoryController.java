package com.atosorigin.mice.km.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.DocumentCategoryEditForm;
import com.atosorigin.mice.km.form.DocumentCategoryListForm;
import com.atosorigin.mice.km.service.DocumentCategoryService;
import com.atosorigin.mice.km.service.LocalizedDataService;
import com.atosorigin.mice.km.validator.DocumentCategoryEditValidator;
import com.atosorigin.mice.km.validator.DocumentCategoryListValidator;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class CategoryController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DocumentCategoryService documentCategoryService;
	private LocalizedDataService localizedDataService; 
	private DocumentCategoryEditValidator documentCategoryEditValidator;
	private DocumentCategoryListValidator documentCategoryListValidator;
	private String insert;
	private String insertSuccess;
	private String update;
	private String updateSuccess;
	private String list;
	private String listResult;

	public DocumentCategoryService getDocumentCategoryService() {
		return documentCategoryService;
	}

	public void setDocumentCategoryService(
			DocumentCategoryService documentCategoryService) {
		this.documentCategoryService = documentCategoryService;
	}
	
	public void setLocalizedDataService(LocalizedDataService localizedDataService) {
		this.localizedDataService = localizedDataService;
	}

	public String getInsert() {
		return insert;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

	public String getInsertSuccess() {
		return insertSuccess;
	}

	public void setInsertSuccess(String insertSuccess) {
		this.insertSuccess = insertSuccess;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getUpdateSuccess() {
		return updateSuccess;
	}

	public void setUpdateSuccess(String updateSuccess) {
		this.updateSuccess = updateSuccess;
	}

	public DocumentCategoryEditValidator getDocumentCategoryEditValidator() {
		return documentCategoryEditValidator;
	}

	public void setDocumentCategoryEditValidator(
			DocumentCategoryEditValidator documentCategoryEditValidator) {
		this.documentCategoryEditValidator = documentCategoryEditValidator;
	}
	
	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getListResult() {
		return listResult;
	}

	public void setListResult(String listResult) {
		this.listResult = listResult;
	}

	public DocumentCategoryListValidator getDocumentCategoryListValidator() {
		return documentCategoryListValidator;
	}

	public void setDocumentCategoryListValidator(
			DocumentCategoryListValidator documentCategoryListValidator) {
		this.documentCategoryListValidator = documentCategoryListValidator;
	}

	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		List<DocumentCategoryVO> parents = this.getDocumentCategoryService().getParent();
		ModelAndView model = new ModelAndView(this.getInsert());
		model.addObject("command", new DocumentCategoryEditForm());
		model.addObject("parents", parents);
		return model;
	}
	
	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, DocumentCategoryEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsert());
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentCategoryEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		DocumentCategoryVO dcvo = new DocumentCategoryVO();
		dcvo.setCategoryGroup(command.getCategoryGroupId());
		dcvo.setDescription(command.getDescription());
		dcvo.setLevelIndex(Constants.LEVEL_INDEX);
		dcvo.setParentId(command.getParentId());
		dcvo.setRank(command.getRank());
		
		List<LocalizedDataVO> localizedDataVOs = new ArrayList<LocalizedDataVO>();
		if(command.getNameTW() != null && command.getNameTW().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setName(command.getNameTW());
			ldvo.setLocaleName(Constants.ZH_TW);
			localizedDataVOs.add(ldvo);
		}
		
		if(command.getNameCN() != null && command.getNameCN().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setName(command.getNameCN());
			ldvo.setLocaleName(Constants.ZH_CN);
			localizedDataVOs.add(ldvo);
		}
		
		if(command.getNameEN() != null && command.getNameEN().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setName(command.getNameEN());
			ldvo.setLocaleName(Constants.EN);
			localizedDataVOs.add(ldvo);
		}
		
		if(command.getNameJP() != null && command.getNameJP().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setName(command.getNameJP());
			ldvo.setLocaleName(Constants.JP);
			localizedDataVOs.add(ldvo);
		}
		
		if(this.getDocumentCategoryService().insert(dcvo, localizedDataVOs) == 0) {
			return model;
		} else {
			return new ModelAndView(this.getInsertSuccess());
		}
	}
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getList());
		model.addObject("command", new DocumentCategoryListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, DocumentCategoryListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		List<DocumentCategoryVO> parents = this.getDocumentCategoryService().getParent();
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (DocumentCategoryListForm)request.getSession().getAttribute("document_category_list_form");
		} else {
			request.getSession().setAttribute("document_category_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());

		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentCategoryListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		Pager pager = this.getDocumentCategoryService().getDocumentCategory(command.getDescription(), command.getCurrentPage());
		List dcvos = pager.getObjList();
		model.addObject("command", command);
		model.addObject("result", pager);
		model.addObject("parents", parents);
		return model;
	}
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, DocumentCategoryEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		DocumentCategoryVO dcvo = this.getDocumentCategoryService().getDocumentCategory(id);
		List<DocumentCategoryVO> parents = this.getDocumentCategoryService().getParent();
		List<LocalizedDataVO> ldvos = this.localizedDataService.getLocalizedDataVOs(dcvo.getId());
		command.setDocumentCategoryId(id);
		command.setCategoryGroupId(dcvo.getCategoryGroupId());
		command.setDescription(dcvo.getDescription());
		command.setDocumentCategoryId(dcvo.getId());
		command.setLevelIndex(dcvo.getLevelIndex());
		command.setParentId(dcvo.getParentId());
		command.setRank(dcvo.getRank());
		for(LocalizedDataVO ldvo : ldvos) {
			if("zh_TW".equals(ldvo.getLocaleName())) {
				command.setNameTW(ldvo.getName());
				command.setLocalizedDataIdTW(ldvo.getId());
				command.setLocaleNameTW(ldvo.getLocaleName());
			}
			if("zh_CN".equals(ldvo.getLocaleName())) {
				command.setNameCN(ldvo.getName());
				command.setLocalizedDataIdCN(ldvo.getId());
				command.setLocaleNameCN(ldvo.getLocaleName());
			}
			if("en".equals(ldvo.getLocaleName())) {
				command.setNameEN(ldvo.getName());
				command.setLocalizedDataIdEN(ldvo.getId());
				command.setLocaleNameEN(ldvo.getLocaleName());
			}
			if("ja".equals(ldvo.getLocaleName())) {
				command.setNameJP(ldvo.getName());
				command.setLocalizedDataIdJP(ldvo.getId());
				command.setLocaleNameJP(ldvo.getLocaleName());
			}
		}
		
		ModelAndView model = new ModelAndView(this.getUpdate());
		model.addObject("command", command);
		model.addObject("parents", parents);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, DocumentCategoryEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdate());
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentCategoryEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DocumentCategoryVO dcvo = this.documentCategoryService.getDocumentCategory(command.getDocumentCategoryId());
		List<LocalizedDataVO> ldvosOrig = this.localizedDataService.getLocalizedDataVOs(dcvo.getId());
		dcvo.setCategoryGroup(command.getCategoryGroupId());
		dcvo.setDescription(command.getDescription());
		dcvo.setLevelIndex(Constants.LEVEL_INDEX);
		dcvo.setParentId(command.getParentId());
		dcvo.setRank(command.getRank());
		List<LocalizedDataVO> ldvosNew = new ArrayList<LocalizedDataVO>();
		
		if(command.getNameTW() != null && command.getNameTW().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setId(command.getLocalizedDataIdTW());
			ldvo.setName(command.getNameTW());
			ldvo.setLocaleName(Constants.ZH_TW);
			ldvosNew.add(ldvo);
		}
		
		if(command.getNameCN() != null && command.getNameCN().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setId(command.getLocalizedDataIdCN());
			ldvo.setName(command.getNameCN());
			ldvo.setLocaleName(Constants.ZH_CN);
			ldvosNew.add(ldvo);
		}
		
		if(command.getNameEN() != null && command.getNameEN().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setId(command.getLocalizedDataIdEN());
			ldvo.setName(command.getNameEN());
			ldvo.setLocaleName(Constants.EN);
			ldvosNew.add(ldvo);
		}

		if(command.getNameJP() != null && command.getNameJP().trim().length() > 0) {
			LocalizedDataVO ldvo = new LocalizedDataVO();
			ldvo.setId(command.getLocalizedDataIdJP());
			ldvo.setName(command.getNameJP());
			ldvo.setLocaleName(Constants.JP);
			ldvosNew.add(ldvo);
		}

		if(this.getDocumentCategoryService().update(dcvo, ldvosOrig, ldvosNew) == 0) {
			return model;
		} else {
			String targetPage = request.getContextPath() + "/category.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new DocumentCategoryListForm());
		}
	}

}
