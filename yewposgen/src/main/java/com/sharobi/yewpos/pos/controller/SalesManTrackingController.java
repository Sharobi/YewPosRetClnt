package com.sharobi.yewpos.pos.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.service.SalesTourPlanService;
import com.sharobi.yewpos.util.Constant;

@Controller
@RequestMapping("/salesmantrack")
public class SalesManTrackingController {
	private final static Logger logger = LoggerFactory.getLogger(SalesManTrackingController.class);
	@Autowired
	SalesTourPlanService salesTourPlanService;
	
@RequestMapping(value = "/getTourPlanLocations/{tourDate}/{salesManId}",
			   method = RequestMethod.GET)
 public void getTourPlanLocationDetails(@PathVariable("tourDate") String tourDate,
		 						@PathVariable("salesManId") int salesManId,
							    HttpSession session,
							    HttpServletResponse response) throws IOException {
				
	//logger.info("In getTourPlanLocations......{}{}", tourDate,salesManId);
	String lang = null;
	if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
	lang = Constant.DEFAULT_LANG;
	}
	LoginInfoByUserDTO userInfo = null;
	if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
	PrintWriter out = response.getWriter();
	response.setContentType("text/plain");
	CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
	commonResultSetMapper.setInvDate(tourDate);
	commonResultSetMapper.setSalesmanId(salesManId);
	commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
	commonResultSetMapper.setStoreId(userInfo.getStoreId());
	commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
	String val = salesTourPlanService.getSaleTourPlanLocationDetailsByDateAndSalesmanId(commonResultSetMapper);
	//System.out.println("res:" + val);
	out.print(val);
	out.flush();
    }
  }

}
