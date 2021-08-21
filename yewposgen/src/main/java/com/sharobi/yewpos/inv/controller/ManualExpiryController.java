package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ExpiryDTO;
import com.sharobi.yewpos.inv.model.ExpiryDetailsDTO;
import com.sharobi.yewpos.inv.service.ExpiryService;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sharobi.yewpos.inv.service.ManualExpiryService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;

@Controller
@RequestMapping("/manualexpiry")
public class ManualExpiryController {
	private final static Logger logger = LoggerFactory.getLogger(ManualExpiryController.class);


	 @Autowired
	 ExpiryService expiryService;
	 @Autowired
     RoleService roleService ;
	 @Autowired
     VendorService vendorService;
	 @Autowired
	 ManualExpiryService manualExpiryService;
	 Gson gson = new Gson();
	 
	 @RequestMapping(value = "/searchmanualexpiry",
				method = RequestMethod.POST)
public ModelAndView searchExpiryInvoice(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
										HttpSession session,
										HttpServletResponse response) throws IOException {

	ModelAndView mav = new ModelAndView();
	LoginInfoByUserDTO userInfo = null;
	if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
		mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
		return mav;
	}
	String lang = null;
	if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
		lang = Constant.DEFAULT_LANG;
	}
	List<ExpiryDetailsDTO> expiryDetailsDTOs = new ArrayList<ExpiryDetailsDTO>();
	if(commonResultSetMapper.getItemId() != 0){
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			//logger.info("In searchexpiry......{},{},{}", commonResultSetMapper.toString());
			expiryDetailsDTOs = manualExpiryService.getAllExpData(commonResultSetMapper);
			mav.addObject("error",0);
	}else{
		mav.addObject("error",1);
	}
	List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
	//System.out.println("res:" + allVendors.toString());
	MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
	mav.addObject("menuByUserDTO", menuByUserDTO);
	mav.addObject("allVendors", allVendors);
	mav.addObject("expiryDetailsDTOs", expiryDetailsDTOs);
	mav.addObject("expiryId", 0);
	MenuSelection menuselect = new MenuSelection();
	menuselect.setMenu(Constant.INVENTORY);
	menuselect.setSubMenu(Constant.STOCK);
	menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE_MANUAL);
	mav.addObject("menuselect",menuselect);
	mav.setViewName(ForwardConstants.VIEW_MANUAL_STOCKEXPISSUE_PAGE);
	mav.addObject("selecteddistributor",commonResultSetMapper.getDistributorId());
	return mav;

}

	 @RequestMapping(value = "/loadmanualexpinvoicedet/{expid}",
				method = RequestMethod.GET)
		public ModelAndView loadManualExpInvoiceDet(@PathVariable("expid") Integer expid,
										Model model,
										HttpSession session) {
			//logger.info("In loadmanualexpinvoicedet......{},", expid);
			ModelAndView mav = new ModelAndView();
			LoginInfoByUserDTO userInfo = null;
			if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
				mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
				return mav;
			}
			String lang = null;
			if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
				lang = Constant.DEFAULT_LANG;
			}
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setExpiryId(expid);
			ExpiryDTO expiryHeader = expiryService.getExpiryInvoiceHeader(commonResultSetMapper);
			List<ExpiryDetailsDTO> expiryDetails = expiryService.geExpiryInvoiceDetails(commonResultSetMapper);
			List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			mav.addObject("expiryHeader", expiryHeader);
			mav.addObject("expiryDetailsDTOs", expiryDetails);
			mav.addObject("allVendors", allVendors);
			mav.addObject("expiryId", expid);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.INVENTORY);
			menuselect.setSubMenu(Constant.STOCK);
			menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE_MANUAL);
			mav.addObject("menuselect",menuselect);
			mav.setViewName(ForwardConstants.VIEW_MANUAL_STOCKEXPISSUE_PAGE);
			for(ExpiryDetailsDTO ob:expiryDetails){ 
				   mav.addObject("selecteddistributor", ob.getDistributorId());
				   break;
				}
			return mav;
		}
	 
}
