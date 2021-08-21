package com.sharobi.yewpos;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/","/.htm","/index.htm"}, method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		///logger.info("Welcome home! The client locale is {}.", locale);

		return new ModelAndView("redirect:/authentication/login.htm");
		
	}



	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView langteset(Locale locale) {
		//logger.info("language test test /test");
		ModelAndView name = new ModelAndView();

		name.setViewName("test");
		return name;
	}

}
