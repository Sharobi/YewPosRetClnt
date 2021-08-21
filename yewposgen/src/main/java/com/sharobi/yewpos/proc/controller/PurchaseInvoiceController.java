/**
 *
 */
package com.sharobi.yewpos.proc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.LocationDTO;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.RetailTypeControlDTO;
import com.sharobi.yewpos.inv.model.ReturnReasonTypeMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.TaxTypeDTO;
import com.sharobi.yewpos.inv.model.VariantDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.inv.service.ManufacturerService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.model.PurchaseInvDetailsDTO;
import com.sharobi.yewpos.proc.model.PurchaseInvHeaderDTO;
import com.sharobi.yewpos.proc.model.PurchaseInvoiceExcelDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDetailsDTO;
import com.sharobi.yewpos.proc.service.PurInvoiceService;
import com.sharobi.yewpos.proc.service.PurReturnService;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.PrintBill;
import com.sharobi.yewpos.storemgnt.model.PrintBillDetails;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author habib,Manodip
 *
 */
@Controller
@RequestMapping("/purchaseinvoice")
public class PurchaseInvoiceController {
	private final static Logger logger = LoggerFactory.getLogger(PurchaseInvoiceController.class);

	@Autowired
	PurInvoiceService purInvoiceService;
	@Autowired
	RoleService roleService;
	@Autowired
	VendorService vendorService;
	@Autowired
	InvSetupService invSetUpService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	PurReturnService purReturnService;
	Gson gson = new Gson();

	/*
	 * add on 7_11_2017
	 */
	private final static ItemService itemService = new ItemService();

