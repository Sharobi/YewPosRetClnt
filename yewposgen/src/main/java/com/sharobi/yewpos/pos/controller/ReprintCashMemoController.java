package com.sharobi.yewpos.pos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.service.ManufacturerService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.SaleOrderDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleOrderHeaderDTO;
import com.sharobi.yewpos.pos.model.TaxDetailsSaleBillDTO;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.ReprintCashMemoService;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.PrintBill;
import com.sharobi.yewpos.storemgnt.model.PrintBillDetails;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;

@Controller
@RequestMapping("/reprintmemo")
public class ReprintCashMemoController {
	private final static Logger logger = LoggerFactory.getLogger(PosController.class);
	  @Autowired
	  ReprintCashMemoService reprintCashMemoService;
	  @Autowired
	  CashMemoService cashMemoService ;
	  @Autowired
	  RoleService roleService ;
	  @Autowired
	  ManufacturerService manufacturerService;
	  @Autowired
	  StoreMgntService storeService;
      Gson gson = new Gson();



	@RequestMapping(value = "/cashmemo",method = RequestMethod.GET)
	public ModelAndView cashMemo(Model model,HttpSession session,	@RequestParam(	required = false,defaultValue = "0") String saleId,@RequestParam(required = false) String reprint,@RequestParam(required = false) String backUrl) throws ParseException {
	//logger.info("In cashmemo......{},{}", saleId,backUrl);
	if (!saleId.equals("0")) {
		saleId = (String.valueOf(saleId).split("-")[2]);
		//System.out.println("new saleId=" + saleId);
	}
	int manuId=0;
	ModelAndView mav = new ModelAndView();
	SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
	List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
	List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
	String printCountRes = "";
	int isRePrint=0;
    int printCount=0;
	//ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
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
		String flag="";
		if(reprint.equals("Y"))
		{
			flag = "r";
			isRePrint=1;
			printCountRes = reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			//System.out.println("print count status: "+printCountRes);
			printCount=Integer.valueOf(printCountRes);
		}else{}

		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setIsRePrint(isRePrint);
		commonResultSetMapper.setPrintCount(printCount);
		commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
		commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
		//logger.info("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
		//System.out.println("ss="+commonResultSetMapper.getNoteLineOne());
		//commonResultSetMapper.setLang(lang);
		saleHeaderDTO =gson.fromJson( cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());

		//System.out.println("====########saleDetailHeader####===="+gson.toJson(saleHeaderDTO));
		//System.out.println("====########saleDetailsDTOs#####===="+gson.toJson(saleDetailsDTOs));
		//System.out.println("====########taxDetailsDTOs#####===="+gson.toJson(taxDetailsDTOs));
		/*for(SaleDetailsDTO saledetails : saleDetailsDTOs)
		{
			manuId=saledetails.getManufacturerId();
		}
		manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/
		/*if(userInfo.getCompanyId() == 18){
		SimpleDateFormat dt1 = new SimpleDateFormat("MMMM dd, yyyy");
		SimpleDateFormat newdt = new SimpleDateFormat("dd-MM-yyyy");
		String invDate= saleHeaderDTO.getInvDate();
		Date invdate = dt1.parse(invDate);
		saleHeaderDTO.setInvDate(newdt.format(invdate));
		}*/
		
		mav.addObject("saleId", saleId);
		mav.addObject("backUrl",backUrl);
		mav.addObject("reprint",reprint);
		mav.addObject("printCountRes",printCountRes);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("taxDetailsDTOs",taxDetailsDTOs);
		mav.addObject("isCur",store.getCurrencyId());
		//System.err.println(store.getCurrencyId());
	}
	StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
	
	PrintBill billPrintData = (PrintBill)session.getAttribute(Constant.SES_BILL_PRINT_DATA);
	List<PrintBillDetails> billPrintDetailsData = (List<PrintBillDetails>)session.getAttribute(Constant.SES_BILL_PRINT_DETAILS_DATA);
	
