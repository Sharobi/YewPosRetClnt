/**
 *
 */
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.model.CustomerType;
import com.sharobi.yewpos.inv.model.LocationDTO;
import com.sharobi.yewpos.inv.model.SalesmanDTO;
import com.sharobi.yewpos.inv.model.VariantDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.inv.service.SalesmanService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.EsiCodeMaster;
import com.sharobi.yewpos.pos.model.GenderMaster;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.pos.model.Sales;
import com.sharobi.yewpos.pos.model.TypeMaster;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.CustomerService;
import com.sharobi.yewpos.pos.service.ReprintCashMemoService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ReadingPorts;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author habib,Manodip
 *
 */
@Controller
@RequestMapping("/pos")
public class PosController {
	private final static Logger logger = LoggerFactory.getLogger(PosController.class);
	// ReadingPorts display = new ReadingPorts();
	@Autowired
	CashMemoService cashMemoService;
	@Autowired
	RoleService roleService;
	@Autowired
	ReprintCashMemoService reprintCashMemoService;
	@Autowired
	InvSetupService invSetUpService;
	@Autowired
	ItemService itemService;
	@Autowired
	CustomerService customerService;

	@Autowired
	SalesmanService salesmanService;

	@Autowired
	StoreMgntService storeService;

	Gson gson = new Gson();

	/**
	 * @author SK A SIDDIK
	 *         <h5>Description</h5> : for sale invovice url =/cashmemo ModelAndView
	 * @param model
	 * @param session
	 * @param saleId  for edit
	 * @return
	 */
	@RequestMapping(value = "/cashmemo", method = RequestMethod.GET)
	public ModelAndView cashMemo(Model model, HttpSession session,
			@RequestParam(required = false, defaultValue = "0") String saleId) {
		//logger.info("In cashmemo......{}", saleId);
		if (!saleId.equals("0")) {
			saleId = (String.valueOf(saleId).split("-")[2]);
			//System.out.println("new saleId=" + saleId);
		}
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
		if (!saleId.equals("0")) {
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			saleHeaderDTO = gson.fromJson(cashMemoService.getSalesHeader(commonResultSetMapper),
					new TypeToken<SaleHeaderDTO>() {
					}.getType());

			//System.err.println("__________saleHeaderDTO_________" + saleHeaderDTO.toString());
			saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetails(commonResultSetMapper),
					new TypeToken<List<SaleDetailsDTO>>() {
					}.getType());

			//System.err.println("__________saleDetailsDTOs_________" + saleDetailsDTOs.toString());

			// saleReturnDTO.setIsPosted(1);
		}
		StoreMaster store = storeService.getStoreDetailsById(userInfo.getStoreId());
		/// System.err.println("userInfo.getIsWholesale "+userInfo.getIsWholesale());

		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		List<TypeMaster> typeMasters = cashMemoService.getTypes(commonResultSetMapper);
		List<EsiCodeMaster> esiCodeMasters = cashMemoService.getEsiCode(commonResultSetMapper);
		List<GenderMaster> genderlist = cashMemoService.getGenderList(commonResultSetMapper);

		CommonResultSetMapper commonResultSetMapperCustType = new CommonResultSetMapper();
		commonResultSetMapperCustType.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperCustType.setStoreId(userInfo.getStoreId());
		commonResultSetMapperCustType.setFinYrId(userInfo.getFinyrId());
		List<CustomerType> customerTypes = gson.fromJson(
				customerService.getAllCustomerType(commonResultSetMapperCustType), new TypeToken<List<CustomerType>>() {
				}.getType());
		/*
		 * MenuByUserDTO menuByUserDTO =
		 * roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(),
		 * userInfo.getId(), userInfo.getProductTypeId(),
		 * RoleBasedCostants.CASH_MEMO_101, lang);
		 */
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.CASH_MEMO_101, lang);
		//System.out.println("______cashmemo___permission___" + menuByUserDTO.toString());

		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleId", saleId);
		mav.addObject("saleRetId", 0);// same page sale ret
		mav.addObject("customerTypes", customerTypes);
		/*
		 * CommonResultSetMapper commonResultSetMappermulser = new
		 * CommonResultSetMapper();
		 * commonResultSetMappermulser.setCompanyId(userInfo.getCompanyId());
		 * commonResultSetMappermulser.setStoreId(userInfo.getStoreId());
		 * commonResultSetMappermulser.setFinYrId(userInfo.getFinyrId());
		 * commonResultSetMappermulser.setQs(menuByUserDTO.getQs());
		 */
