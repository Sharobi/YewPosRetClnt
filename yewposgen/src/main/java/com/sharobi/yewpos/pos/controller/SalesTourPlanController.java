package com.sharobi.yewpos.pos.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.SalesmanDTO;
import com.sharobi.yewpos.inv.service.SalesmanService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharobi.yewpos.pos.model.SaleTourPlanDTO;
import com.sharobi.yewpos.pos.service.SalesTourPlanService;

@Controller
@RequestMapping("/salestour")
public class SalesTourPlanController {
	private final static Logger logger = LoggerFactory.getLogger(SalesTourPlanController.class);
	@Autowired
	SalesTourPlanService salesTourPlanService;
	@Autowired
	RoleService roleService;
	@Autowired
	SalesmanService salesmanService;
	 
	@RequestMapping(value = "/loadsalestourplan",method = RequestMethod.GET)
	public ModelAndView loadSalesTourPaln(Model model,HttpSession session) {
		//logger.info("In loadsalestourplan......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.AREA_SETUP_201R, lang);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		List<SalesmanDTO> allSalesmanMasterlist=salesmanService.getAllSalesmanmasterlist(commonResultSetMapper);
		//System.out.println("res:"+allSalesmanMasterlist.toString());
		
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.SALE_TOUR_PLAN);
		mav.addObject("menuselect",menuselect);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.addObject("allSalesmanMasterlist",allSalesmanMasterlist);
		mav.setViewName(ForwardConstants.VIEW_TOUR_PLAN_PAGE);
        return mav;
  }
	
	
	@RequestMapping(value = "/loadsalesmantrack",method = RequestMethod.GET)
	public ModelAndView loadSalesManTrackingPage(Model model,HttpSession session) {
		//logger.info("In loadsalesmantrack......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		List<SalesmanDTO> allSalesmanMasterlist=salesmanService.getAllSalesmanmasterlist(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.AREA_SETUP_201R, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.SALE_MAN_TRACKING);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_SALESMAN_TRACK_PAGE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allSalesmanMasterlist",allSalesmanMasterlist);
        return mav;
  }
	
	
@RequestMapping(value = "/searchTourPlan",
			    method = RequestMethod.POST)
public ModelAndView searchTourPlan(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
									BindingResult result,
									HttpSession session,
									HttpServletResponse response) throws IOException {
										
	//logger.info("In searchTourPlan......{}", commonResultSetMapper.toString());	
	ModelAndView mav = new ModelAndView();
	LoginInfoByUserDTO userInfo=null;
	if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
	{
		mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
		return mav;
	}
	String lang=null;
	if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
	{
		lang=Constant.DEFAULT_LANG;
	}
	commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
	commonResultSetMapper.setStoreId(userInfo.getStoreId());	
	commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
	List<SaleTourPlanDTO> allSaleTourPlanDetails = salesTourPlanService.getAllSaleTourPlanDetails(commonResultSetMapper);
	//System.out.println("res:"+allSaleTourPlanDetails.toString());
	MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
	List<SalesmanDTO> allSalesmanMasterlist=salesmanService.getAllSalesmanmasterlist(commonResultSetMapper);
	
	MenuSelection menuselect = new MenuSelection();
	menuselect.setMenu(Constant.POS);
	menuselect.setSubMenu(Constant.SALE_TOUR_PLAN);
	mav.addObject("menuselect",menuselect);
	mav.addObject("menuByUserDTO",menuByUserDTO);
	mav.addObject("allTours",allSaleTourPlanDetails);
	mav.addObject("allSalesmanMasterlist",allSalesmanMasterlist);
	mav.setViewName(ForwardConstants.VIEW_TOUR_PLAN_PAGE);
    return mav;	
  }
	
@RequestMapping(value = "/getTourPlanDetails/{saleTourPlanId}",
method = RequestMethod.GET)
public void getTourPlanDetails(@PathVariable("saleTourPlanId") int saleTourPlanId,
				HttpSession session,
				HttpServletResponse response) throws IOException {
	
		//logger.info("In getTourPlanDetails......{}", saleTourPlanId);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
		lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setTourplanId(saleTourPlanId);
		String val = salesTourPlanService.getSaleTourPlanDetailsById(commonResultSetMapper);
		//System.out.println("res:" + val);
		out.print(val);
		out.flush();
		}
  }
	
	
@RequestMapping(value = "/getTourPlanHeader/{saleTourPlanId}",
method = RequestMethod.GET)
public void getTourPlanHeader(@PathVariable("saleTourPlanId") int saleTourPlanId,
				HttpSession session,
				HttpServletResponse response) throws IOException {
	
		//logger.info("In getTourPlanHeader......{}", saleTourPlanId);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
		lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setTourplanId(saleTourPlanId);
		String val = salesTourPlanService.getSaleTourHeaderById(commonResultSetMapper);
		//System.out.println("res:" + val);
		out.print(val);
		out.flush();
		}
  }
	
}
