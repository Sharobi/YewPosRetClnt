/**
 *
 */
package com.sharobi.yewpos.inv.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.LocationDTO;
import com.sharobi.yewpos.inv.model.OpeningStock;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.RetailTypeControlDTO;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.StockDetailsAdjustmentDTO;
import com.sharobi.yewpos.inv.model.StockTransfer;
import com.sharobi.yewpos.inv.model.StockTransferDTO;
import com.sharobi.yewpos.inv.model.StockTransferDetails;
import com.sharobi.yewpos.inv.model.StockTransferDetailsDTO;
import com.sharobi.yewpos.inv.model.TaxTypeDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.inv.service.StockService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

/**
 * @author habib,Manodip
 *
 */
@Controller
@RequestMapping("/stock")
public class StockController {
	private final static Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService stockService;
	@Autowired
	VendorService vendorService;
	@Autowired
	InvSetupService invSetUpService;
	@Autowired
	RoleService roleService;
	@Autowired
	ItemService itemService;
	@Autowired
	CashMemoService cashMemoService;
	Gson gson = new Gson();

	@RequestMapping(value = "/stockentry", method = RequestMethod.GET)
	public ModelAndView loadStockentry(Model model, HttpSession session) {
		//logger.info("In stockentry......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<CategoryMaster> allCat = invSetUpService.getAllCat(userInfo.getCompanyId(), lang);

		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
//		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);

		List<TaxTypeDTO> alltaxtypeexclusive = itemService.getalltaxtype_is_exclusive(commonResultSetMapper);

		String countrylist = invSetUpService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);
		mav.addObject("allVendors", allVendors);
		// all location
		CommonResultSetMapper commonResultSetMapperLoc = new CommonResultSetMapper();
		commonResultSetMapperLoc.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperLoc.setStoreId(userInfo.getStoreId());
//				commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapperLoc.setLang(lang);
		List<LocationDTO> locationDTOs = gson.fromJson(itemService.getAllLocation(commonResultSetMapperLoc),
				new TypeToken<List<LocationDTO>>() {
				}.getType());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ENTRY);
		mav.addObject("menuselect", menuselect);
		mav.addObject("storeId", userInfo.getStoreId());
		mav.addObject("createdBy", userInfo.getId());
		mav.addObject("finyrId", userInfo.getFinyrId());
		mav.addObject("cmpnyId", userInfo.getCompanyId());
		mav.addObject("msg", "fresh");
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("allCat", allCat);
		mav.addObject("locationDTOs", locationDTOs);
		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("alltaxtypelist", alltaxtypeexclusive);
		mav.addObject("contrylist", countryMasters);
		mav.addObject("isWholesale", userInfo.getIsWholesale());
		mav.addObject("userinformation", userInfo);
		mav.setViewName(ForwardConstants.VIEW_STOCKENTRY_PAGE);
		return mav;
	}

	@RequestMapping(value = "/createorupdateStock", method = RequestMethod.POST)
	public void createorupdateStock(@RequestBody String stockString, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In createorupdateStock......{},{},{}", stockString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			OpeningStock stockStringObj = gson.fromJson(stockString, new TypeToken<OpeningStock>() {
			}.getType());
			/*
			 * String stockStringDetailsObj =
			 * stockStringObj.getOpeningStockDetails().toString(); //OpeningStockDetails
			 * ostd = (OpeningStockDetails) stockStringObj.getOpeningStockDetails();
			 * //OpeningStockDetails ostd[] = gson.fromJson(stockStringDetailsObj, new
			 * TypeToken<OpeningStockDetails>() {}.getType()); List<OpeningStockDetails>
			 * ostd = (List<OpeningStockDetails>) stockStringObj.getOpeningStockDetails();
			 * List<OpeningStockDetails> obj = null; for(OpeningStockDetails
			 * openingStockDetails : ostd) {
			 * openingStockDetails.setStoreId(userInfo.getStoreId());
			 * openingStockDetails.setCreatedBy(userInfo.getId());
			 * openingStockDetails.setFinyrId(userInfo.getFinyrId());
			 * openingStockDetails.setCreatedDate(DateUtil.getCurrentDate());
			 * stockStringObj.setOpeningStockDetails((List<OpeningStockDetails>)
			 * openingStockDetails); }
			 * 
			 */

			String res = stockService.createorupdateStock(stockStringObj);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/stockadj", method = RequestMethod.GET)
	public ModelAndView loadStockadj(Model model, HttpSession session) {
		//logger.info("In stockadj......");
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
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ADJ);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("menuselect", menuselect);
		mav.addObject("allVendors", allVendors);
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.setViewName(ForwardConstants.VIEW_STOCKADJ_PAGE);
		return mav;
	}

	@RequestMapping(value = "/stkadjupdatesearch", method = RequestMethod.POST)
	public ModelAndView searchItem(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
			HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In stkadjupdate......{},{},{}", commonResultSetMapper.toString());
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
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ADJ);
		/*
		 * CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		 * commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"
		 * ));
		 * commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"))
		 * ;
		 */
		List<StockDetailsAdjustmentDTO> stockDetailsAdjustmentDTOs = stockService
				.getStockDetailsForAdjustment(commonResultSetMapper);
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("menuselect", menuselect);
		mav.addObject("allVendors", allVendors);
		mav.addObject("stockDetailsAdjustmentDTOs", stockDetailsAdjustmentDTOs);
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.setViewName(ForwardConstants.VIEW_STOCKADJ_PAGE);
		return mav;
	}

	@RequestMapping(value = "/stkadjupdate", method = RequestMethod.POST)
	public void stkAdjUpdate(@RequestBody String StockDetailsAdjustmentDTOObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In stkadjupdate......{},{},{}", StockDetailsAdjustmentDTOObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			StockDetailsAdjustmentDTO commonResultSetMapper = gson.fromJson(StockDetailsAdjustmentDTOObj,
					new TypeToken<StockDetailsAdjustmentDTO>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinyrId(userInfo.getFinyrId());
			commonResultSetMapper.setCreatedBy(userInfo.getId());
			// commonResultSetMapper.setLang(lang);
			ResponseObj res = stockService.updateStkAdj(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(gson.toJson(res));
			out.flush();
		}
	}

	@RequestMapping(value = "/stkadjdelete", method = RequestMethod.POST)
	public void stkAdjDelete(@RequestBody String commonResultSetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In stkAdjDelete......{},{},{}", commonResultSetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			ResponseObj res = stockService.deleteStkAdj(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(gson.toJson(res));
			out.flush();
		}
	}

	@RequestMapping(value = "/expissue", method = RequestMethod.GET)
	public ModelAndView loadExpIssue(Model model, HttpSession session) {
		//logger.info("In expissue......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		// List<DistributorMaster> allVendors =
		// vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.EXPIRE_QS) && ob.getIsDefault() == 1) {
				mav.addObject("expiryDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("expiryDefaultPrefix", Constant.EXPIRY_INVOICE_MEMO);
			}
		}
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("allVendors", allVendors);
		mav.addObject("menuselect", menuselect);
		mav.addObject("expiryId", 0);
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUE_PAGE);
		return mav;
	}

	@RequestMapping(value = "/expissuereg", method = RequestMethod.GET)
	public ModelAndView loadExpIssueReg(Model model, HttpSession session) {
		//logger.info("In expissuereg......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUEREG);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.EXPIRE_QS) && ob.getIsDefault() == 1) {
				mav.addObject("expiryDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("expiryDefaultPrefix", Constant.EXPIRY_INVOICE_MEMO);
			}
		}
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUEREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getcurrstock/{itemid}", method = RequestMethod.GET)
	public void getCurrStock(@PathVariable int itemid, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In getcurrstock......{}", itemid);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setItemId(itemid);
			commonResultSetMapper.setLang(lang);
			String res = stockService.getCurrentStockOfItem(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getcurrstocksku/{barcode}", method = RequestMethod.GET)
	public void getCurrStock(@PathVariable String barcode, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In getcurrstocksku......{}", barcode);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setSku(barcode);
			commonResultSetMapper.setLang(lang);
			String res = stockService.getCurrentStockOfItemByBarcode(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getcurrstockbybatexpmrp", method = RequestMethod.POST)
	public void getCurrentStockByBatExpMrp(@RequestBody String commonResultSetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getCurrentStockByBatExpMrp......{},{},{}", commonResultSetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = stockService.getCurrentStockOfItemByBatExpMrp(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getserialnobyitemid", method = RequestMethod.POST)
	public void getSerialNoByItemid(@RequestBody String commonResultSetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getSerialNoByItemid......{},{},{}", commonResultSetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = stockService.getSerialNoByItemid(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/uploadstockexcel", method = RequestMethod.POST)
	public ModelAndView uploadStock(
			@Valid @ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
			@RequestParam MultipartFile fileUpload, HttpSession session, HttpServletResponse response,
			HttpServletRequest request, BindingResult result) {
		logger.debug("uploadstockexcel...! ");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		InputStream inputFile = null;
		byte[] byteArr = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		if (fileUpload != null && fileUpload.getSize() > 0) {
			try {
				//System.out.println("file name:" + fileUpload.getName());
				//System.out.println("original file name:" + fileUpload.getOriginalFilename());
				//System.out.println("file size:" + fileUpload.getSize());
				inputFile = fileUpload.getInputStream();
				byteArr = fileUpload.getBytes();
				//System.out.println("inputsterm:" + inputFile);

				String uploadsDir = "/uploads/";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				//System.out.println("path:" + realPathtoUploads);
				String orgName = fileUpload.getOriginalFilename();
				String filePath = realPathtoUploads + "/" + orgName;
				File dest = new File(filePath);
				fileUpload.transferTo(dest);

				// MenuFile menuFile=new MenuFile();
				// menuFile.setStoreId(userInfo.getStoreId());
				// menuFile.setFileName(fileUpload.getOriginalFilename());

				commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
				commonResultSetMapper.setStoreId(userInfo.getStoreId());
				commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
				commonResultSetMapper.setCreatedBy(userInfo.getId());

				FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
				filePart.setContentDisposition(
						FormDataContentDisposition.name("file").fileName(fileUpload.getOriginalFilename()).build());
				MultiPart multipartEntity = new FormDataMultiPart().field("commonResultSetMapper",
						new Gson().toJson(commonResultSetMapper), MediaType.APPLICATION_JSON_TYPE).bodyPart(filePart);

				String res = stockService.uploadstockexcel(inputFile, multipartEntity, commonResultSetMapper);
				//System.out.println("result:" + res);
				mav.addObject("msg", res);
				// inputFile.close();
			} catch (Exception e) {
				logger.error("File uploading problem: ", e);
			}
		} else {
			result.reject("NotEmpty.menuitemupload.file");
		}
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ADJ);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_STOCKENTRY_PAGE);
		return mav;
	}

	@RequestMapping(value = "/expissuemanual", method = RequestMethod.GET)
	public ModelAndView loadManualExpIssue(Model model, HttpSession session) {
		//logger.info("In manual expissue......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE_MANUAL);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//System.out.println("res:" + allVendors.toString());
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("allVendors", allVendors);
		mav.addObject("menuselect", menuselect);
		mav.addObject("expiryId", 0);
		mav.addObject("selecteddistributor", 0);
		mav.setViewName(ForwardConstants.VIEW_MANUAL_STOCKEXPISSUE_PAGE);
		return mav;
	}

	/// STOCK TRANSFER MODULE
	/// Author:Sayantan Mahanty
	/// Date:26.11.19

	@RequestMapping(value = "/stocktransfer", method = RequestMethod.GET)
	public ModelAndView loadStockTransfer(Model model, HttpSession session) {
		//logger.info("In stocktransfer......");

		ModelAndView mav = new ModelAndView();
		SaleReturnDTO saleReturnDTO = new SaleReturnDTO();
		SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
		List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		/// MenuByUserDTO menuByUserDTO =
		/// roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
		/// userInfo.getId(),userInfo.getProductTypeId(),
		/// RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_TRANSFER);
		mav.addObject("menuselect", menuselect);
		mav.addObject("isOptical", userInfo.getIsOptical());
		mav.addObject("menuselect", menuselect);	
		mav.addObject("poDefaultPrefix", Constant.STOCK_TRANSFER_QS);
		mav.addObject("userinfo", userInfo);
		mav.addObject("noOfDue", CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("setprinter",
				Integer.valueOf(CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALE_SETPRINTER)));
		/*
		 * for sale
		 */

		/// mav.addObject("defaulSalesManPer",CommonResource.getProperty(CommonResource.DEFAULT_SALESMAN_PERCENTAGE));
		/// mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.STOCK_TRANSFER_PAGE);
		return mav;
	}

	@RequestMapping(value = "/createOrUpdateStockTransfer", method = RequestMethod.POST)
	public void createOrUpdateStockTransfer(@RequestBody String StockTransferObj, HttpSession session,
			HttpServletResponse response) throws IOException {

		//logger.info("In StockTransfer......{}", StockTransferObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			StockTransfer stkTransfer = gson.fromJson(StockTransferObj, new TypeToken<StockTransfer>() {
			}.getType());
			/*
			 * customerMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			 * customerMasterObject.setCompanyId(userInfo.getCompanyId());
			 * customerMasterObject.setStoreId(userInfo.getStoreId());
			 * customerMasterObject.setFinyrId(userInfo.getFinyrId());
			 * customerMasterObject.setCreatedBy(userInfo.getId());
			 * customerMasterObject.setLang(lang);
			 */
			String res = stockService.createOrUpdateStockTransferService(stkTransfer);
		//	System.err.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/stocktransferreg", method = RequestMethod.GET)
	public ModelAndView stockTransferReg(Model model, HttpSession session) {
		//logger.info("In stocktransferreg......");

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
		/// MenuByUserDTO menuByUserDTO =
		/// roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
		/// userInfo.getId(),userInfo.getProductTypeId(),
		/// RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_TRANSFER_REG);
		mav.addObject("menuselect", menuselect);
		/// mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.STOCK_TRANSFER__REG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchstocksransferregister", method = RequestMethod.POST)
	public ModelAndView getAllStockTransferDetails(
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
		commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
		commonResultSetMapper.setInvoiceNo(Constant.STOCK_TRANSFER_QS + "/" + commonResultSetMapper.getFinyrCode() + "/"
				+ commonResultSetMapper.getInvoiceNo());
		//System.err.println( commonResultSetMapper.getFinyrCode());
		//logger.info("In searchstocktransferregister......{}", commonResultSetMapper.toString());
		List<StockTransferDTO> stockTransferDTO = stockService.getAllStockTransfer(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("stockTransferDTO", stockTransferDTO);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_TRANSFER_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno);		
		mav.addObject("poDefaultPrefix", Constant.STOCK_TRANSFER_QS);
		mav.setViewName(ForwardConstants.STOCK_TRANSFER__REG_PAGE);
		return mav;
	}

	

	@RequestMapping(value = "/stocktransferheader/{pid}", method = RequestMethod.GET)
	public ModelAndView getStockTransferHeaderById(@PathVariable("pid") Integer pid, Model model, HttpSession session) {
		//logger.info("In StockTransferHeader......{},", pid);
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
		commonResultSetMapper.setTransferId(pid);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
		commonResultSetMapper.setLang(lang);
		StockTransferDTO stockTransferHeader = stockService.getStockTransferHeader(commonResultSetMapper);
		
		List<StockTransferDetailsDTO> stockTransferDetails = stockService
				.getStockTransferDetails(commonResultSetMapper);

		//System.err.println("___________stockTransferDetails______________" + stockTransferDetails.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("stockTransferHeader", stockTransferHeader);
		mav.addObject("stockTransferDetails", stockTransferDetails);
		mav.addObject("poDefaultPrefix", Constant.STOCK_TRANSFER_QS);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_TRANSFER);
		mav.addObject("menuselect", menuselect);
		mav.addObject("gettransId", stockTransferHeader.getTransId());
		
		mav.setViewName(ForwardConstants.STOCK_TRANSFER_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/deleteStockTransfer", method = RequestMethod.POST)
	public void deleteStockTransfer(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {		
		//logger.info("In deleteStock Trans......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
			commonResultSetMapper.setLang(lang);
			String res = stockService.deleteStockTransfer(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/dispatchStockTransfer",method = RequestMethod.POST)
	public void dispatchStockTransfer(	@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException {
		//logger.info("In dispatchStockTransfer......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
			commonResultSetMapper.setLang(lang);
			String res = stockService.doConfirmDispatchStockTransfer(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	
	@RequestMapping(value = "/stockreceive", method = RequestMethod.GET)
	public ModelAndView stockReceive(Model model, HttpSession session) {
		//logger.info("In stockreceive......");

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
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_RECEIVE);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.STOCK_RECEIVE_PAGE);
		return mav;
	}

	@RequestMapping(value = "/stockreceivereg", method = RequestMethod.GET)
	public ModelAndView stockReceiveReg(Model model, HttpSession session) {
		//logger.info("In stockreceivereg......");

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
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_RECEIVE_REG);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.STOCK_RECEIVE_REG_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/searchstockreceiveregister", method = RequestMethod.POST)
	public ModelAndView getAllStockTransferReceiveDetails(
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
		commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
		commonResultSetMapper.setInvoiceNo(Constant.STOCK_TRANSFER_QS + "/" + commonResultSetMapper.getFinyrCode() + "/"
				+ commonResultSetMapper.getInvoiceNo());
		
		//logger.info("In searchstockreceiveregister......{}", commonResultSetMapper.toString());
		List<StockTransferDTO> stockTransferDTO = stockService.getAllStockTransferReceive(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.STOCK_202, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("stockTransferDTO", stockTransferDTO);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_RECEIVE_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno);		
		mav.addObject("poDefaultPrefix", Constant.STOCK_TRANSFER_QS);
		mav.setViewName(ForwardConstants.STOCK_RECEIVE_REG_PAGE);
		return mav;
	}
	
	
	@RequestMapping(value = "/stockreceiveheader/{pid}", method = RequestMethod.GET)
	public ModelAndView getStockReceiveHeaderById(@PathVariable("pid") Integer pid, Model model, HttpSession session) {
		//logger.info("In StockReceiveHeader......{},", pid);
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
		commonResultSetMapper.setTransferId(pid);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
		commonResultSetMapper.setLang(lang);
		StockTransferDTO stockTransferHeader = stockService.getStockTransferHeader(commonResultSetMapper);		
		List<StockTransferDetailsDTO> stockTransferDetails = stockService
				.getStockTransferDetails(commonResultSetMapper);

		//System.err.println("___________StockReceiveDetails______________" + stockTransferDetails.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("stockTransferHeader", stockTransferHeader);
		mav.addObject("stockTransferDetails", stockTransferDetails);

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_RECEIVE);
		mav.addObject("menuselect", menuselect);
		mav.addObject("gettransId", stockTransferHeader.getTransId());
		mav.addObject("QS", Constant.STOCK_TRANSFER_QS);
		mav.setViewName(ForwardConstants.STOCK_RECEIVE_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/createOrUpdateStockTransferReceive", method = RequestMethod.POST)
	public void createOrUpdateStockTransferReceive(@RequestBody String StockTransferObj, HttpSession session,
			HttpServletResponse response) throws IOException {

		//logger.info("In StockReceive......{}", StockTransferObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			StockTransfer stkTransfer = gson.fromJson(StockTransferObj, new TypeToken<StockTransfer>() {
			}.getType());
			String res = stockService.createOrUpdateStockTransferReceive(stkTransfer);
			//System.err.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/closeStockTransferReceive",method = RequestMethod.POST)
	public void closeStockTransferReceive(	@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException {
		//logger.info("In closeStockTransfer......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
			commonResultSetMapper.setLang(lang);
			String res = stockService.closeStockTransferReceiveService(commonResultSetMapper);
			//System.err.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/restocktransitqty", method = RequestMethod.POST)
	public void reStockTransitQty(@RequestBody String StockTransferDetailsObj, HttpSession session,
			HttpServletResponse response) throws IOException {

		//logger.info("In reStockTransitQty......{}", StockTransferDetailsObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			StockTransferDetails stkTransferdtl = gson.fromJson(StockTransferDetailsObj, new TypeToken<StockTransferDetails>() {
			}.getType());
			String res = stockService.reStockTransitQtyService(stkTransferdtl);
			//System.err.println("res:" + res);
			out.print(res);
			out.flush();
		}
		
	}
	
	@RequestMapping(value = "/closeStockTransferSend",method = RequestMethod.POST)
	public void closeStockTransferSend(	@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException {
		//logger.info("In closeStockTransfer......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
			commonResultSetMapper.setLang(lang);
			String res = stockService.closeStockTransferSend(commonResultSetMapper);
			//System.err.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	

}
