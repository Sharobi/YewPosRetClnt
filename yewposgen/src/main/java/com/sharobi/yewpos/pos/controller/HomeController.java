/**
 *
 */
package com.sharobi.yewpos.pos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.HomeService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.PrintBill;
import com.sharobi.yewpos.storemgnt.model.PrintBillDetails;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;

/**
 * @author habib
 *
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	private final static Logger logger=LoggerFactory.getLogger(HomeController.class);
	@Autowired
	RoleService roleService;
	@Autowired
    StoreMgntService storeService;
	@Autowired
	HomeService homeService;
	@Autowired
	CashMemoService cashMemoService;
	Gson gson = new Gson();

	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(Model model,HttpSession session)
	{
		//logger.info("In welcome......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}


		CommonResultSetMapper commonResultSetMapper= new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId()); // new added 22.4.2019
		List<StoreMaster>  allStorelist=homeService.getAllStore(commonResultSetMapper);
		//System.out.println("==============="+allStorelist.toString());
        session.setAttribute(Constant.SES_ALL_STORES_DATA, allStorelist); // new added 17.4.2019
		/*System.err.println(userInfo.getIsTaxRegNo());
		System.err.println(userInfo.getTaxRegNo());
		System.err.println(userInfo.getTaxRegNoText());*/
       // System.out.println("==================New Store Id=================="+userInfo.getStoreId());
		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		//System.err.println(store.toString());
		/*List<MenuByUserDTO> appMenuList=roleService.getAppMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(), userInfo.getId(),userInfo.getProductTypeId());*/
		List<MenuByUserDTO> appMenuList=roleService.getAppMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId());
		/*System.out.println("==================Menu List=================="+gson.toJson(appMenuList));*/
		session.setAttribute(Constant.SES_LOGGED_IN_STORE, store);
		session.setAttribute(Constant.SES_APP_MENU_LIST, appMenuList);
		mav.addObject(Constant.SES_LOGGED_IN_STORE,store);
		//System.err.println(store.toString());
		mav.addObject(Constant.SES_APP_MENU_LIST,appMenuList);
		Locale locale = LocaleContextHolder.getLocale();
		session.setAttribute(Constant.SES_LOGGED_IN_LANG, locale.getDisplayLanguage());
		
		//For hold prefix list in session
		CommonResultSetMapper commonResultSetMappermulser = new CommonResultSetMapper();
		commonResultSetMappermulser.setCompanyId(userInfo.getCompanyId());
		commonResultSetMappermulser.setStoreId(userInfo.getStoreId());
		commonResultSetMappermulser.setFinYrId(userInfo.getFinyrId());
		List<CommonResultSetMapper> commonResultSetMappermulserret = gson.fromJson(cashMemoService.getInvoicePrefixByqs(commonResultSetMappermulser),new TypeToken<List<CommonResultSetMapper>>() {}.getType());
		session.setAttribute(Constant.SES_BILL_PREFIX_LIST, commonResultSetMappermulserret);   
		
		mav.addObject(Constant.HOME,"Y");
		mav.addObject("allstorelist", allStorelist);
		mav.addObject("isTRN", userInfo.getIsTaxRegNo());
		mav.addObject("financialyear", userInfo.getStartDate().toString());
		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("cur_id", store.getCurrencyId());
		
		PrintBill billPrintData =homeService.getBillPrintData(userInfo.getCompanyId(),userInfo.getStoreId()); 
		List<PrintBillDetails> billPrintDetailsData = homeService.getbillPrintDetailsData(userInfo.getCompanyId(),userInfo.getStoreId());
		//logger.info("_______Bill print data______{}"+gson.toJson(billPrintData));
		//logger.info("_______Bill print details data______{}"+gson.toJson(billPrintDetailsData));
		session.setAttribute(Constant.SES_BILL_PRINT_DATA, billPrintData);   
		session.setAttribute(Constant.SES_BILL_PRINT_DETAILS_DATA, billPrintDetailsData);   
		
        if(userInfo.getIsAdmin() == 1){
        	mav.setViewName(ForwardConstants.VIEW_ADMIN_HOME_PAGE);
        }else{
        	mav.setViewName(ForwardConstants.VIEW_USER_HOME_PAGE);
        }
		
		return mav;
	}


	@RequestMapping(value = "/getalltotal", method = RequestMethod.POST)
	public void getalltotal(@RequestBody CommonResultSetMapper commonResultSetMapper,HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In getalltotal......{}" );

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

		   //CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			 commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			 commonResultSetMapper.setStoreId(userInfo.getStoreId());
			 commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			String res =gson.toJson(homeService.getTotal(commonResultSetMapper));
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}


	@RequestMapping(value = "/getlinechart", method = RequestMethod.POST)
	public void getLinechart(@RequestBody CommonResultSetMapper commonResultSetMapper,HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In getlinechart......{}" );

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			// CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			 commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			 commonResultSetMapper.setStoreId(userInfo.getStoreId());
			 commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			//String res =gson.toJson(homeService.getLineChart(commonResultSetMapper));
			 String res =homeService.getLineChart(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

    // New added 23.4.2019
	@RequestMapping(value="/changeStore/{storeId}",method=RequestMethod.GET)
	public void changeCurrentStore(@PathVariable int storeId,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In changestore......{}",storeId);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			List<StoreMaster>  allStorelist = null;
			allStorelist = (List<StoreMaster>) session.getAttribute(Constant.SES_ALL_STORES_DATA);
			for(StoreMaster ob: allStorelist){
				if(ob.getId()==storeId){
					userInfo.setStoreId(storeId);
					userInfo.setFinyrId(ob.getFinYrId());
					break;
				}
			}
			userInfo.setPassword(null);
			session.setAttribute(Constant.SES_LOGGED_IN_USER, userInfo);
			
			LoginInfoByUserDTO ob = ((LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)); 
			//System.out.println("++++++++++++++ UPDATED SESSION ++++++++++++"+ob.toString());
			
			out.print("success");
			out.flush();
		}
	}

}
