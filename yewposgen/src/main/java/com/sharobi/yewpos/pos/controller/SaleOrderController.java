package com.sharobi.yewpos.pos.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.model.CustomerType;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.RetailTypeControlDTO;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.TaxTypeDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.GenderMaster;
import com.sharobi.yewpos.pos.model.SaleOrder;
import com.sharobi.yewpos.pos.model.SaleOrderDTO;
import com.sharobi.yewpos.pos.model.SaleOrderDetailsDTO;
import com.sharobi.yewpos.pos.model.TypeMaster;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.CustomerService;
import com.sharobi.yewpos.pos.service.SaleOrderService;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

@Controller
@RequestMapping("/saleorder")
public class SaleOrderController {
	private final static Logger logger = LoggerFactory.getLogger(SaleOrderController.class);
	@Autowired
	CashMemoService cashMemoService;
	@Autowired
	 RoleService roleService ;
	@Autowired
	 VendorService vendorService ;
	@Autowired
     InvSetupService invSetUpService;
	@Autowired
	 SaleOrderService saleOrderService ;
	  @Autowired
      ItemService itemService;
	  @Autowired
	  CustomerService customerService;
	  
	  Gson gson = new Gson();

	@RequestMapping(value = "/loadsaleorder",
			method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(	Model model,
									HttpSession session) {
		//logger.info("In loadsaleorder......");
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
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		CommonResultSetMapper commonResultSetMapperMenu = new CommonResultSetMapper();
		commonResultSetMapperMenu.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperMenu.setStoreId(userInfo.getStoreId());
		commonResultSetMapperMenu.setRetailTypeID(userInfo.getRetailTypeId());
		commonResultSetMapperMenu.setMenuID(RoleBasedCostants.ITEM_SETUP_201J);
		commonResultSetMapperMenu.setLang(lang);
		List<RetailTypeControlDTO> retailTypeControlDTOs=gson.fromJson(invSetUpService.getAllSelectedMenuByRetailType(commonResultSetMapperMenu), new TypeToken<List<RetailTypeControlDTO>>() {}.getType());
		List<CategoryMaster> allCat=invSetUpService.getAllCat(userInfo.getCompanyId(),lang);
		List<TypeMaster> typeMasters = cashMemoService.getTypes(commonResultSetMapper);
		List<GenderMaster> genderlist = cashMemoService.getGenderList(commonResultSetMapper);
		String countrylist = invSetUpService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);
		List<CustomerType> customerTypes = gson.fromJson(customerService.getAllCustomerType(commonResultSetMapper), new TypeToken<List<CustomerType>>() {}.getType());
		/* add on 7_11_2017*/
		List<TaxTypeDTO> alltaxtypeexclusive =itemService.getalltaxtype_is_exclusive(commonResultSetMapperMenu);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("alltaxtypelist", alltaxtypeexclusive);
		/*mav.addObject("sizeVariantDTOs", sizeVariantDTOs);
		mav.addObject("colorVariantDTOs", colorVariantDTOs);
		mav.addObject("locationDTOs", locationDTOs);
		mav.addObject("allReturnReasonTypes",allReturnReasonType);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);*/
		mav.addObject("allCat", allCat);


		mav.addObject("noOfDue",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("wholesaleProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("isWholesale",userInfo.getIsWholesale());
		mav.addObject("isOptical",userInfo.getIsOptical());

		mav.addObject("purOrderQtyFromSale",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE));
		mav.addObject("saleHistoryDay",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_SALE_HISTORY_DAY));
		mav.addObject("dayToPurchase",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_DAY_TO_PURCHASE));
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.SALE_ORDER);
		mav.addObject("typeMasters", typeMasters);
		mav.addObject("menuselect", menuselect);
		mav.addObject("contrylist", countryMasters);
		mav.addObject("customerTypes", customerTypes);
		mav.addObject("genderlist", genderlist);
		mav.addObject("userinformation", userInfo);/*Sayantan Mahanty added date-2/03/2020*/
		mav.setViewName(ForwardConstants.VIEW_SALEORDER_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/createorupdatesaleorder",
			method = RequestMethod.POST)
	public void createPurchaseOrder(	@RequestBody String saleString,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.info("In createorupdatesaleorder......{},{},{}", saleString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			SaleOrder saleOrder = gson.fromJson(saleString, new TypeToken<SaleOrder>() {}.getType());
			saleOrder.setCompanyId(userInfo.getCompanyId());
			saleOrder.setStoreId(userInfo.getStoreId());
			saleOrder.setCreatedBy(userInfo.getId());
			saleOrder.setFinyrId(userInfo.getFinyrId());
			saleOrder.setFinyrCode(userInfo.getFinyrCode());
			saleOrder.setLang(lang);
			//System.out.println("_________SALE ORDER DETAILS_______"+gson.toJson(saleOrder));
			String res = saleOrderService.createOrUpdateSaleOrder(saleOrder);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/loadsaleordregister",
			method = RequestMethod.GET)
	public ModelAndView loadSaleInvRegister(	Model model,
											HttpSession session) {
		//logger.info("In loadsaleordregister......");
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
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		//mav.addObject("allVendors", allVendors);
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>)session.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for(CommonResultSetMapper ob: commonResultSetMappermulserret ){
			if(ob.getQs().equals(Constant.SALE_ORDER_QS) && ob.getIsDefault() == 1){
				mav.addObject("soDefaultPrefix", ob.getMulSeriesPrefix());
			}else{
				mav.addObject("soDefaultPrefix", Constant.SALE_ORDER_PREFIX);
			}
		}
		
		
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.SALE_ORDER_REG);
		mav.addObject("menuselect", menuselect);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.setViewName(ForwardConstants.VIEW_SALEORDREG_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/searchsaleorderregister",
			method = RequestMethod.POST)
	public ModelAndView searchPORegister(	@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
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
		String invno = commonResultSetMapper.getInvoiceNo();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.PURCHASE_ORDER + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		//logger.info("In searchsaleorderregister......{}", commonResultSetMapper.toString());
		List<SaleOrderDTO> saleOrderList = saleOrderService.getAllSaleOrder(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("saleOrderList", saleOrderList);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.SALE_ORDER_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		List<CommonResultSetMapper> commonResultSetMappermulserret = (List<CommonResultSetMapper>)session.getAttribute(Constant.SES_BILL_PREFIX_LIST);
		for(CommonResultSetMapper ob: commonResultSetMappermulserret ){
			if(ob.getQs().equals(Constant.SALE_ORDER_QS) && ob.getIsDefault() == 1){
				mav.addObject("soDefaultPrefix", ob.getMulSeriesPrefix());
			}else{
				mav.addObject("soDefaultPrefix", Constant.SALE_ORDER_PREFIX);
			}
		}
		
		mav.setViewName(ForwardConstants.VIEW_SALEORDREG_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/loadsaleorderdet/{pid}",
			method = RequestMethod.GET)
	public ModelAndView loadSaleOrderDet(	@PathVariable("pid") Integer pid,
									Model model,
									HttpSession session) {
		//logger.info("In loadsaleorderdet......{},", pid);
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
		SaleOrderDTO saleOrderHeader = saleOrderService.getSaleOrderHeader(commonResultSetMapper);
		saleOrderHeader.setCompanyId(userInfo.getCompanyId());
		saleOrderHeader.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setCustId(saleOrderHeader.getCustomerId());
		
		//DistributorMaster vendorDetails = gson.fromJson(vendorService.getVendorDet(commonResultSetMapper), new TypeToken<DistributorMaster>() {}.getType());
		
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<SaleOrderDetailsDTO> saleOrderDetails = saleOrderService.getSaleOrderDetails(commonResultSetMapper);

		//System.err.println("___________saleOrderDetails______________"+saleOrderDetails.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		
		mav.addObject("purchaseOrderHeader", saleOrderHeader);
		mav.addObject("purchaseOrderDetails", saleOrderDetails);
		
		mav.addObject("allRacks",allRacks);
		mav.addObject("allGroups",allGroups);
		mav.addObject("allSchedules",allSchedules);
		//mav.addObject("distEmail",vendorDetails.getEmail());
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("purOrderQtyFromSale",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE));
		mav.addObject("saleHistoryDay",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_SALE_HISTORY_DAY));
		mav.addObject("dayToPurchase",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_DAY_TO_PURCHASE));

		mav.addObject("wholesaleProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.addObject("isWholesale",userInfo.getIsWholesale());
		mav.addObject("isOptical",userInfo.getIsOptical());
		
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.SALE_ORDER);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALEORDER_DETAILS_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/postsaleorder",
			method = RequestMethod.POST)
	public void postSaleOrder(	@RequestBody String commonResultsetMapperObj,
						HttpSession session,
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = saleOrderService.postSaleOrder(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/deletesaleorder",
			method = RequestMethod.POST)
	public void deleteSaleOrder(	@RequestBody String commonResultsetMapperObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		//logger.info("In deleteSaleOrder......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setLang(lang);
			String res = saleOrderService.deleteSaleOrder(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/closesaleorder",
			method = RequestMethod.POST)
	public void closePurOrder(	@RequestBody String commonResultsetMapperObj,
							HttpSession session,
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = saleOrderService.closeSaleOrder(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/getsaleorderdetbyordernoforpi",
			method = RequestMethod.POST)
	public void getSaleOrderDetByOrderNoforPI(@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.info("In getSaleOrderDetByOrderNoforPI......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setLang(lang);
			String res = saleOrderService.getSaleOrderDetailsByOrderNoForPI(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/uploadprescription",method=RequestMethod.POST)
	public @ResponseBody String uploadStock(@RequestParam MultipartFile fileUpload, @RequestParam String saleOrderId ,HttpSession session,HttpServletResponse response,HttpServletRequest request)
	{
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		//logger.debug("uploadprescription...! ");
		ModelAndView mav=new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		InputStream inputFile = null;
		byte[] byteArr=null;
		String genName = null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			//return mav;
			//System.out.println("Please login first");
		}
		if (fileUpload != null && fileUpload.getSize() > 0) {
			try{
				//System.out.println("file name:"+fileUpload.getName());
				//System.out.println("original file name:"+fileUpload.getOriginalFilename());
				//System.out.println("file size:"+fileUpload.getSize());
				inputFile=fileUpload.getInputStream();
				byteArr=fileUpload.getBytes();
				//System.out.println("inputsterm:"+inputFile);
				//System.out.println("byte arr:"+byteArr);
				String uploadsDir = "/resources/uploads/";
                String realPathtoUploads =  request.getSession().getServletContext().getRealPath(uploadsDir);
                //System.out.println("path = "+realPathtoUploads);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }

				//System.out.println("path:"+realPathtoUploads);
				String orgName = fileUpload.getOriginalFilename();
				String ext = orgName.substring(orgName.lastIndexOf('.'));
				//genName = UUID.randomUUID().toString()+ext;
				
				genName = saleOrderId+"-"+userInfo.getCompanyId()+"-"+userInfo.getStoreId()+ext;
                String filePath = realPathtoUploads +"/"+ genName;
                File dest = new File(filePath);
                fileUpload.transferTo(dest);

				
			}catch (Exception e) {
				logger.error("File uploading problem: ", e);
				//System.out.println("File upload unsuccessful");
			}
			
		}
		else
		{
			System.out.println("File uploaded successfully "+genName);
		}
		
		return genName;
	}
	
	@RequestMapping(value = "/getsodetailsbyno/{soDoc}/{soFinyr}/{sono}",
			method = RequestMethod.GET)
	public void getSoDetails(	@PathVariable String soDoc,
								@PathVariable String soFinyr,
								@PathVariable String sono,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.info("In getsodetailsbyno......{}{}{}", soDoc + soFinyr + sono);
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
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setInvoiceNo(soDoc+'/'+soFinyr+'/'+sono);
			String res = saleOrderService.getSaleOrderDetailsByInvoiceNo(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
}