//		String res = cashMemoService.getInvoicePrefixByqs(commonResultSetMappermulser);
		/*
		 * List<CommonResultSetMapper> commonResultSetMappermulserret =
		 * gson.fromJson(cashMemoService.getInvoicePrefixByqs(
		 * commonResultSetMappermulser),new TypeToken<List<CommonResultSetMapper>>()
		 * {}.getType());
		 */
		List<CommonResultSetMapper> commonResultSetMappermulSalPrefix = new ArrayList<CommonResultSetMapper>();
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		
		
		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.SALE_RETURN_PAYMENT_QS) && ob.getIsDefault() == 1) {
				mav.addObject("retDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("retDefaultPrefix", Constant.SALES_INVOICE_RETURN_MEMO);
			}

			if (ob.getQs().equals(Constant.SALE_ORDER_QS) && ob.getIsDefault() == 1) {
				mav.addObject("soDefaultPrefix", ob.getMulSeriesPrefix());
			} else {
				mav.addObject("soDefaultPrefix", Constant.SALE_ORDER_PREFIX);
			}

			if (ob.getQs().equals(Constant.SALE_QS)) {
				commonResultSetMappermulSalPrefix.add(ob);
			}
		}

		if (commonResultSetMappermulSalPrefix.isEmpty()) {
			CommonResultSetMapper ob = new CommonResultSetMapper();
			ob.setMulSeriesPrefix(Constant.SALES_INVOICE);
			commonResultSetMappermulSalPrefix.add(ob);
		}
		mav.addObject("serialnumberlist", commonResultSetMappermulSalPrefix);

		// for series list
		/* mav.addObject("serialnumberlist", commonResultSetMappermulserret); */
		/* session.setAttribute("newseriesnumber", commonResultSetMappermulserret); */
		session.setAttribute("newseriesnumber", commonResultSetMappermulSalPrefix);

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

		String countrylist = invSetUpService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);

		// all location
		CommonResultSetMapper commonResultSetMapperLoc = new CommonResultSetMapper();
		commonResultSetMapperLoc.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperLoc.setStoreId(userInfo.getStoreId());
