/**
 *
 */
package com.sharobi.yewpos.proc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestBody;
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
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.RetailTypeControlDTO;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.TaxTypeDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleOrderDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleOrderHeaderDTO;
import com.sharobi.yewpos.pos.model.TaxDetailsSaleBillDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.PurchaseOrder;
import com.sharobi.yewpos.proc.model.PurchaseOrderDTO;
import com.sharobi.yewpos.proc.model.PurchaseOrderDetailsDTO;
import com.sharobi.yewpos.proc.service.PurOrderService;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.PrintBill;
import com.sharobi.yewpos.storemgnt.model.PrintBillDetails;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping("/purorder")
public class PurOrderController {
	private final static Logger logger = LoggerFactory.getLogger(PurOrderController.class);

	@Autowired
	RoleService roleService;
	@Autowired
	VendorService vendorService;
	@Autowired
	InvSetupService invSetUpService;
	@Autowired
	PurOrderService purOrderService;
	@Autowired
	ItemService itemService;
	Gson gson = new Gson();

	@RequestMapping(value = "/loadpurorder", method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(Model model, HttpSession session) {
		//logger.info("In loadpurorder......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
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
		List<CategoryMaster> allCat = invSetUpService.getAllCat(userInfo.getCompanyId(), lang);
		/* Sayantan Mahanty added date-19/02/2020 */
		String countrylist = invSetUpService.getAllCountryList(commonResultSetMapperMenu);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);
		/* add on 7_11_2017 */
		List<TaxTypeDTO> alltaxtypeexclusive = itemService.getalltaxtype_is_exclusive(commonResultSetMapperMenu);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("alltaxtypelist", alltaxtypeexclusive);
		mav.addObject("userinformation", userInfo);
		/*
		 * mav.addObject("sizeVariantDTOs", sizeVariantDTOs);
		 * mav.addObject("colorVariantDTOs", colorVariantDTOs);
		 * mav.addObject("locationDTOs", locationDTOs);
		 * mav.addObject("allReturnReasonTypes",allReturnReasonType);
		 * mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		 */
		mav.addObject("contrylist", countryMasters); /* Sayantan Mahanty added date-19/02/2020 */
		mav.addObject("allCat", allCat);

		mav.addObject("noOfDue", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("isWholesale", userInfo.getIsWholesale());

		mav.addObject("purOrderQtyFromSale",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE));
		mav.addObject("saleHistoryDay",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_SALE_HISTORY_DAY));
		mav.addObject("dayToPurchase", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_DAY_TO_PURCHASE));
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD);
		mav.addObject("menuselect", menuselect);
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);

		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.SALE_ORDER_QS) && ob.getIsDefault() == 1) {
				mav.addObject("soDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("soDefaultPrefix", Constant.SALE_ORDER_PREFIX);
			}
		}

		mav.setViewName(ForwardConstants.VIEW_PURORDER_PAGE);
		return mav;
	}

	@RequestMapping(value = "/loadpurordregister", method = RequestMethod.GET)
	public ModelAndView loadPurInvRegister(Model model, HttpSession session) {
		//logger.info("In loadpurordregister......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD_REG);
		mav.addObject("menuselect", menuselect);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);

		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);

		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.PURCHASE_ORDER) && ob.getIsDefault() == 1) {
				mav.addObject("poDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("poDefaultPrefix", Constant.PUR_ORDER_PREFIX);
			}
		}

		mav.setViewName(ForwardConstants.VIEW_PURORDREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/createorupdatepurchaseorder", method = RequestMethod.POST)
	public void createPurchaseOrder(@RequestBody String purchaseString, HttpSession session,
			HttpServletResponse response) throws IOException {

		//logger.info("In createorupdatepurchaseorder......{},{},{}", purchaseString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			PurchaseOrder purchaseOrder = gson.fromJson(purchaseString, new TypeToken<PurchaseOrder>() {
			}.getType());
			purchaseOrder.setCompanyId(userInfo.getCompanyId());
			purchaseOrder.setStoreId(userInfo.getStoreId());
			purchaseOrder.setCreatedBy(userInfo.getId());
			purchaseOrder.setFinyrId(userInfo.getFinyrId());
			purchaseOrder.setFinyrCode(userInfo.getFinyrCode());
			purchaseOrder.setLang(lang);
			String res = purOrderService.createOrUpdatePurchaseOrder(purchaseOrder);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/createtemppofromsale", method = RequestMethod.POST)
	public void createTempPOFromSale(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In createTempPOFromSale......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.createTempPOFromSale(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loadpurorderdet/{pid}", method = RequestMethod.GET)
	public ModelAndView loadPurOrderDet(@PathVariable("pid") Integer pid, Model model, HttpSession session) {
		//logger.info("In loadpurorderdet......{},", pid);
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
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		PurchaseOrderDTO purchaseOrderHeader = purOrderService.getPurchaseOrderHeader(commonResultSetMapper);
		commonResultSetMapper.setDistributorId(purchaseOrderHeader.getDistributorId());
		DistributorMaster vendorDetails = gson.fromJson(vendorService.getVendorDet(commonResultSetMapper),
				new TypeToken<DistributorMaster>() {
				}.getType());
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<PurchaseOrderDetailsDTO> purchaseOrderDetails = purOrderService
				.getPurchaseOrderDetails(commonResultSetMapper);

		//System.err.println("___________purchaseOrderDetails______________" + purchaseOrderDetails.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("purchaseOrderHeader", purchaseOrderHeader);
		mav.addObject("purchaseOrderDetails", purchaseOrderDetails);
		mav.addObject("allRacks", allRacks);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("distEmail", vendorDetails.getEmail());
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("purOrderQtyFromSale",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE));
		mav.addObject("saleHistoryDay",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_SALE_HISTORY_DAY));
		mav.addObject("dayToPurchase", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_DAY_TO_PURCHASE));

		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("isWholesale", userInfo.getIsWholesale());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD);
		mav.addObject("menuselect", menuselect);
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.SALE_ORDER_QS) && ob.getIsDefault() == 1) {
				mav.addObject("soDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("soDefaultPrefix", Constant.SALE_ORDER_PREFIX);
			}
		}
		mav.setViewName(ForwardConstants.VIEW_PURORDER_DETAILS_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchpurchaseorderregister", method = RequestMethod.POST)
	public ModelAndView searchPORegister(
			@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper, HttpSession session,
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
		String invno = commonResultSetMapper.getInvoiceNo();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.PURCHASE_ORDER + "/" + commonResultSetMapper.getFinyrCode() + "/"
				+ commonResultSetMapper.getInvoiceNo());
		//logger.info("In searchpurchaseorderregister......{}", commonResultSetMapper.toString());
		List<PurchaseOrderDTO> purchaseOrderList = purOrderService.getAllPurchaseOrder(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("purchaseOrderList", purchaseOrderList);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);

		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.PURCHASE_ORDER) && ob.getIsDefault() == 1) {
				mav.addObject("poDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("poDefaultPrefix", Constant.PUR_ORDER_PREFIX);
			}
		}

		mav.setViewName(ForwardConstants.VIEW_PURORDREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getpurorderbytype", method = RequestMethod.POST)
	public void getPurOrderByType(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getPurOrderByType......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.getPurOrderByType(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deletepurorder", method = RequestMethod.POST)
	public void deletePurOrder(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In deletepurorder......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.deletePurchaseOrder(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postpurorder", method = RequestMethod.POST)
	public void postPurOrder(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {

		//logger.info("In postpurorder......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.postPurchaseOrder(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getpurorderdetbyordernoforpi", method = RequestMethod.POST)
	public void getPurOrderDetByOrderNoforPI(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getPurOrderDetByOrderNoforPI......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.getPurOrderDetailsByOrderNoForPI(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/closepurorder", method = RequestMethod.POST)
	public void closePurOrder(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In closepurorder......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.closePurchaseOrder(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getcalpurordrqty", method = RequestMethod.POST)
	public void getCalPurOrdrQty(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getcalpurordrqty......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purOrderService.calPurOrdrQty(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

//17-06-2019 for purchaseOrder bill print

	@RequestMapping(value = "/reprintmemo/purorder", method = RequestMethod.GET)
	public ModelAndView purchaseOrder(Model model, HttpSession session,
			@RequestParam(required = false, defaultValue = "0") String purId,
			@RequestParam(required = false) String reprint, @RequestParam(required = false) String backUrl)
			throws ParseException {
		//logger.info("In /reprintmemo/purorder......{},{}", purId, backUrl);
		if (!purId.equals("0")) {
			purId = (String.valueOf(purId).split("-")[2]);
			//System.out.println("new purId=" + purId);
		}
		int manuId = 0;
		ModelAndView mav = new ModelAndView();
		PurchaseOrderDTO purchaseOrderHeader = new PurchaseOrderDTO();
		List<PurchaseOrderDetailsDTO> purchaseOrderDetails = new ArrayList<PurchaseOrderDetailsDTO>();
		// List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
		List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
		String printCountRes = "";
		int isRePrint = 0;
		int printCount = 0;
		// ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			//System.err.println("new userInfo=" + userInfo);
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			//System.err.println("new lang=" + lang);
			lang = Constant.DEFAULT_LANG;
		}
		if (!purId.equals("0")) {
			String flag = "";
			/*
			 * if(reprint.equals("Y")) { flag = "r"; isRePrint=1; printCountRes =
			 * reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			 * System.out.println("print count status: "+printCountRes);
			 * printCount=Integer.valueOf(printCountRes); }else{}
			 */

			// StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			// commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
			commonResultSetMapper.setPurchaseOrderId(Integer.valueOf(purId));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setIsRePrint(isRePrint);
			commonResultSetMapper.setPrintCount(printCount);
			commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
			commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
			//logger.info("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
			//System.out.println("ss=" + commonResultSetMapper.getNoteLineOne());
			// commonResultSetMapper.setLang(lang);

			purchaseOrderHeader = purOrderService.getPurchaseOrderHeader(commonResultSetMapper);
			commonResultSetMapper.setDistributorId(purchaseOrderHeader.getDistributorId());
			purchaseOrderDetails = purOrderService.getPurchaseOrderDetails(commonResultSetMapper);

			//System.err.println("___________purchaseOrderDetails______________" + purchaseOrderDetails.toString());

			// saleHeaderDTO =gson.fromJson(
			// cashMemoService.getSalesOrderHeaderForBill(commonResultSetMapper), new
			// TypeToken<SaleOrderHeaderDTO>() {}.getType());
			// saleDetailsDTOs =
			// gson.fromJson(cashMemoService.getSaleOrderDetailsForBill(commonResultSetMapper),
			// new TypeToken<List<SaleOrderDetailsDTO>>() {}.getType());
			// taxDetailsDTOs =
			// gson.fromJson(cashMemoService.getTaxDetailsForSaleOrderBill(commonResultSetMapper),
			// new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());

			/*
			 * saleHeaderDTO =gson.fromJson(
			 * cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new
			 * TypeToken<SaleHeaderDTO>() {}.getType()); saleDetailsDTOs =
			 * gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper),
			 * new TypeToken<List<SaleDetailsDTO>>() {}.getType()); taxDetailsDTOs =
			 * gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper),
			 * new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());
			 */

			//System.out.println("====########====" + purchaseOrderHeader.toString());
			//System.out.println("====########====" + purchaseOrderDetails.toString());
			/*
			 * for(SaleDetailsDTO saledetails : saleDetailsDTOs) {
			 * manuId=saledetails.getManufacturerId(); } manufacturerDetails =
			 * manufacturerService.getManufacturerbyId(manuId,lang);
			 */
			/*
			 * if(userInfo.getCompanyId() == 18){ SimpleDateFormat dt1 = new
			 * SimpleDateFormat("MMMM dd, yyyy"); SimpleDateFormat dt2 = new
			 * SimpleDateFormat("yyyy-MM-dd"); SimpleDateFormat newdt = new
			 * SimpleDateFormat("dd-MM-yyyy"); String invDate= saleHeaderDTO.getInvDate();
			 * Date invdate = dt1.parse(invDate); String dueDate =
			 * saleHeaderDTO.getDueDate(); Date duedate = dt2.parse(dueDate);
			 * saleHeaderDTO.setInvDate(newdt.format(invdate));
			 * saleHeaderDTO.setDueDate(newdt.format(duedate)); }
			 */

			mav.addObject("purId", purId);
			/* mav.addObject("backUrl",backUrl+"/"+saleId); */
			mav.addObject("backUrl", backUrl);
			mav.addObject("reprint", reprint);
			mav.addObject("printCountRes", printCountRes);
			mav.addObject("purchaseHeader", purchaseOrderHeader);
			mav.addObject("purchaseDetails", purchaseOrderDetails);
			mav.addObject("taxDetailsDTOs", taxDetailsDTOs);
			// mav.addObject("isCur",store.getCurrencyId());
			// System.err.println(store.getCurrencyId());
		}
		// StoreMaster store =
		// (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
		/* mav.setViewName(ForwardConstants.VIEW_PRINT_SALEORDER_NONCOMPOSITE_PAGE); */

		/*
		 * if(userInfo.getCompanyId() == 18){ mav.setViewName(ForwardConstants.
		 * VIEW_PRINTPURCHASEORDER_NONCOMPOSITE_SPECIAL_PAGE); }else{
		 * mav.setViewName(ForwardConstants.VIEW_PRINT_PURCHASEORDER_NONCOMPOSITE_PAGE);
		 * }
		 */

		PrintBill billPrintData = (PrintBill) session.getAttribute(Constant.SES_BILL_PRINT_DATA);
		List<PrintBillDetails> billPrintDetailsData = (List<PrintBillDetails>) session
				.getAttribute(Constant.SES_BILL_PRINT_DETAILS_DATA);

		for (PrintBillDetails ob : billPrintDetailsData) {
			if (ob.getBillType().equalsIgnoreCase("PO")) {
				ForwardConstants.VIEW_PRINT_PUR_ORDER_BILL_PAGE = ob.getPageUrl();
			}
		}
		/* mav.setViewName(ForwardConstants.VIEW_PRINT_PUR_ORDER_BILL_PAGE); */
		mav.setViewName(ForwardConstants.VIEW_PRINTPURCHASEORDER_NONCOMPOSITE_SPECIAL_PAGE);

		return mav;
	}

}
