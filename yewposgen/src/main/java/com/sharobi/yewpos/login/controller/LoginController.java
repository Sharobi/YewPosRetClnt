/**
 *
 */
package com.sharobi.yewpos.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.login.service.LoginService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;



/**
 * @author habib
 * @Modify Manodip
 */
@Controller
@RequestMapping("/authentication")
public class LoginController {
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

@Autowired
 LoginService loginService;
	@RequestMapping(value = "/login",method = RequestMethod.GET)
		public ModelAndView login(Model model) {
		//logger.info("In Login...");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ForwardConstants.VIEW_LOGIN_PAGE);
		model.addAttribute("user", new LoginInfoByUserDTO());
		return mav;
	}

	@RequestMapping(value = "/dologin",method = RequestMethod.POST)
		public ModelAndView doLogin(@Valid @ModelAttribute("user") LoginInfoByUserDTO user,
				BindingResult result,
				HttpSession session,HttpServletRequest request) {
		//logger.info("In doLogin from ...{}",request.getRemoteAddr());
		String ipAddress = request.getRemoteAddr();
//		System.out.println("Client ipAddress="+ipAddress);

		ModelAndView mav = new ModelAndView();
		mav.setViewName(ForwardConstants.VIEW_LOGIN_PAGE);
		if (!result.hasErrors()) {
			LoginInfoByUserDTO userInfo=loginService.login(user);
		if(null==userInfo){
			result.reject("Failed.user.invalid");
		}else{
			if (userInfo.getId() != 0) {
				userInfo.setPassword(null);
				session.setAttribute(Constant.SES_LOGGED_IN_USER, userInfo);
				session.setAttribute(Constant.SES_LOGGED_IN_STORE_ID, userInfo.getStoreId());// new added 23.4.2019
				mav.setViewName(ForwardConstants.REDIRECT_HOME);
			}
			else {
				if (userInfo.getMessage().equalsIgnoreCase("License Expired")) {
					result.reject("Failed.user.invalid.explicense");
					mav.addObject("msg", "explicense");
				} else if (userInfo.getMessage().contains("You have no license!")) {
					result.reject("Failed.user.invalid.nolicense");
					mav.addObject("msg", "nolicense");
				} else if(userInfo.getMessage().equalsIgnoreCase("Store Id Not Matching")){
					result.reject("Failed.user.invalid.store.notmatching");
				} else if(userInfo.getMessage().equalsIgnoreCase("Server Closed")){
					result.reject("Failed.server.closed");
				}
				else
					result.reject("Failed.user.invalid");
			}
		}
		}
		return mav;
	}

	@RequestMapping(value = "/logout",
			method = RequestMethod.GET)
		public ModelAndView logout(	Model model,
								HttpSession session,HttpServletRequest request) {
		//logger.info("in logout from...{}",request.getRemoteAddr());
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute(Constant.SES_LOGGED_IN_USER) != null) {
			session.removeAttribute(Constant.SES_LOGGED_IN_USER);
			session.removeAttribute(Constant.SES_LOGGED_IN_STORE);
			session.removeAttribute(Constant.SES_APP_MENU_LIST);
			session.removeAttribute(Constant.SES_ALL_STORES_DATA); // new 22.04.2019
			session.removeAttribute(Constant.SES_LOGGED_IN_STORE_ID); // new 23.04.2019
			session.removeAttribute(Constant.SES_BILL_PREFIX_LIST); // new added 7.9.2019
			session.removeAttribute(Constant.SES_BILL_PRINT_DATA); // new added 2.8.2019
			session.removeAttribute(Constant.SES_BILL_PRINT_DETAILS_DATA); // new added 2.8.2019
			
		}
		model.addAttribute("user", new LoginInfoByUserDTO());
		mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
		return mav;
		}

	@RequestMapping(value = "/test",
			method = RequestMethod.GET)
		public ModelAndView test(Model model,HttpSession session,HttpServletRequest request) {
		//logger.info("in logout from...{}",request.getRemoteAddr());
		ModelAndView mav = new ModelAndView();

		model.addAttribute("user", new LoginInfoByUserDTO());
		mav.setViewName(ForwardConstants.TEST);
		return mav;
		}


}