//		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapperLoc.setLang(lang);
		List<LocationDTO> locationDTOs = gson.fromJson(itemService.getAllLocation(commonResultSetMapperLoc),
				new TypeToken<List<LocationDTO>>() {
				}.getType());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.CASH_MEMO);

		mav.addObject("isWholesale", userInfo.getIsWholesale());
		mav.addObject("isOptical", userInfo.getIsOptical());
		mav.addObject("menuselect", menuselect);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("saleReturnDTO", saleReturnDTO);
		mav.addObject("typeMasters", typeMasters);
		mav.addObject("esiCodeMasters", esiCodeMasters);
		mav.addObject("genderlist", genderlist);
		mav.addObject("sizeVariantDTOs", sizeVariantDTOs);
		mav.addObject("colorVariantDTOs", colorVariantDTOs);
		mav.addObject("locationDTOs", locationDTOs);
		mav.addObject("userinfo", userInfo);
		mav.addObject("isTRN", store.getCurrencyId());
		mav.addObject("contrylist", countryMasters);
		/// mav.addObject("getIsOnBillPurchase",userInfo.getIsOnBillPurchase());
		mav.addObject("setprinter",
				Integer.valueOf(CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALE_SETPRINTER)));
		/*
		 * for sale
		 */
		mav.addObject("defaulSalesManPer", CommonResource.getProperty(CommonResource.DEFAULT_SALESMAN_PERCENTAGE));

		if (userInfo.getIsNewDisplay() == 1) {
			mav.setViewName(ForwardConstants.VIEW_POS_NUMERICKEY_PAGE);
		} else {
			mav.setViewName(ForwardConstants.VIEW_POS_PAGE);
		}
		return mav;
	}

	@RequestMapping(value = "/createorupdatesalesinvoice", method = RequestMethod.POST)
	public void createOrUpdateSalesInv(@RequestBody String salesString, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In createOrUpdateSalesInv......{},{},{}", salesString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Sales sales = gson.fromJson(salesString, new TypeToken<Sales>() {
			}.getType());
			sales.setCompanyId(userInfo.getCompanyId());
			sales.setStoreId(userInfo.getStoreId());
			sales.setCreatedBy(userInfo.getId());
			sales.setFinyrId(userInfo.getFinyrId());
			sales.setFinyrCode(userInfo.getFinyrCode());
			sales.setLang(lang);
			sales.setQs(Constant.SALE_QS);
			sales.setIs_account(userInfo.getIs_account());

			//System.err.println("____________createorupdatesalesinvoice______________" + sales.toString());
			//System.out.println("__________ SALE DETAILS________" + gson.toJson(sales));
			String res = cashMemoService.createOrUpdateSalesInvoice(sales);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/modifycashmemo", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modifyCashmemo(
			@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper, Model model,
			HttpSession session) {
		//logger.info("In modifycashmemo......{}" + commonResultSetMapper.toString());
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

		// for series list
		/*
		 * CommonResultSetMapper commonResultSetMappermulser = new
		 * CommonResultSetMapper();
		 * commonResultSetMappermulser.setCompanyId(userInfo.getCompanyId());
		 * commonResultSetMappermulser.setStoreId(userInfo.getStoreId());
		 * commonResultSetMappermulser.setFinYrId(userInfo.getFinyrId());
		 * commonResultSetMappermulser.setQs(Constant.SALE_QS);
		 */
		// List<CommonResultSetMapper> commonResultSetMappermulserret =
		// gson.fromJson(cashMemoService.getInvoicePrefixByqs(commonResultSetMappermulser),new
		// TypeToken<List<CommonResultSetMapper>>() {}.getType());

		List<CommonResultSetMapper> commonResultSetMappermulSalPrefix = new ArrayList<CommonResultSetMapper>();
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.SALE_QS) && ob.getIsDefault() == 1) {
				if (commonResultSetMapper.getMulSeriesPrefix() == null) {
					commonResultSetMapper.setMulSeriesPrefix(ob.getMulSeriesPrefix());
				}
			} else {
				if (commonResultSetMapper.getMulSeriesPrefix() == null) {
					commonResultSetMapper.setMulSeriesPrefix(Constant.SALES_INVOICE);
				}
			}

			if (ob.getQs().equals(Constant.SALE_QS)) {
				commonResultSetMappermulSalPrefix.add(ob);
			}
		}
		if (commonResultSetMappermulSalPrefix.isEmpty()) {
			CommonResultSetMapper ob = new CommonResultSetMapper();
			ob.setMulSeriesPrefix(Constant.SALES_INVOICE);
			commonResultSetMappermulSalPrefix.add(ob);
		}
		mav.addObject("serialnumberlist", commonResultSetMappermulSalPrefix);

		if (commonResultSetMapper.getFinyrCode() == null) {
			commonResultSetMapper.setFinyrCode(userInfo.getFinyrCode());
		}
		if (commonResultSetMapper.getInvoiceNo() == null || commonResultSetMapper.getInvoiceNo() == "") {
			commonResultSetMapper.setInvoiceNo("");
		}
		// for date
		CommonResultSetMapper commonResultSetMapperdate = new CommonResultSetMapper();
		if (commonResultSetMapper.getMulSeriesPrefix() == null) {
			commonResultSetMapperdate.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
			commonResultSetMapperdate.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
			commonResultSetMapper.setMulSeriesPrefix("");
			mav.addObject("commonResultSetMapper", commonResultSetMapperdate);
		} else {
			mav.addObject("commonResultSetMapper", commonResultSetMapper);
		}
		//System.out.println("commonResultSetMapper.getMulSeriesPrefix()=" + commonResultSetMapper.getMulSeriesPrefix());
		String invno = commonResultSetMapper.getInvoiceNo();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		// commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" +
		// commonResultSetMapper.getFinyrCode() + "/" +
		// commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setInvoiceNo(commonResultSetMapper.getMulSeriesPrefix() + "/"
				+ commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		if (commonResultSetMapper.getStartDate() == null && commonResultSetMapper.getEndDate() == null) {
			commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
			commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		}

		//logger.info("In searchsalesinvoice......{},{},{}", commonResultSetMapper.toString());
		List<SaleDetailsAllDTO> saleDetailsAllDTOs = cashMemoService.getAllSalesInvoice(commonResultSetMapper);
		// MenuByUserDTO menuByUserDTO =
		// roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(),
		// userInfo.getId(), userInfo.getProductTypeId(),
		// RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		//System.out.println("______modifycashmemo___permission___" + menuByUserDTO.toString());

		//System.out.println(menuByUserDTO.toString());
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs);

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.MC_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno);
		mav.setViewName(ForwardConstants.VIEW_MODIFYCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/reprintcashmemo", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView reprintCashmemo(
			@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper, Model model,
			HttpSession session) {
		//logger.info("In reprintcashmemo......");
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
		CommonResultSetMapper commonResultSetMapperdate = new CommonResultSetMapper();
		// for first page loading date
		if (commonResultSetMapper.getMulSeriesPrefix() == null) {
			commonResultSetMapperdate.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
			commonResultSetMapperdate.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
			mav.addObject("commonResultSetMapper", commonResultSetMapperdate);
		} else// for submit loading
		{
			mav.addObject("commonResultSetMapper", commonResultSetMapper);
		}
		// for series list
		/*
		 * CommonResultSetMapper commonResultSetMappermulser = new
		 * CommonResultSetMapper();
		 * commonResultSetMappermulser.setCompanyId(userInfo.getCompanyId());
		 * commonResultSetMappermulser.setStoreId(userInfo.getStoreId());
		 * commonResultSetMappermulser.setFinYrId(userInfo.getFinyrId());
		 * commonResultSetMappermulser.setQs(Constant.SALE_QS);
		 * List<CommonResultSetMapper> commonResultSetMappermulserret =
		 * gson.fromJson(cashMemoService.getInvoicePrefixByqs(
		 * commonResultSetMappermulser),new TypeToken<List<CommonResultSetMapper>>()
		 * {}.getType()); // for series list mav.addObject("serialnumberlist",
		 * commonResultSetMappermulserret);
		 */
		List<CommonResultSetMapper> commonResultSetMappermulSalPrefix = new ArrayList<CommonResultSetMapper>();
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>) session
				.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for (CommonResultSetMapper ob : commonResultSetMappermulserret) {
			if (ob.getQs().equals(Constant.SALE_QS)) {
				commonResultSetMappermulSalPrefix.add(ob);
			}

		}
		if (commonResultSetMappermulSalPrefix.isEmpty()) {
			CommonResultSetMapper ob = new CommonResultSetMapper();
			ob.setMulSeriesPrefix(Constant.SALES_INVOICE);
			commonResultSetMappermulSalPrefix.add(ob);
		}
		mav.addObject("serialnumberlist", commonResultSetMappermulSalPrefix);

		String mulSeriesPrefix = commonResultSetMapper.getMulSeriesPrefix();
		String invno = commonResultSetMapper.getInvoiceNo();
		String finYrCode = commonResultSetMapper.getFinyrCode();
		session.setAttribute("reprintSearchStrtDate", commonResultSetMapper.getStartDate());
		session.setAttribute("reprintSearchEndDate", commonResultSetMapper.getEndDate());
		session.setAttribute("reprintSearchInvNo", commonResultSetMapper.getInvoiceNo());
		session.setAttribute("reprintSearchFinyrCode", finYrCode);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		// commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + finYrCode +
		// "/" + commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setInvoiceNo(commonResultSetMapper.getMulSeriesPrefix() + "/" + finYrCode + "/"
				+ commonResultSetMapper.getInvoiceNo());
		//System.out.println("invoice no ::::::::::::" + commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.info("In searchsalesinvoice......{},{},{}", commonResultSetMapper.toString());
		List<SaleDetailsAllDTO> saleDetailsAllDTOs = reprintCashMemoService.getAllSalesInvoice(commonResultSetMapper);

		// MenuByUserDTO menuByUserDTO =
		// roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(),
		// userInfo.getId(),userInfo.getProductTypeId(),
		// RoleBasedCostants.REPRINT_RETRURN_MEMO_106, lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.REPRINT_RETRURN_MEMO_106, lang);
		// System.out.println(menuByUserDTO.toString());
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs);

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.REPRINT_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		commonResultSetMapper.setFinyrCode(finYrCode); // after result replaced with only financial year code
		commonResultSetMapper.setMulSeriesPrefix(mulSeriesPrefix);

		mav.setViewName(ForwardConstants.VIEW_REPRINTCASHMEMO_PAGE);
		return mav;
	}

	/*
	 * @RequestMapping(value = "/searchsalesinvoice",method = RequestMethod.POST)
	 * public ModelAndView searchPIRegister(@ModelAttribute("commonResultSetMapper")
	 * CommonResultSetMapper commonResultSetMapper, HttpSession
	 * session,HttpServletResponse response) throws IOException {
	 * 
	 * ModelAndView mav = new ModelAndView(); LoginInfoByUserDTO userInfo = null; if
	 * ((userInfo = (LoginInfoByUserDTO)
	 * session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
	 * mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE); return mav; } String
	 * lang = null; if ((lang = (String)
	 * session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) { lang =
	 * Constant.DEFAULT_LANG; }
	 * 
	 * 
	 * System.out.println("commonResultSetMapper.getMulSeriesPrefix()="+
	 * commonResultSetMapper.getMulSeriesPrefix()); String invno =
	 * commonResultSetMapper.getInvoiceNo();
	 * commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
	 * commonResultSetMapper.setStoreId(userInfo.getStoreId());
	 * commonResultSetMapper.setFinYrId(userInfo.getFinyrId()); //
	 * commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" +
	 * commonResultSetMapper.getFinyrCode() + "/" +
	 * commonResultSetMapper.getInvoiceNo());
	 * commonResultSetMapper.setInvoiceNo(commonResultSetMapper.getMulSeriesPrefix()
	 * + "/" + commonResultSetMapper.getFinyrCode() + "/" +
	 * commonResultSetMapper.getInvoiceNo());
	 * commonResultSetMapper.setStatus(Constant.ORDER_ALL);
	 * logger.info("In searchsalesinvoice......{},{},{}",
	 * commonResultSetMapper.toString()); List<SaleDetailsAllDTO> saleDetailsAllDTOs
	 * = cashMemoService.getAllSalesInvoice(commonResultSetMapper); MenuByUserDTO
	 * menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
	 * (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
	 * userInfo.getId(), userInfo.getProductTypeId(),
	 * RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
	 * mav.addObject("menuByUserDTO", menuByUserDTO);
	 * mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs); MenuSelection
	 * menuselect = new MenuSelection(); menuselect.setMenu(Constant.POS);
	 * menuselect.setSubMenu(Constant.MC_CASH_MEMO); mav.addObject("menuselect",
	 * menuselect); commonResultSetMapper.setInvoiceNo(invno); // after result
	 * replaced with only inv no
	 * mav.setViewName(ForwardConstants.VIEW_MODIFYCASHMEMO_PAGE); return mav; }
	 */

	@RequestMapping(value = "/deletesalesinv", method = RequestMethod.POST)
	public void deleteSalesInv(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In deleteSalesInv......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.deleteSalesInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postsalesinv", method = RequestMethod.POST)
	public void postSalesInv(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In postsalesinv......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.postSalesInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getsaleheaderdetbycustomerphno", method = RequestMethod.POST)
	public void getSaleHeaderByCustPhNo(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getsaleheaderdetbycustomerphno......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.getCustomerLastSalesHeader(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getsaledetbycustomerphno", method = RequestMethod.POST)
	public void getSaleDetByCustPhNo(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getsaledetbycustomerphno......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.getSaleDetails(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	/*
	 * not use this url
	 */
	@RequestMapping(value = "/reprintbacktosearch", method = RequestMethod.GET)
	public ModelAndView reprintbacktosearch(Model model, HttpSession session) throws IOException {

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
//		String newseriesnumber = (String) session.getAttribute("newseriesnumber");
//		System.out.println("newseriesnumber="+newseriesnumber);
		String invno = (String) session.getAttribute("reprintSearchInvNo");
		String finYrCode = (String) session.getAttribute("reprintSearchFinyrCode");
		commonResultSetMapper.setStartDate((String) session.getAttribute("reprintSearchStrtDate"));
		commonResultSetMapper.setEndDate((String) session.getAttribute("reprintSearchEndDate"));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + finYrCode + "/" + invno);
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.info("In reprintbacktosearch......{},{},{}", commonResultSetMapper.toString());
		List<SaleDetailsAllDTO> saleDetailsAllDTOs = reprintCashMemoService.getAllSalesInvoice(commonResultSetMapper);

		// MenuByUserDTO menuByUserDTO =
		// roleService.getRoleMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(),
		// userInfo.getId(), userInfo.getProductTypeId(),
		// RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs);
		session.setAttribute("reprintSearchStrtDate", commonResultSetMapper.getStartDate());
		session.setAttribute("reprintSearchEndDate", commonResultSetMapper.getEndDate());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.REPRINT_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		commonResultSetMapper.setFinyrCode(finYrCode); // after result replaced with only financial year code
		mav.setViewName(ForwardConstants.VIEW_REPRINTCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/salebillprint", method = RequestMethod.POST)
	public void saleBillPrint(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In saleBillPrint......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.saleBillPrint(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getplaceoftreatmentautocomplete", method = RequestMethod.GET)
	public void getplaceoftreatmentListAutocom(@RequestParam String tagName, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getplaceoftreatmentautocomplete......{}", tagName.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setRemarks(tagName.toString());
			// gson.fromJson(commonResultsetMapperObj, new
			// TypeToken<CommonResultSetMapper>() {}.getType());

			String res = cashMemoService.getPlaceOfTreatmentListAutocom(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/saleitemdetforret", method = RequestMethod.POST)
	public void saleItemDetforRet(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In saleItemDetforRet......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setNoOfDays(Integer
					.valueOf(CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEITEM_DETAILS_NOOFDAYS)));
			commonResultSetMapper.setLang(lang);
			String res = cashMemoService.getSaleItemDetailsforRet(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postallselectedesalesinvoice", method = RequestMethod.POST)
	public void postAllSelectedSalesInv(@RequestBody String salesString, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In postAllSelectedSalesInv......{},{},{}", salesString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Sales sales = gson.fromJson(salesString, new TypeToken<Sales>() {
			}.getType());
			sales.setCompanyId(userInfo.getCompanyId());
			sales.setStoreId(userInfo.getStoreId());
			sales.setCreatedBy(userInfo.getId());
			sales.setFinyrId(userInfo.getFinyrId());
			sales.setFinyrCode(userInfo.getFinyrCode());
			sales.setLang(lang);
			String res = cashMemoService.postAllSelSalesInvoice(sales);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getinvoiceprefixbyqs", method = RequestMethod.POST)
	public void getInvoicePrefixByqs(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getInvoicePrefixByqs......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.getInvoicePrefixByqs(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/updateInvoicePrefixbyQS", method = RequestMethod.POST)
	public void updateInvoicePrefixbyQS(@RequestBody String commonResultsetMapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In updateInvoicePrefixbyQS......{}", commonResultsetMapperObj.toString());
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

			String res = cashMemoService.updateInvoicePrefixbyQS(commonResultSetMapper);
			session.setAttribute("newseriesnumber", res);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/viewdisplayweight", method = RequestMethod.GET)
	public void showDisplayWeight(HttpSession session, HttpServletResponse response)
			throws IOException, InterruptedException {
		// DecimalFormat weightdf = new DecimalFormat("00.000");
		final StoreMaster store;
		store = (StoreMaster) session.getAttribute(Constant.SES_LOGGED_IN_STORE);
		if (store != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			// code goes here.
			// String weight="0.0";
			// System.out.println("store.getWmPort()=" + store.getWmPort());
			// if (!store.getWmPort().equals("-")) {

			// ReadingPorts.setDefaultPort(store.getWmPort());
			// ReadingPorts.setBaudRate(store.getWmBaudRate());
			ReadingPorts.setDefaultPort(store.getDefaultPort());
			ReadingPorts.setBaudRate(store.getBaudRate());
			// Call together

			out.print(String.valueOf(ReadingPorts.getInstance().getQty()));
			// Call together
			out.flush();
			// }
		}
	}

	/**
	 * load all salemane during sale
	 */

	@RequestMapping(value = "/loadallsaleman", method = RequestMethod.GET)
	public void Loadallsaleman(HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In Loadallsaleman......{}");
		// ModelAndView mav = new ModelAndView();
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		String res = "";
		LoginInfoByUserDTO userInfo = null;
		List<SalesmanDTO> salesmanMasters = new ArrayList<SalesmanDTO>();
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			salesmanMasters = salesmanService.getAllSalesmanmasterlist(commonResultSetMapper);
			//System.err.println(salesmanMasters.toString());
			res = gson.toJson(salesmanMasters);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
}
