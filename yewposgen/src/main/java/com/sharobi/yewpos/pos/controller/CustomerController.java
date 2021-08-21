/**
 *
 */
package com.sharobi.yewpos.pos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.model.CustomerType;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.CustPaymentDetailsAllDTO;
import com.sharobi.yewpos.pos.model.CustPaymentDetailsByIdDTO;
import com.sharobi.yewpos.pos.model.CustomerDTO;
import com.sharobi.yewpos.pos.model.CustomerMaster;
import com.sharobi.yewpos.pos.model.CustomerPayment;
import com.sharobi.yewpos.pos.model.GenderMaster;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.CustomerService;
import com.sharobi.yewpos.proc.model.PaymentMode;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author habib,Manodip
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	private final static Logger logger=LoggerFactory.getLogger(CustomerController.class);

	@Autowired
     CustomerService customerService;
	@Autowired
	 VendorService vendorService;
	 Gson gson = new Gson();
	 @Autowired
     RoleService roleService;
	 @Autowired
	 CashMemoService cashMemoService;
	 @Autowired
	 StoreMgntService storeService;
	 @Autowired
	InvSetupService invsetupService;


	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : this is for load all customer
	 *  url =/loadcustomer
	 * ModelAndView
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/loadcustomer",method=RequestMethod.GET)
	public ModelAndView loadReturnMemo(Model model,HttpSession session)
	{
		//logger.info("In loadcustomer......");
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
		String df="yyyy-MM-dd";
		CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		commonResultSetMapper.setAsOnDate(DateUtil.getCurrentDateString(df));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CustomerDTO> allCustomers=customerService.getAllCustomersPost(commonResultSetMapper);
		List<GenderMaster> genderlist=cashMemoService.getGenderList(commonResultSetMapper);

		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		String countrylist = invsetupService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);

		CommonResultSetMapper commonResultSetMapperCustType = new CommonResultSetMapper();
		commonResultSetMapperCustType.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperCustType.setStoreId(userInfo.getStoreId());
		commonResultSetMapperCustType.setFinYrId(userInfo.getFinyrId());
		List<CustomerType> customerTypes = gson.fromJson(customerService.getAllCustomerType(commonResultSetMapperCustType), new TypeToken<List<CustomerType>>() {}.getType());

		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CUSTOMER_MASTER_107, lang);
	    //System.out.println(menuByUserDTO.toString());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CUSTOMER);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allCustomers",allCustomers);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.addObject("genderlist",genderlist);
		mav.addObject("customerTypes",customerTypes);
		mav.addObject("isTRN", store.getCurrencyId());
		mav.addObject("contrylist", countryMasters);
		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_PAGE);
		return mav;
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : for add customer
	 *  url =/addcustomer
	 * void
	 * @param CustomerMasterObj
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/addcustomer",method=RequestMethod.POST)
	public void adddocctr(@RequestBody String CustomerMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addcustomer......{}",CustomerMasterObj.toString());
		//ModelAndView mav = new ModelAndView();
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;

		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CustomerMaster customerMasterObject = gson.fromJson(CustomerMasterObj, new TypeToken<CustomerMaster>() {}.getType());
			customerMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			customerMasterObject.setCompanyId(userInfo.getCompanyId());
			customerMasterObject.setStoreId(userInfo.getStoreId());
			customerMasterObject.setFinyrId(userInfo.getFinyrId());
			customerMasterObject.setCreatedBy(userInfo.getId());
			customerMasterObject.setLang(lang);
			String res=customerService.addcustomer(customerMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : for edit customer
	 *  url =/editcustomer
	 * void
	 * @param CustomerMasterObj
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/editcustomer",method=RequestMethod.POST)
	public void editdoctor(@RequestBody String CustomerMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editcustomer......{}",CustomerMasterObj.toString());
		//ModelAndView mav = new ModelAndView();
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;

		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CustomerMaster customerMasterObject = gson.fromJson(CustomerMasterObj, new TypeToken<CustomerMaster>() {}.getType());
			customerMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			customerMasterObject.setCompanyId(userInfo.getCompanyId());
			customerMasterObject.setStoreId(userInfo.getStoreId());
			customerMasterObject.setFinyrId(userInfo.getFinyrId());
			customerMasterObject.setUpdatedBy(userInfo.getId());
			customerMasterObject.setLang(lang);
			String res=customerService.editcustomer(customerMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : get customer by id
	 *  url =/getCustomerbyId/{id}
	 * void
	 * @param id
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getCustomerbyId/{id}",method=RequestMethod.GET)
	public void getDoctorbyId(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getCustomerbyId......{}",id);
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper =new CommonResultSetMapper();
			commonResultSetMapper.setCustId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String CustomerMasterObject = customerService.getCustomerbyId(commonResultSetMapper);
//			System.out.println("res:"+CustomerMasterObject.toString());
			out.print(CustomerMasterObject);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : for delete customer by id
	 *  url =/deleteCustomer/{id}
	 * void
	 * @param id
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteCustomer/{id}",method=RequestMethod.GET)
	public void deleteDoctor(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteCustomer......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = customerService.deleteCustomer(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : delete customer
	 *  url =/ delcustomer
	 * void
	 * @param CommonResultSetMapperObj
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/delcustomer",method=RequestMethod.POST)
	public void delCustomer(@RequestBody String CommonResultSetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In delCustomer......{}",CommonResultSetMapperObj.toString());
		//ModelAndView mav = new ModelAndView();
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;

		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(CommonResultSetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=customerService.deletecustomer(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : get customer with credit list auto complete
	 *  url =/getcustomerwithcreditlistautocomplete
	 * void
	 * @param tagName
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getcustomerwithcreditlistautocomplete",method=RequestMethod.GET)
	public void getCustomerWithCreditListAutocom(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getcustomercreditlistautocomplete......{}",tagName.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			String df="yyyy-MM-dd";
			commonResultSetMapper.setAsOnDate(DateUtil.getCurrentDateString(df));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setCustPh(tagName.toString());
//			gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());

			String res=customerService.getCustomerWithCreditListAutocom(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : auto complete customer credit list by name
	 *  url =/getcustomerwithcreditlistautocompletebyname
	 * void
	 * @param tagName
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getcustomerwithcreditlistautocompletebyname",method=RequestMethod.GET)
	public void getCustomerWithCreditListAtoComByName(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getcustomerwithcreditlistautocompletebyname......{}",tagName.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			String df="yyyy-MM-dd";
			commonResultSetMapper.setAsOnDate(DateUtil.getCurrentDateString(df));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setCustName(tagName.toString());
//			gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());

			String res=customerService.getCustomerWithCreditListAutocom(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : for all customer auto complete
	 *  url =/getcustomerlistautocomplete
	 * void
	 * @param tagName
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getcustomerlistautocomplete",method=RequestMethod.GET)
	public void getCustomerListAtoCom(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getcustomerlistautocomplete......{}",tagName.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setCustPh(tagName.toString());
//			gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());

			String res=customerService.getCustomerListAutocomplete(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : get all customer auto complete by name
	 *  url =/getcustomerlistautocompletebyname
	 * void
	 * @param tagName
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getcustomerlistautocompletebyname",method=RequestMethod.GET)
	public void getCustomerListAtoComByName(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getcustomerlistautocomplete......{}",tagName.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setCustName(tagName.toString());
//			gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());

			String res=customerService.getCustomerListAutocomplete(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : customer payment details
	 *  url =/loadcustomerpaydet/{cid}/{outstandamt}/{custname}
	 * ModelAndView
	 * @param cid for id
	 * @param outstandamt for outstanding amount
	 * @param custname for customer name
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loadcustomerpaydet/{cid}/{outstandamt}/{custname}",
			method = RequestMethod.GET)
	public ModelAndView loadCustomerPaymentDetails(	@PathVariable("cid") int cid,@PathVariable("outstandamt") double outstandamt,@PathVariable("custname") String custname,Model model,
										HttpSession session) {
		//logger.info("In loadcustomerpaydet......");
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
		commonResultSetMapper.setStartDate(userInfo.getStartDate());
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		//System.out.println("commonResultSetMapper.getStartDate()=" + commonResultSetMapper.getStartDate());
		//System.out.println("commonResultSetMapper.getStartDate()");
		commonResultSetMapper.setCustId(cid);
		commonResultSetMapper.setCustName(custname);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.CUSTOMER_PAY_MEMO + "/" + userInfo.getFinyrCode() + "/");

		List<CustPaymentDetailsAllDTO> custPaymentDetailsAllDTOs = new ArrayList<CustPaymentDetailsAllDTO>();
		custPaymentDetailsAllDTOs = gson.fromJson(customerService.getCustomerAllPaymentDetails(commonResultSetMapper), new TypeToken<List<CustPaymentDetailsAllDTO>>() {}.getType());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CUSTOMER_MASTER_107, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CUSTOMER);
		mav.addObject("menuselect", menuselect);
		mav.addObject("cid",cid);
		mav.addObject("custName",custname);
		mav.addObject("outstandingAmnt",outstandamt);
		mav.addObject("custPaymentDetailsAllDTOs",custPaymentDetailsAllDTOs);
		mav.addObject("is_acc", userInfo.getIs_account());
		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_PAYMENT_DET_PAGE);
		commonResultSetMapper.setInvoiceNo("");
		return mav;
	}

	@RequestMapping(value = "/loadcustomerpay/{cid}/{outstandamt}/{custname}",
			method = RequestMethod.GET)
	public ModelAndView loadCustomerPayment(	@PathVariable("cid") int cid,@PathVariable("outstandamt") double outstandamt,@PathVariable("custname") String custname,Model model,
										HttpSession session) {
		//logger.info("In loadcustomerpay......");
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
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		String data = df.format(new Date());
		commonResultSetMapper.setPaymentDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		List<PaymentMode> paymentModes = gson.fromJson(vendorService.getPaymentModes(commonResultSetMapper), new TypeToken<List<PaymentMode>>() {}.getType());
		commonResultSetMapper.setCustId(cid);
		List<CustPaymentDetailsByIdDTO> custDetailsDTOs = gson.fromJson(customerService.getCustomerPendingPayment(commonResultSetMapper), new TypeToken<List<CustPaymentDetailsByIdDTO>>() {}.getType());
		//System.out.println("paymentModes:" + paymentModes.toString());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CUSTOMER);
		mav.addObject("menuselect", menuselect);
		mav.addObject("custPaymentId", 0);
		mav.addObject("paymentModes", paymentModes);
		mav.addObject("cid",cid);
		mav.addObject("custName",custname);
		mav.addObject("custDetailsDTOs", custDetailsDTOs);
		mav.addObject("outstandingAmnt",outstandamt);
		mav.addObject("is_account", userInfo.getIs_account());
		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_PAYMENT_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchcustomerpayregister",
			method = RequestMethod.POST)
	public ModelAndView searchCustomerPayRegister(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
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
		commonResultSetMapper.setInvoiceNo(Constant.CUSTOMER_PAY_MEMO + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());

		List<CustPaymentDetailsAllDTO> custPaymentDetailsAllDTOs = new ArrayList<CustPaymentDetailsAllDTO>();
		custPaymentDetailsAllDTOs = gson.fromJson(customerService.getCustomerAllPaymentDetails(commonResultSetMapper), new TypeToken<List<CustPaymentDetailsAllDTO>>() {}.getType());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CUSTOMER_MASTER_107, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("custPaymentDetailsAllDTOs", custPaymentDetailsAllDTOs);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CUSTOMER);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.addObject("cid",commonResultSetMapper.getCustId());
		mav.addObject("custName",commonResultSetMapper.getCustName());
		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_PAYMENT_DET_PAGE);
		return mav;
	}

	@RequestMapping(value="/postcustomerpayment",method=RequestMethod.POST)
	public void postCustomerPayment(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In postCustomerPayment......{}",commonResultsetMapperObj.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=customerService.postcustomerpayment(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/searchcustomerpendingpayment",
			method = RequestMethod.POST)
		public void searchCustomerPendingPayment(	@RequestBody String commonResultSetMapperStr,
											HttpSession session,
											HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			//return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperStr, new TypeToken<CommonResultSetMapper>() {}.getType());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		//logger.info("In searchCustomerPendingPayment......{}", commonResultSetMapper.toString());
		String res = customerService.getCustomerPendingPayment(commonResultSetMapper);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(res);
		out.flush();
	}

	@RequestMapping(value = "/createorupdatecustomerpayment",
			method = RequestMethod.POST)
	public void createOrUpdateCustomerPayment(@RequestBody String CustomerPaymentObj,
					HttpSession session,
					HttpServletResponse response) throws IOException {
		//logger.info("In createOrUpdateCustomerPayment......{},{},{}", CustomerPaymentObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CustomerPayment customerPayment = gson.fromJson(CustomerPaymentObj, new TypeToken<CustomerPayment>() {}.getType());
			customerPayment.setCompanyId(userInfo.getCompanyId());
			customerPayment.setStoreId(userInfo.getStoreId());
			customerPayment.setCreatedBy(userInfo.getId());
			customerPayment.setFinyrId(userInfo.getFinyrId());
			customerPayment.setFinyrCode(userInfo.getFinyrCode());
			customerPayment.setLang(lang);
			customerPayment.setQs(Constant.CUSTOMER_PAYMENT_QS);
			customerPayment.setIs_account(userInfo.getIs_account());
		//	System.err.println(customerPayment.toString());
			String res = customerService.createOrUpdateCustomerPayment(customerPayment);
		//	System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/createorupdatwithpostcustomerpayment",
			method = RequestMethod.POST)
	public void createorupdatwithpostcustomerpayment(@RequestBody String CustomerPaymentObj,
					HttpSession session,
					HttpServletResponse response) throws IOException {
		//logger.info("In createorUpdatWithPostCustomerPayment......{},{},{}", CustomerPaymentObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			String res="";
			CustomerPayment customerPayment = gson.fromJson(CustomerPaymentObj, new TypeToken<CustomerPayment>() {}.getType());
			customerPayment.setCompanyId(userInfo.getCompanyId());
			customerPayment.setStoreId(userInfo.getStoreId());
			customerPayment.setCreatedBy(userInfo.getId());
			customerPayment.setFinyrId(userInfo.getFinyrId());
			customerPayment.setFinyrCode(userInfo.getFinyrCode());
			customerPayment.setLang(lang);
			customerPayment.setQs(Constant.CUSTOMER_PAYMENT_QS);
			customerPayment.setIs_account(userInfo.getIs_account());
			//System.err.println(customerPayment.toString());
			int paymentId = Integer.parseInt(customerService.createOrUpdateCustomerPayment(customerPayment).toString());
			if(paymentId!=0)
			{
				CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
				commonResultSetMapper.setPaymentId(paymentId);
				commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
				commonResultSetMapper.setStoreId(userInfo.getStoreId());
				commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
				commonResultSetMapper.setLang(lang);
				res=customerService.postcustomerpayment(commonResultSetMapper);
			}
			else
			{
				res = "0";
			}
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loadcustomerpaybyid",
			method = RequestMethod.GET)
	public ModelAndView loadCustomerPaybyId(	Model model,
								HttpSession session,
								@RequestParam(	required = false,
												defaultValue = "0") String custPaymentId) {
		//logger.info("In cashmemo......{}", custPaymentId);
		if (!custPaymentId.equals("0")) {
			custPaymentId = (String.valueOf(custPaymentId).split("-")[2]);
			//System.out.println("new saleId=" + custPaymentId);
		}
		ModelAndView mav = new ModelAndView();
		CustPaymentDetailsAllDTO custHeaderDTO = new CustPaymentDetailsAllDTO();
		List<CustPaymentDetailsByIdDTO> custDetailsDTOs = new ArrayList<CustPaymentDetailsByIdDTO>();
		List<PaymentMode> paymentModes = new ArrayList<PaymentMode>();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if (!custPaymentId.equals("0")) {
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setPaymentId(Integer.valueOf(custPaymentId));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			paymentModes = gson.fromJson(vendorService.getPaymentModes(commonResultSetMapper), new TypeToken<List<PaymentMode>>() {}.getType());
			custHeaderDTO =gson.fromJson( customerService.getCustomerPaymentHeaderById(commonResultSetMapper), new TypeToken<CustPaymentDetailsAllDTO>() {}.getType());
			custDetailsDTOs = gson.fromJson(customerService.getCustomerPaymentDetailsById(commonResultSetMapper), new TypeToken<List<CustPaymentDetailsByIdDTO>>() {}.getType());
		}
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CUSTOMER_MASTER_107, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("custPaymentId", custPaymentId);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CUSTOMER);
		mav.addObject("menuselect", menuselect);
		mav.addObject("custHeaderDTO", custHeaderDTO);
		mav.addObject("custDetailsDTOs", custDetailsDTOs);
		mav.addObject("paymentModes", paymentModes);
		mav.addObject("outstandingAmnt",custHeaderDTO.getOutstandingAmount());
		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_PAYMENT_PAGE);
		return mav;
	}

	@RequestMapping(value = "/deletecustpayinv",
			method = RequestMethod.POST)
	public void deletePurRetInv(	@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.info("In deletepurretinv......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setDeletedBy(userInfo.getId());
			commonResultSetMapper.setCustId(0);
			String res = customerService.deleteCustPaymentInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getAllCustomerType",
			method = RequestMethod.GET)
	public void getAllCustomerType(
								HttpSession session,
								HttpServletResponse response) throws IOException {
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
			String res = customerService.getAllCustomerType(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	/**
	 * for customer payment print
	 *
	 */
	@RequestMapping(value = "/printpaymentreceipt/{id}",method = RequestMethod.GET)
	public ModelAndView printCustomerPayment(@PathVariable("id") int id,Model model,HttpSession session,	@RequestParam(required = false,defaultValue = "0") String custPaymentId) {
		//logger.info("In printpaymentreceipt......{}", id);

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
		commonResultSetMapper.setPaymentId(id);
		CustPaymentDetailsAllDTO customerDetails=customerService.getcustomerPayment(commonResultSetMapper);

		//System.err.println(customerDetails.toString());
		if (customerDetails.getChequeNo().trim().length()==0) {
			customerDetails.setChequeNo("NA");
		}

		if (customerDetails.getChequeDate().trim().length()==0) {
			customerDetails.setChequeDate("NA");
		}
		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		mav.addObject("custDet", customerDetails);
		mav.addObject(Constant.SES_LOGGED_IN_STORE,store);
		mav.addObject("cur",userInfo.getCurrency_code());
		mav.addObject("curdes", userInfo.getCurrency_desc());

		//System.out.println(store.toString());

		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_PAYMENT_PRINT);
		return mav;
	}
}
