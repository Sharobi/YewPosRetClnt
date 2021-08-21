package com.sharobi.yewpos.inv.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;

@Controller
@RequestMapping("/invreport")
public class InvReportController {
	private final static Logger logger=LoggerFactory.getLogger(InvReportController.class);

	@Autowired
    InvSetupService invSetUpService;
	@Autowired
    VendorService vendorService;

	@RequestMapping(value="/stockreg",method=RequestMethod.GET)
	public ModelAndView stockReg(Model model,HttpSession session)
	{
		//logger.info("In stockReg......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
//		List<ScheduleMaster> allSchedules=invSetUpService.getAllSchedule(userInfo.getCompanyId(),lang);
		List<CategoryMaster> allCategories = invSetUpService.getAllCat(userInfo.getCompanyId(),lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_INV);
		menuselect.setChildsubMenu(Constant.REP_INV_REG);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allCategories",allCategories);
		mav.setViewName(ForwardConstants.VIEW_STOCKREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/expiryreg",method=RequestMethod.GET)
	public ModelAndView expiryReg(Model model,HttpSession session)
	{
		//logger.info("In expiryReg......");
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
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_INV);
		menuselect.setChildsubMenu(Constant.REP_INV_EXP);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allRacks", allRacks);
		mav.addObject("allVendors", allVendors);
		mav.setViewName(ForwardConstants.VIEW_EXPREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/vendorwiseexpissue",method=RequestMethod.GET)
	public ModelAndView vendorWiseExpIssue(Model model,HttpSession session)
	{
		//logger.info("In vendorWiseExpIssue......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_INV);
		menuselect.setChildsubMenu(Constant.REP_INV_EXP_DISTWISE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors", allVendors);
		mav.setViewName(ForwardConstants.VIEW_EXPDISTWISE_PAGE);
		return mav;
	}
	@RequestMapping(value="/stockonvalue",method=RequestMethod.GET)
	public ModelAndView stockOnVal(Model model,HttpSession session)
	{
		//logger.info("In stockOnVal......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(),lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_INV);
		menuselect.setChildsubMenu(Constant.STOCK_ONVALUE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allGroups",allGroups);
		mav.setViewName(ForwardConstants.VIEW_STOCKONVALUE_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/groupwisestock",method=RequestMethod.GET)
	public ModelAndView groupWiseStock(Model model,HttpSession session)
	{
		//logger.info("In groupWiseStock......");
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
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_INV);
		menuselect.setChildsubMenu(Constant.GROUP_WISE_STOCK);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_GROUP_WISE_STOCK_PAGE);
		return mav;
	}
//30.08.2019
	@RequestMapping(value="/vendorwisestock",method=RequestMethod.GET)
	public ModelAndView vendorwisestock(Model model,HttpSession session)
	{
		logger.debug("In vendorwisestock......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//System.out.println("allvendors = "+allVendors);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_INV);
		menuselect.setChildsubMenu(Constant.REP_VENDROWISE_STOCK);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors", allVendors);
		mav.setViewName(ForwardConstants.VIEW_VENDOR_WISE_STOCK_PAGE);
		return mav;
	}
	
	
}
