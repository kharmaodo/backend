package com.atosorigin.mice.km.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.VenueBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.VenueEditForm;
import com.atosorigin.mice.km.form.VenueListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.LocalizedDataService;
import com.atosorigin.mice.km.service.RegionTaiwanService;
import com.atosorigin.mice.km.service.VenueCategoryService;
import com.atosorigin.mice.km.service.VenueService;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;
import com.atosorigin.mice.km.vo.RegionTaiwanVO;
import com.atosorigin.mice.km.vo.VenueCategoryVO;
import com.atosorigin.mice.km.vo.VenueDetailVO;
import com.atosorigin.mice.km.vo.VenueVO;
import com.atosorigin.mice.km.xml.GoogleMapDigester;
import com.atosorigin.mice.km.xml.bean.Location;

public class VenueController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private VenueService venueService;
	private VenueCategoryService venueCategoryService;
	private DocLogService docLogService;
	private RegionTaiwanService regionTaiwanService;
	private LocalizedDataService localizedDataService;
	private String insertForm;
	private String insertSuccess;
	private String listForm;
	private String listResult;
	private String updateForm;
	
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
		List<VenueCategoryVO> vcvos = this.venueCategoryService.getVenueCategoryies();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		List<LocalizedDataVO> ldvos = this.localizedDataService.getLocalizedDataVOsLike("COUNTY", "zh_TW");
		model.addObject("vcvos", vcvos);
		model.addObject("rtvos", rtvos);
		model.addObject("ldvos", ldvos);
		model.addObject("command", new VenueEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, VenueEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
        
		// Validtor
		/*
		BindException errors = super.bindObject(request, command, this.getIndustryNewsEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		*/
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		VenueVO vvo = new VenueVO();
		vvo.setActivated("Y");
		vvo.setBooth(command.getBooth());
		vvo.setCapacity(command.getCapacity());
		vvo.setCountyCategoryId(command.getCountyCategoryId().substring(0, command.getCountyCategoryId().length()-2));
		vvo.setCreateDate(new Date());
		vvo.setCreator(dmvo.getAccount());
		vvo.setDescription(command.getDescription());
		vvo.setDivision(command.getDivision());
		vvo.setDivisionRoom(command.getDivisionRoom());
		vvo.setLatitude(command.getLatitude());
		vvo.setLongitude(command.getLongitude());
		vvo.setMeetingRoom(command.getMeetingRoom());
		vvo.setOwnerId(String.valueOf(dmvo.getId()));
		vvo.setRegion(command.getRegion());
		vvo.setRoom(command.getRoom());
		vvo.setTel(command.getTel());
		vvo.setVenueCategoryId(command.getVenueCategoryId());
		vvo.setUrl(command.getUrlTW());
		vvo.setVerified("Y");
		vvo.setVerifier(dmvo.getAccount());
		vvo.setVerifyDate(new Date());
		
		vvo.setOwnRoom("N");
		vvo.setNearbyBusStop("N");
		vvo.setNearbyHospital("N");
		vvo.setNearbyHotel("N");
		vvo.setNearbyHsrStation("N");
		vvo.setNearbyMrtStation("N");
		vvo.setNearbyParking("N");
		vvo.setNearbyTrainStation("N");
		vvo.setNoCateringService("N");
		vvo.setChineseFood("N");
		vvo.setWesternFood("N");
		vvo.setRefreshment("N");
		vvo.setOwnParking("N");
	    
		
		//go to Google Maps and ask for lat nad lng
		/*
		GoogleMapDigester googleMapDigester = new GoogleMapDigester();
		if(!command.getAddressTW().isEmpty()) {
			Location location = googleMapDigester.getLocation(command.getAddressTW());
			if(location == null) {
				vvo.setLatitude("");
				vvo.setLongitude("");
			} else {
				vvo.setLatitude(location.getLat());
				vvo.setLongitude(location.getLng());
			}
		} else {
			vvo.setLatitude("");
			vvo.setLongitude("");
		}
		*/
		
		
		List<VenueDetailVO> vos = new ArrayList<VenueDetailVO>();
		
		if(!command.getNameTW().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressTW());
			vdvo.setLocale("zh_TW");
			vdvo.setName(command.getNameTW());
			vdvo.setUrl(command.getUrlTW());
			vdvo.setVisible("Y");
			vos.add(vdvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressCN());
			vdvo.setLocale("zh_CN");
			vdvo.setName(command.getNameCN());
			vdvo.setUrl(command.getUrlCN());
			vdvo.setVisible("Y");
			vos.add(vdvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressEN());
			vdvo.setLocale("en");
			vdvo.setName(command.getNameEN());
			vdvo.setUrl(command.getUrlEN());
			vdvo.setVisible("Y");
			vos.add(vdvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressJP());
			vdvo.setLocale("ja");
			vdvo.setName(command.getNameJP());
			vdvo.setUrl(command.getUrlJP());
			vdvo.setVisible("Y");
			vos.add(vdvo);
		}
		
		if(this.venueService.insert(vvo, vos) == 0) {
			model.addObject("command", command);
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Venue, " + vvo.getDescription());
			this.docLogService.insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		}
	}
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListForm());
		List<VenueCategoryVO> vcvos = this.venueCategoryService.getVenueCategoryies();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		model.addObject("vcvos", vcvos);
		model.addObject("rtvos", rtvos);
		model.addObject("command", new VenueListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, VenueListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (VenueListForm)request.getSession().getAttribute("venue_list_form");
		} else {
			request.getSession().setAttribute("venue_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		Pager pager = this.venueService.getVenue(command.getDescription(), command.getVenueCategoryId(), command.getRegion(), command.getCurrentPage());
		List<VenueCategoryVO> vcvos = this.venueCategoryService.getVenueCategoryies();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		model.addObject("vcvos", vcvos);
		model.addObject("rtvos", rtvos);
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, VenueEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		VenueBean bean = this.venueService.getVenue(id);
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		VenueVO vvo = bean.getVenueVO();
		List<VenueDetailVO> vdvos = bean.getVenueDetailVOs();
		
		command.setBooth(vvo.getBooth());
		command.setCapacity(vvo.getCapacity());
		command.setCountyCategoryId(vvo.getCountyCategoryId() + "TW");
		command.setDescription(vvo.getDescription());
		command.setDivision(vvo.getDivision());
		command.setDivisionRoom(vvo.getDivisionRoom());
		command.setId(vvo.getId());
		command.setLatitude(vvo.getLatitude());
		command.setLongitude(vvo.getLongitude());
		command.setMeetingRoom(vvo.getMeetingRoom());
		command.setRegion(vvo.getRegion());
		command.setRoom(vvo.getRoom());
		command.setTel(vvo.getTel());
		command.setVenueCategoryId(vvo.getVenueCategoryId());

		for(VenueDetailVO vdvo : vdvos) {
			if("zh_TW".equals(vdvo.getLocale())) {
				command.setAddressTW(vdvo.getAddress());
				command.setNameTW(vdvo.getName());
				command.setUrlTW(vdvo.getUrl());
				command.setVisibleTW(vdvo.getVisible());
			}
			
			if("zh_CN".equals(vdvo.getLocale())) {
				command.setAddressCN(vdvo.getAddress());
				command.setNameCN(vdvo.getName());
				command.setUrlCN(vdvo.getUrl());
				command.setVisibleCN(vdvo.getVisible());
			}
			
			if("en".equals(vdvo.getLocale())) {
				command.setAddressEN(vdvo.getAddress());
				command.setNameEN(vdvo.getName());
				command.setUrlEN(vdvo.getUrl());
				command.setVisibleEN(vdvo.getVisible());
			}
			
			if("ja".equals(vdvo.getLocale())) {
				command.setAddressJP(vdvo.getAddress());
				command.setNameJP(vdvo.getName());
				command.setUrlJP(vdvo.getUrl());
				command.setVisibleJP(vdvo.getVisible());
			}
		}
			
		List<VenueCategoryVO> vcvos = this.venueCategoryService.getVenueCategoryies();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		List<LocalizedDataVO> ldvos = this.localizedDataService.getLocalizedDataVOsLike("COUNTY", "zh_TW");
		model.addObject("vcvos", vcvos);
		model.addObject("rtvos", rtvos);
		model.addObject("ldvos", ldvos);
		model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, VenueEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        
		/*
		// Validtor
		BindException errors = super.bindObject(request, command, this.industryNewsEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		*/
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		VenueBean bean = this.venueService.getVenue(command.getId());
		if(bean == null) return model;
		
		VenueVO vvo = bean.getVenueVO();
		List<VenueDetailVO> vdvos =  new ArrayList<VenueDetailVO>();
		
		vvo.setActivated(command.getActivated());
		vvo.setBooth(command.getBooth());
		vvo.setCapacity(command.getCapacity());
		vvo.setCountyCategoryId(command.getCountyCategoryId().substring(0, command.getCountyCategoryId().length()-2));
		vvo.setModifier(dmvo.getAccount());
		vvo.setModifyDate(new Date());
		vvo.setDescription(command.getDescription());
		vvo.setDivision(command.getDivision());
		vvo.setDivisionRoom(command.getDivisionRoom());
		vvo.setLatitude(command.getLatitude());
		vvo.setLongitude(command.getLongitude());
		vvo.setMeetingRoom(command.getMeetingRoom());
		vvo.setOwnerId(String.valueOf(dmvo.getId()));
		vvo.setRegion(command.getRegion());
		vvo.setRoom(command.getRoom());
		vvo.setTel(command.getTel());
		vvo.setVenueCategoryId(command.getVenueCategoryId());
		vvo.setUrl(command.getUrlTW());
		
		
		//go to Google Maps and ask for lat nad lng
		/*
		GoogleMapDigester googleMapDigester = new GoogleMapDigester();
		if(!command.getAddressTW().isEmpty()) {
			Location location = googleMapDigester.getLocation(command.getAddressTW());
			if(location == null) {
				vvo.setLatitude("");
				vvo.setLongitude("");
			} else {
				vvo.setLatitude(location.getLat());
				vvo.setLongitude(location.getLng());
			}
		} else {
			vvo.setLatitude("");
			vvo.setLongitude("");
		}
		 */
		
		if(!command.getNameTW().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressTW());
			vdvo.setLocale("zh_TW");
			vdvo.setName(command.getNameTW());
			vdvo.setUrl(command.getUrlTW());
			vdvo.setVisible(command.getVisibleTW());
			vdvos.add(vdvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressCN());
			vdvo.setLocale("zh_CN");
			vdvo.setName(command.getNameCN());
			vdvo.setUrl(command.getUrlCN());
			vdvo.setVisible(command.getVisibleCN());
			vdvos.add(vdvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressEN());
			vdvo.setLocale("en");
			vdvo.setName(command.getNameEN());
			vdvo.setUrl(command.getUrlEN());
			vdvo.setVisible(command.getVisibleEN());
			vdvos.add(vdvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			VenueDetailVO vdvo = new VenueDetailVO();
			vdvo.setAddress(command.getAddressJP());
			vdvo.setLocale("ja");
			vdvo.setName(command.getNameJP());
			vdvo.setUrl(command.getUrlJP());
			vdvo.setVisible(command.getVisibleJP());
			vdvos.add(vdvo);
		}
		
		
		if(this.venueService.update(vvo, vdvos) == 0) {
			model.addObject("command", command);
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改VEnue, " + vvo.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/venue.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new VenueListForm());
		}
	}
	
	//======================= getter and setter ================================
	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}
	
	public void setVenueService(VenueService venueService) {
		this.venueService = venueService;
	}

	public void setVenueCategoryService(VenueCategoryService venueCategoryService) {
		this.venueCategoryService = venueCategoryService;
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

	public void setRegionTaiwanService(RegionTaiwanService regionTaiwanService) {
		this.regionTaiwanService = regionTaiwanService;
	}

	public void setLocalizedDataService(LocalizedDataService localizedDataService) {
		this.localizedDataService = localizedDataService;
	}

	
}