	@RequestMapping(value = "/loadpurchaseinvoice", method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(Model model, HttpSession session) {
		//logger.info("In loadpurinvoice......");
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
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		commonResultSetMapper.setRetType("P");

		String countrylist = invSetUpService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);

		/* add on 7_11_2017 */
		List<TaxTypeDTO> alltaxtypeexclusive = itemService.getalltaxtype_is_exclusive(commonResultSetMapper);
		CommonResultSetMapper commonResultSetMapperMenu = new CommonResultSetMapper();
		commonResultSetMapperMenu.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperMenu.setStoreId(userInfo.getStoreId());
		commonResultSetMapperMenu.setRetailTypeID(userInfo.getRetailTypeId());
		commonResultSetMapperMenu.setMenuID(RoleBasedCostants.ITEM_SETUP_201J);
		commonResultSetMapperMenu.setLang(lang);
		List<RetailTypeControlDTO> retailTypeControlDTOs = gson.fromJson(
				invSetUpService.getAllSelectedMenuByRetailType(commonResultSetMapperMenu),
				new TypeToken<List<RetailTypeControlDTO>>() {
				}.getType());
		List<ReturnReasonTypeMaster> allReturnReasonType = invSetUpService
				.getAllReturnReasonType(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<CategoryMaster> allCat = invSetUpService.getAllCat(userInfo.getCompanyId(), lang);
		CommonResultSetMapper commonResultSetMapperSizeVarient = new CommonResultSetMapper();
		commonResultSetMapperSizeVarient.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperSizeVarient.setStoreId(userInfo.getStoreId());
		commonResultSetMapperSizeVarient.setVariantTypeId(0);
		commonResultSetMapperSizeVarient.setVariantTypeName(Constant.SIZE);
		commonResultSetMapperSizeVarient.setLang(lang);
		List<VariantDTO> sizeVariantDTOs = gson.fromJson(
				invSetUpService.getAllVariantMaster(commonResultSetMapperSizeVarient),
				new TypeToken<List<VariantDTO>>() {
				}.getType());
		CommonResultSetMapper commonResultSetMapperColorVarient = new CommonResultSetMapper();
		commonResultSetMapperColorVarient.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperColorVarient.setStoreId(userInfo.getStoreId());
		commonResultSetMapperColorVarient.setVariantTypeId(0);
		commonResultSetMapperColorVarient.setVariantTypeName(Constant.COLOR);
		commonResultSetMapperColorVarient.setLang(lang);
		List<VariantDTO> colorVariantDTOs = gson.fromJson(
				invSetUpService.getAllVariantMaster(commonResultSetMapperColorVarient),
				new TypeToken<List<VariantDTO>>() {
				}.getType());
		// all location
		CommonResultSetMapper commonResultSetMapperLoc = new CommonResultSetMapper();
		commonResultSetMapperLoc.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperLoc.setStoreId(userInfo.getStoreId());
		// commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapperLoc.setLang(lang);
		List<LocationDTO> locationDTOs = gson.fromJson(itemService.getAllLocation(commonResultSetMapperLoc),
				new TypeToken<List<LocationDTO>>() {
				}.getType());

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_309, lang);
		PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO = new PurchaseInvoiceExcelDTO();
		mav.addObject("isWholesale", userInfo.getIsWholesale());
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("sizeVariantDTOs", sizeVariantDTOs);
		mav.addObject("colorVariantDTOs", colorVariantDTOs);
		mav.addObject("locationDTOs", locationDTOs);
		/* add on 7_11_2017 */
		mav.addObject("alltaxtypelist", alltaxtypeexclusive);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("noOfDue", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("purchaseInvoiceExcelDTO", purchaseInvoiceExcelDTO);
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("expalertrequiremnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_EXPALERT_REQUIRED));
		mav.addObject("allReturnReasonTypes", allReturnReasonType);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV_NEW);
		mav.addObject("menuselect", menuselect);
		mav.addObject("msg", "-1");
		mav.addObject("allCat", allCat);
		mav.addObject("is_acc", userInfo.getIs_account());
		mav.addObject("userinformation", userInfo);
		mav.addObject("contrylist", countryMasters);
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);

		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.PURCHASE_ORDER) && ob.getIsDefault() == 1) {
				mav.addObject("poDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("poDefaultPrefix", Constant.PUR_ORDER_PREFIX);
			}
			if (ob.getQs().equals(Constant.PURCHASE_RETURN_PAYMENT_QS) && ob.getIsDefault() == 1) {
				mav.addObject("purReturnDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("purReturnDefaultPrefix", Constant.PURCHASE_INVOICE_RETURN_MEMO);
			}
		}

		mav.setViewName(ForwardConstants.VIEW_PURCHASEINVOICE_PAGE);
		return mav;
	}

	@RequestMapping(value = "/loadpurchaseinvoicedet/{pid}", method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(@PathVariable("pid") Integer pid, Model model, HttpSession session) {
		//logger.info("In loadpurinvoicedet......{},", pid);
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
		commonResultSetMapper.setPurchaseOrderId(pid);
		/*
		 * commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		 * commonResultSetMapper.setStoreId(userInfo.getStoreId());
		 * commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		 * commonResultSetMapper.setLang(lang); commonResultSetMapper.setRetType("P");
		 */
		List<ReturnReasonTypeMaster> allReturnReasonType = invSetUpService
				.getAllReturnReasonType(commonResultSetMapper);
		PurchaseInvHeaderDTO purchaseHeader = purInvoiceService.getPurchaseInvoiceDirectHeader(commonResultSetMapper); // changed
																														// FOR
																														// DIRECT
																														// /
																														// INDIRECT
		//System.out.println("_______purchaseHeader_________ = " + gson.toJson(purchaseHeader));
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<PurchaseInvDetailsDTO> purchaseDetails = purInvoiceService
				.getPurchaseInvoiceDirectDetails(commonResultSetMapper); // changed
																			// for
																			// direct
																			// /indirect
		//System.out.println("_________purchaseDetails______________ = " + gson.toJson(purchaseDetails));
		List<CategoryMaster> allCat = invSetUpService.getAllCat(userInfo.getCompanyId(), lang);

		CommonResultSetMapper commonResultSetMapperMenu = new CommonResultSetMapper();
		commonResultSetMapperMenu.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperMenu.setStoreId(userInfo.getStoreId());
		commonResultSetMapperMenu.setRetailTypeID(userInfo.getRetailTypeId());
		commonResultSetMapperMenu.setMenuID(RoleBasedCostants.ITEM_SETUP_201J);
		commonResultSetMapperMenu.setLang(lang);
		List<RetailTypeControlDTO> retailTypeControlDTOs = gson.fromJson(
				invSetUpService.getAllSelectedMenuByRetailType(commonResultSetMapperMenu),
				new TypeToken<List<RetailTypeControlDTO>>() {
				}.getType());

		CommonResultSetMapper commonResultSetMapperSizeVarient = new CommonResultSetMapper();
		commonResultSetMapperSizeVarient.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperSizeVarient.setStoreId(userInfo.getStoreId());
		commonResultSetMapperSizeVarient.setVariantTypeId(0);
		commonResultSetMapperSizeVarient.setVariantTypeName(Constant.SIZE);
		commonResultSetMapperSizeVarient.setLang(lang);
		List<VariantDTO> sizeVariantDTOs = gson.fromJson(
				invSetUpService.getAllVariantMaster(commonResultSetMapperSizeVarient),
				new TypeToken<List<VariantDTO>>() {
				}.getType());
		CommonResultSetMapper commonResultSetMapperColorVarient = new CommonResultSetMapper();
		commonResultSetMapperColorVarient.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperColorVarient.setStoreId(userInfo.getStoreId());
		commonResultSetMapperColorVarient.setVariantTypeId(0);
		commonResultSetMapperColorVarient.setVariantTypeName(Constant.COLOR);
		commonResultSetMapperColorVarient.setLang(lang);
		List<VariantDTO> colorVariantDTOs = gson.fromJson(
				invSetUpService.getAllVariantMaster(commonResultSetMapperColorVarient),
				new TypeToken<List<VariantDTO>>() {
				}.getType());
		// all location
		CommonResultSetMapper commonResultSetMapperLoc = new CommonResultSetMapper();
		commonResultSetMapperLoc.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperLoc.setStoreId(userInfo.getStoreId());
		// commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapperLoc.setLang(lang);
		List<LocationDTO> locationDTOs = gson.fromJson(itemService.getAllLocation(commonResultSetMapperLoc),
				new TypeToken<List<LocationDTO>>() {
				}.getType());

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("isWholesale", userInfo.getIsWholesale());
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("purchaseHeader", purchaseHeader);
		mav.addObject("purchaseDetails", purchaseDetails);
		mav.addObject("allRacks", allRacks);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("sizeVariantDTOs", sizeVariantDTOs);
		mav.addObject("colorVariantDTOs", colorVariantDTOs);
		mav.addObject("locationDTOs", locationDTOs);
		mav.addObject("allReturnReasonTypes", allReturnReasonType);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("allCat", allCat);
		mav.addObject("noOfDue", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("expalertrequiremnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_EXPALERT_REQUIRED));
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV_NEW);
		mav.addObject("menuselect", menuselect);
		mav.addObject("is_acc", userInfo.getIs_account());
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);

		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.PURCHASE_RETURN_PAYMENT_QS) && ob.getIsDefault() == 1) {
				mav.addObject("purReturnDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("purReturnDefaultPrefix", Constant.PURCHASE_INVOICE_RETURN_MEMO);
			}

		}

		mav.setViewName(ForwardConstants.VIEW_PURCHASEINVOICE_DETAILS_PAGE);
		return mav;
	}

	@RequestMapping(value = "/purchasebillfromchallan", method = RequestMethod.GET)
	public ModelAndView loadPurBillFromChallan(Model model, HttpSession session) {
		//logger.info("In loadpurinvoice......");
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
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		commonResultSetMapper.setRetType("P");

		String countrylist = invSetUpService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);

		/* add on 7_11_2017 */
		List<TaxTypeDTO> alltaxtypeexclusive = itemService.getalltaxtype_is_exclusive(commonResultSetMapper);
		CommonResultSetMapper commonResultSetMapperMenu = new CommonResultSetMapper();
		commonResultSetMapperMenu.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperMenu.setStoreId(userInfo.getStoreId());
		commonResultSetMapperMenu.setRetailTypeID(userInfo.getRetailTypeId());
		commonResultSetMapperMenu.setMenuID(RoleBasedCostants.ITEM_SETUP_201J);
		commonResultSetMapperMenu.setLang(lang);
		List<RetailTypeControlDTO> retailTypeControlDTOs = gson.fromJson(
				invSetUpService.getAllSelectedMenuByRetailType(commonResultSetMapperMenu),
				new TypeToken<List<RetailTypeControlDTO>>() {
				}.getType());
		List<ReturnReasonTypeMaster> allReturnReasonType = invSetUpService
				.getAllReturnReasonType(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<CategoryMaster> allCat = invSetUpService.getAllCat(userInfo.getCompanyId(), lang);
		CommonResultSetMapper commonResultSetMapperSizeVarient = new CommonResultSetMapper();
		commonResultSetMapperSizeVarient.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperSizeVarient.setStoreId(userInfo.getStoreId());
		commonResultSetMapperSizeVarient.setVariantTypeId(0);
		commonResultSetMapperSizeVarient.setVariantTypeName(Constant.SIZE);
		commonResultSetMapperSizeVarient.setLang(lang);
		List<VariantDTO> sizeVariantDTOs = gson.fromJson(
				invSetUpService.getAllVariantMaster(commonResultSetMapperSizeVarient),
				new TypeToken<List<VariantDTO>>() {
				}.getType());
		CommonResultSetMapper commonResultSetMapperColorVarient = new CommonResultSetMapper();
		commonResultSetMapperColorVarient.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperColorVarient.setStoreId(userInfo.getStoreId());
		commonResultSetMapperColorVarient.setVariantTypeId(0);
		commonResultSetMapperColorVarient.setVariantTypeName(Constant.COLOR);
		commonResultSetMapperColorVarient.setLang(lang);
		List<VariantDTO> colorVariantDTOs = gson.fromJson(
				invSetUpService.getAllVariantMaster(commonResultSetMapperColorVarient),
				new TypeToken<List<VariantDTO>>() {
				}.getType());
		// all location
		CommonResultSetMapper commonResultSetMapperLoc = new CommonResultSetMapper();
		commonResultSetMapperLoc.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperLoc.setStoreId(userInfo.getStoreId());
		// commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapperLoc.setLang(lang);
		List<LocationDTO> locationDTOs = gson.fromJson(itemService.getAllLocation(commonResultSetMapperLoc),
				new TypeToken<List<LocationDTO>>() {
				}.getType());

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO = new PurchaseInvoiceExcelDTO();
		mav.addObject("isWholesale", userInfo.getIsWholesale());
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("sizeVariantDTOs", sizeVariantDTOs);
		mav.addObject("colorVariantDTOs", colorVariantDTOs);
		mav.addObject("locationDTOs", locationDTOs);
		/* add on 7_11_2017 */
		mav.addObject("alltaxtypelist", alltaxtypeexclusive);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("noOfDue", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("purchaseInvoiceExcelDTO", purchaseInvoiceExcelDTO);
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("expalertrequiremnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_EXPALERT_REQUIRED));
		mav.addObject("allReturnReasonTypes", allReturnReasonType);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_BILL_CHALAN);
		mav.addObject("menuselect", menuselect);
		mav.addObject("msg", "-1");
		mav.addObject("allCat", allCat);
		mav.addObject("is_acc", userInfo.getIs_account());
		mav.addObject("userinformation", userInfo);
		mav.addObject("contrylist", countryMasters);
		mav.setViewName(ForwardConstants.VIEW_PURCHASEBILLCHALLAN_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getPurchaseDetailsbyPurchaseIDs/{purchaseIds}", method = RequestMethod.GET)
	public ModelAndView getPurchaseDetailsbyPurchaseIDs(@PathVariable("purchaseIds") String purchaseIds, Model model,
			HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In getPurchaseDetailsbyPurchaseIDs ......");
		//System.out.println("getPurchaseDetailsbyPurchaseIDs purchaseIds = " + purchaseIds);
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setPurchaseIds(purchaseIds);

		String purchaseDetails = purInvoiceService.getPurchaseDetailsbyPurchaseIDs(commonResultSetMapper);
		//System.out.println("In getPurchaseDetailsbyPurchaseIDs Pur inv List :" + purchaseDetails);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.print(purchaseDetails);
		out.flush();
		return null;
	}

	// new method added 23-05-19
	/*
	 * ======================== Print Purchase Start ==========================
	 */

	@RequestMapping(value = "/printPurchaseInvoice", method = RequestMethod.GET)
	public ModelAndView printPurInvoice(Model model, HttpSession session,
			@RequestParam(required = false, defaultValue = "0") Integer pid,
			@RequestParam(required = false) String backUrl) {

		//logger.debug("In printPurchaseInvoice......{},{},", pid, backUrl);
		ModelAndView mav = new ModelAndView();
		String returnDt = "";
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
		commonResultSetMapper.setPurchaseOrderId(pid);
		// commonResultSetMapper.setPurInvId(pid);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		// Purchase purchaseHeader =
		// purInvoiceService.getPurchaseInvoiceHeader(commonResultSetMapper);
		PurchaseInvHeaderDTO purchaseHeader = purInvoiceService.getPurchaseInvoiceDirectHeader(commonResultSetMapper);
		// List<PurchaseDetails> purchaseDetails =
		// purInvoiceService.getPurchaseInvoiceDetails(commonResultSetMapper);
		List<PurchaseInvDetailsDTO> purchaseDetails = purInvoiceService
				.getPurchaseInvoiceDirectDetails(commonResultSetMapper); // changed for direct /indirect
		commonResultSetMapper.setDistributorId(purchaseHeader.getDistributorId());
		DistributorMaster distributorDetails = gson.fromJson(vendorService.getVendorDet(commonResultSetMapper),
				new TypeToken<DistributorMaster>() {
				}.getType());
		List<PurchaseReturnDTO> purchaseReturnHeader = purInvoiceService.getPurchaseReturnHeader(commonResultSetMapper);
		if (!purchaseReturnHeader.isEmpty()) {
			commonResultSetMapper.setPurchaseReturnId(purchaseReturnHeader.get(0).getPurchaseReturnId());
			returnDt = purchaseReturnHeader.get(0).getInvDate();
		}
		List<PurchaseReturnDetailsDTO> purchaseReturnDetailsDTOs = gson.fromJson(
				purReturnService.getPurInvReturnMemoDetailsById(commonResultSetMapper),
				new TypeToken<List<PurchaseReturnDetailsDTO>>() {
				}.getType());
		/*
		 * int manuId=0; for(PurchaseDetails purdetails : purchaseDetails) {
		 * manuId=purdetails.getManuId(); } ManufacturerMaster manufacturerDetails =
		 * manufacturerService.getManufacturerbyId(manuId,lang);
		 */

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("purchaseHeader", purchaseHeader);
		mav.addObject("purchaseDetails", purchaseDetails);
		mav.addObject("backUrl", backUrl);
		mav.addObject("pinvId", pid);/* Sayantan Mahanty added date-20/02/2020 */
		mav.addObject("distributorDetails", distributorDetails);
		mav.addObject("purchaseReturnHeader", purchaseReturnHeader);
		mav.addObject("purchaseReturnDetailsDTOs", purchaseReturnDetailsDTOs);
		mav.addObject("purReturnId", commonResultSetMapper.getPurchaseReturnId());
		mav.addObject("returnDt", returnDt);
		// mav.setViewName(ForwardConstants.VIEW_PRINTPUR_PAGE);

		PrintBill billPrintData = (PrintBill) session.getAttribute(Constant.SES_BILL_PRINT_DATA);
		List<PrintBillDetails> billPrintDetailsData = (List<PrintBillDetails>) session
				.getAttribute(Constant.SES_BILL_PRINT_DETAILS_DATA);
		for (PrintBillDetails ob : billPrintDetailsData) {
			if (ob.getBillType().equalsIgnoreCase("PURINV")) {
				ForwardConstants.VIEW_PRINT_PUR_INVOICE_BILL_PAGE = ob.getPageUrl();
			}
		}
		/* mav.setViewName(ForwardConstants.VIEW_PRINT_PUR_INVOICE_BILL_PAGE); */
		// mav.setViewName(ForwardConstants.VIEW_PRINTINVOICE_PAGE);
		mav.setViewName(ForwardConstants.VIEW_PRINTPURINV_PAGE);/* Sayantan Mahanty added date-20/02/2020 */
		return mav;
	}

	/* ======================== Print Purchase End ============================ */
}
