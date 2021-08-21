package com.sharobi.yewpos.proc.controller;

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

import com.sharobi.yewpos.inv.controller.InvReportController;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;

@Controller
@RequestMapping("/procreport")
public class ProcReportController {
private final static Logger logger=LoggerFactory.getLogger(InvReportController.class);

 @Autowired
  VendorService vendorService;


	@RequestMapping(value="/purchasesummary",method=RequestMethod.GET)
	public ModelAndView purchasesummary(Model model,HttpSession session)
	{
		//logger.info("In purchasesummary......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.setViewName(ForwardConstants.VIEW_PURCHASESUMMARY_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/purchasereg",method=RequestMethod.GET)
	public ModelAndView purchasereg(Model model,HttpSession session)
	{
		//logger.info("In purchasereg......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_REG);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.setViewName(ForwardConstants.VIEW_PURCHASEREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/itemwisepurchase",method=RequestMethod.GET)
	public ModelAndView purchaseitemwise(Model model,HttpSession session)
	{
		//logger.info("In purchaseitemwise......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_ITEMWISE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.setViewName(ForwardConstants.VIEW_PURCHASEITEMWISE_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	/* ================================ Purchase Return Report Start========================= */

	@RequestMapping(value="/purchasereturnsummary",method=RequestMethod.GET)
	public ModelAndView purchasereturnsummary(Model model,HttpSession session)
	{
		//logger.info("In purchasereturnsummary......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_RETURN_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.setViewName(ForwardConstants.VIEW_PURCHASERETURNSUMMARY_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/purchasereturnreg",method=RequestMethod.GET)
	public ModelAndView purchasereturnreg(Model model,HttpSession session)
	{
		//logger.info("In purchasereturnreg......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_RETURN_REG);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.setViewName(ForwardConstants.VIEW_PURCHASERETURNREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/itemwisepurchasereturn",method=RequestMethod.GET)
	public ModelAndView itemwisepurchasereturn(Model model,HttpSession session)
	{
		//logger.info("In itemwisepurchasereturn......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_RETURN_ITEMWISE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.setViewName(ForwardConstants.VIEW_PURCHASERETURNITEMWISE_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/freeqtyreport",method=RequestMethod.GET)
	public ModelAndView vendorWiseFreeQty(Model model,HttpSession session)
	{
		//logger.info("In vendorWiseFreeQty......");
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
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_FREE_QTY);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors", allVendors);
		mav.setViewName(ForwardConstants.VIEW_FREEQTYDISTWISE_PAGE);
		return mav;
	}

	@RequestMapping(value="/vendorledger",method=RequestMethod.GET)
	public ModelAndView vendorLedger(Model model,HttpSession session)
	{
		//logger.info("In vendorLedger......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_VENDOR_LEDGER);
		mav.addObject("allVendors", allVendors);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_VENDORLEDGER_PAGE);
		return mav;
	}

	@RequestMapping(value="/poadj",method=RequestMethod.GET)
	public ModelAndView poAdj(Model model,HttpSession session)
	{
		//logger.info("In poadj......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_PROC);
		menuselect.setChildsubMenu(Constant.REP_PROC_PURCHASE_ORDER_ADJ);
		mav.addObject("allVendors", allVendors);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_PURCHASE_ORDER_ADJ);
		return mav;
	}
}