	for(PrintBillDetails ob:billPrintDetailsData){
		if(ob.getBillType().equalsIgnoreCase("SAL")){
			ForwardConstants.VIEW_PRINT_SALE_BILL_PAGE = ob.getPageUrl();
		}
	}
	if(userInfo.getIsWholesale()!=0)
	{ 
		
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE);				
	}
	else
	{
		mav.setViewName(ForwardConstants.VIEW_PRINT_SALE_BILL_PAGE);
	}
		/* mav.setViewName(ForwardConstants.VIEW_PRINT_SALE_BILL_PAGE); */
	  
	
	
	/*int isExclusive =  store.getIsExclusive();
	if(isExclusive==0)
	{
		if(userInfo.getCompanyId() == 23){
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_COMPOSITE_OPTICAL_PAGE);
		}
		else{
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_PAGE);
			
		}
		
	}
	else
	{
		if(userInfo.getCompanyId() == 18){
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_SPECIAL_PAGE);
		}else if(userInfo.getCompanyId() == 22){
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITESPCL_80_PAGE);
		}else{
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE);
			
		}
	}*/
	
	return mav;
	}


	@RequestMapping(value = "/cashmemo80mm",method = RequestMethod.GET)
	public ModelAndView cashMemo80mm(Model model,HttpSession session,@RequestParam(	required = false,defaultValue = "0") String saleId,@RequestParam(required = false) String reprint,@RequestParam(required = false) String backUrl){
	//logger.info("In cashmemo80mm......{},{}", saleId,backUrl);
	if (!saleId.equals("0")) {
		saleId = (String.valueOf(saleId).split("-")[2]);
		//System.out.println("new saleId=" + saleId);
	}
	int manuId=0;
	ModelAndView mav = new ModelAndView();
	SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
	List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
	List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
	String printCountRes = "";
	int isRePrint=0;
    int printCount=0;
	//ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
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
		String flag="";
		if(reprint.equals("Y"))
		{
			flag = "r";
			isRePrint=1;
			printCountRes = reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			//System.out.println("print count status: "+printCountRes);
			printCount=Integer.valueOf(printCountRes);
		}else{}

		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());


		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setIsRePrint(isRePrint);
		commonResultSetMapper.setPrintCount(printCount);
		commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
		commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
		//logger.info("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
		//System.out.println("ss="+commonResultSetMapper.getNoteLineOne());
		//commonResultSetMapper.setLang(lang);
		saleHeaderDTO =gson.fromJson( cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());
		//System.err.println("_________saleHeaderDTO__________"+saleHeaderDTO.toString());
		//System.err.println("_________saleDetailsDTOs________"+saleDetailsDTOs.toString());
		/*for(SaleDetailsDTO saledetails : saleDetailsDTOs)
		{
			manuId=saledetails.getManufacturerId();
		}
		manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/
		
		mav.addObject("saleId", saleId);
		mav.addObject("backUrl",backUrl);
		mav.addObject("reprint",reprint);
		mav.addObject("printCountRes",printCountRes);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("taxDetailsDTOs",taxDetailsDTOs);
		mav.addObject("isCur",store.getCurrencyId());
		//System.out.println(store.getCurrencyId());

	}
	StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
	int isExclusive =  store.getIsExclusive();
	mav.setViewName(ForwardConstants.VIEW_PRINTMEMO80mm_PAGE);
	/*if(isExclusive==0)
	{
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_PAGE);
	}
	else
	{
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE);
	}*/
	return mav;
	}

	
	@RequestMapping(value = "/saleorder",method = RequestMethod.GET)
	public ModelAndView saleOrder(Model model,HttpSession session,	@RequestParam(	required = false,defaultValue = "0") String saleId,@RequestParam(required = false) String reprint,@RequestParam(required = false) String backUrl) throws ParseException {
	//logger.info("In cashmemo......{},{}", saleId,backUrl);
	if (!saleId.equals("0")) {
		saleId = (String.valueOf(saleId).split("-")[2]);
		//System.out.println("new saleId=" + saleId);
	}
	int manuId=0;
	ModelAndView mav = new ModelAndView();
	SaleOrderHeaderDTO saleHeaderDTO = new SaleOrderHeaderDTO();
	List<SaleOrderDetailsDTO> saleDetailsDTOs = new ArrayList<SaleOrderDetailsDTO>();
	//List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
	List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
	String printCountRes = "";
	int isRePrint=0;
    int printCount=0;
	//ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
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
		String flag="";
		/*if(reprint.equals("Y"))
		{
			flag = "r";
			isRePrint=1;
			printCountRes = reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			System.out.println("print count status: "+printCountRes);
			printCount=Integer.valueOf(printCountRes);
		}else{}*/

		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setIsRePrint(isRePrint);
		commonResultSetMapper.setPrintCount(printCount);
		commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
		commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
		//logger.info("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
		//System.out.println("ss="+commonResultSetMapper.getNoteLineOne());
		//commonResultSetMapper.setLang(lang);
		saleHeaderDTO =gson.fromJson( cashMemoService.getSalesOrderHeaderForBill(commonResultSetMapper), new TypeToken<SaleOrderHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleOrderDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleOrderDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForSaleOrderBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());
		
		/*saleHeaderDTO =gson.fromJson( cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());*/

		//System.out.println("====########===="+saleHeaderDTO.toString());
		//System.out.println("====########===="+saleDetailsDTOs.toString());
		/*for(SaleDetailsDTO saledetails : saleDetailsDTOs)
		{
			manuId=saledetails.getManufacturerId();
		}
		manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/
		/*if(userInfo.getCompanyId() == 18){
		SimpleDateFormat dt1 = new SimpleDateFormat("MMMM dd, yyyy");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newdt = new SimpleDateFormat("dd-MM-yyyy");
		String invDate= saleHeaderDTO.getInvDate();
		Date invdate = dt1.parse(invDate);
		String dueDate = saleHeaderDTO.getDueDate();
		Date duedate = dt2.parse(dueDate);
		saleHeaderDTO.setInvDate(newdt.format(invdate));
		saleHeaderDTO.setDueDate(newdt.format(duedate));
		}*/
		
		mav.addObject("saleId", saleId);
		/*mav.addObject("backUrl",backUrl+"/"+saleId);*/
		mav.addObject("backUrl",backUrl);
		mav.addObject("reprint",reprint);
		mav.addObject("printCountRes",printCountRes);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("taxDetailsDTOs",taxDetailsDTOs);
		mav.addObject("isCur",store.getCurrencyId());
		//System.err.println(store.getCurrencyId());
	}
	//StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
	/*mav.setViewName(ForwardConstants.VIEW_PRINT_SALEORDER_NONCOMPOSITE_PAGE);*/
	
	
	PrintBill billPrintData = (PrintBill)session.getAttribute(Constant.SES_BILL_PRINT_DATA);
	List<PrintBillDetails> billPrintDetailsData = (List<PrintBillDetails>)session.getAttribute(Constant.SES_BILL_PRINT_DETAILS_DATA);
	for(PrintBillDetails ob:billPrintDetailsData){
	if(ob.getBillType().equalsIgnoreCase("SO")){
		ForwardConstants.VIEW_PRINT_SALE_ORDER_BILL_PAGE = ob.getPageUrl();
		}
	 }
     mav.setViewName(ForwardConstants.VIEW_PRINT_SALE_ORDER_BILL_PAGE);
	
	
	
	
	
	/*if(userInfo.getCompanyId() == 18){
		mav.setViewName(ForwardConstants.VIEW_PRINTSALEORDER_NONCOMPOSITE_SPECIAL_PAGE);
	}else if(userInfo.getCompanyId() == 23){
		mav.setViewName(ForwardConstants.VIEW_PRINTSALEORDER_COMPOSITE_OPTICAL_PAGE);
	}else{
		mav.setViewName(ForwardConstants.VIEW_PRINT_SALEORDER_NONCOMPOSITE_PAGE);
	}*/
	
	return mav;
	}
	
}
