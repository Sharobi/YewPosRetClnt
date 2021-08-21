/**
 *
 */
package com.sharobi.yewpos.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.acc.model.AccountGroupDTO;
import com.sharobi.yewpos.acc.model.AccountTypeDTO;
import com.sharobi.yewpos.acc.service.AccGroupService;
import com.sharobi.yewpos.acc.service.AccSetupService;
import com.sharobi.yewpos.inv.model.AreaDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author SK A Siddik
 *
 *         Nov 7, 2017
 */


@Controller
@RequestMapping("/accntsetup")
public class AccountSetupController {
	private final static Logger logger = LoggerFactory.getLogger(AccountSetupController.class);
	@Autowired
	RoleService roleService;
	@Autowired
	InvSetupService invsetupService;
	@Autowired
	AccGroupService accountgroupservice;
	@Autowired
	AccSetupService accSetupService;
	Gson gson = new Gson();



	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : load all account list
	 *  url =/loadaccntsetup
	 * @param model
	 * @param session
	 * @return
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/loadaccntsetup", method = RequestMethod.GET)
	public ModelAndView loadloadaccntsetup(Model model, HttpSession session) {
		///logger.info("In loadaccountsetup ......");
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

		/*
		 * service loading start here
		 */

		String countrylist = invsetupService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		List<AccountTypeDTO> allAccTypes = accountgroupservice.getAllAccType(commonResultSetMapper);

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setAsOnDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<AccountDTO> accountMasterslist = accSetupService.getAllAccountSetup(commonResultSetMapper);

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.ACCOUNTS_ACCOUNT_SETUP_401B, lang);

		///System.out.println(accountMasterslist.toString());

		MenuSelection menuselect = new MenuSelection();

		menuselect.setMenu(Constant.ACCOUNTS);
		menuselect.setSubMenu(Constant.ACCOUNT_SETUP);
		menuselect.setChildsubMenu(Constant.ACCOUNT_SETUP_ACCOUNTSETUP);
		mav.addObject("menuselect", menuselect);

		mav.addObject("cur", userInfo.getCurrency_code());

		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("accountgrouplist", accountgrouplist);
		mav.addObject("contrylist", countryMasters);
		mav.addObject("allAccTypes", allAccTypes);
		mav.addObject("accountlist", accountMasterslist);
		mav.addObject("userinformation", userInfo);/*Sayantan Mahanty added date-19/02/2020*/
		mav.setViewName(ForwardConstants.VIEW_ACCONTSETUP_PAGE);
		return mav;
	}





	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : add new account
	 *  url =/addaccountsetup
	 * @param accountsetup
	 * @param session
	 * @param response
	 * @throws IOException
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/addaccountsetup", method = RequestMethod.POST)
	public void addaccontsetup(@RequestBody String accountsetup, HttpSession session, HttpServletResponse response)
			throws IOException {
		///logger.info("In addaccontsetup......{}" + accountsetup);

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			AccountDTO accountMaster = gson.fromJson(accountsetup, new TypeToken<AccountDTO>() {
			}.getType());
			accountMaster.setCompanyId(userInfo.getCompanyId());
			accountMaster.setStoreId(userInfo.getStoreId());
			accountMaster.setCreatedBy(userInfo.getId());
			accountMaster.setLang(lang);
			accountMaster.setFinyrId(userInfo.getFinyrId());

			String res = accSetupService.addAccountSetup(accountMaster);

			//logger.info("res:" + res);
			out.print(res);
			out.flush();
		}
	}




	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : update the account
	 *  url =/updateaccountsetup
	 * @param accountsetup
	 * @param session
	 * @param response
	 * @throws IOException
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/updateaccountsetup", method = RequestMethod.POST)
	public void updateaccountsetup(@RequestBody String accountsetup, HttpSession session, HttpServletResponse response)
			throws IOException {
		///logger.info("In updateaccountsetup......{}" + accountsetup);

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			AccountDTO accountMaster = gson.fromJson(accountsetup, new TypeToken<AccountDTO>() {
			}.getType());
			accountMaster.setCompanyId(userInfo.getCompanyId());
			accountMaster.setStoreId(userInfo.getStoreId());
			accountMaster.setCreatedBy(userInfo.getId());
			accountMaster.setLang(lang);
			accountMaster.setFinyrId(userInfo.getFinyrId());

			String res = accSetupService.updateAccountSetup(accountMaster);

			///logger.info("res:" + res);
			out.print(res);
			out.flush();
		}
	}



	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : delete the account by id
	 *  url =/deleteaccount/{id}
	 * @param id
	 * @param session
	 * @param response
	 * @throws IOException
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/deleteaccount/{id}", method = RequestMethod.GET)
	public void deleteAcc(@PathVariable("id") int id, HttpSession session, HttpServletResponse response)
			throws IOException {
		///logger.info("In deleteaccount......{}", id);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setId(id);
			String res = accSetupService.deleteAccount(commonResultSetMapper);

			///logger.info("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> :  get tha all account group name
	 *  url =/getaccontgroupname
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/getaccontgroupname", method = RequestMethod.GET)
	public List<AccountGroupDTO> getAccontGroupName(HttpSession session, HttpServletResponse response)
			throws IOException {
		///logger.info("In getAccontGroupName hit......{}");

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			List<AccountGroupDTO> allAccTypes = accountgroupservice.getAllAccGroup(commonResultSetMapper);
			//logger.info("getAllAccgroup List :" + allAccTypes);

			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(allAccTypes));

		}
		return null;
	}



	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> :  get area list  by zone id
	 *  url = /getareabyzone
	 * @param commonresultsetmapperObj
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/getareabyzone", method = RequestMethod.POST)
	public List<AreaDTO> getAreabyZone(@RequestBody String commonresultsetmapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In getAreabyZone......{}", commonresultsetmapperObj);

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			List<AreaDTO> arealist = invsetupService.getAllAreaList(commonResultSetMapper);

			//logger.info("autocompleteitem List :" + arealist);
			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(arealist));

		}
		return null;
	}

	/**
	 * @author SK A SIDDIK
	 * <h5>Description</h5> : search the account
	 *  url = /searchaccount
	 * @param commonresultsetmapperObj
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 * Jun 19, 2018
	 */
	@RequestMapping(value = "/searchaccount", method = RequestMethod.POST)
	public ResponseObj searchaccount(@RequestBody String commonresultsetmapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {
		///logger.info("In searchaccount......{},{},{}", commonresultsetmapperObj);

		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());

			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);

			ResponseObj responseObj = accSetupService.searchaccount(commonResultSetMapper);

			///logger.info("searchaccount List :" + responseObj);

			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(responseObj));
		}
		return null;
	}

}
