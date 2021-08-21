/**
 *
 */
package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.sharobi.yewpos.inv.model.SalesmanDTO;
import com.sharobi.yewpos.inv.model.SalesmanMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.SalesmanService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping(value="salesman")
public class SalesmanController {

	private final static Logger logger=LoggerFactory.getLogger(MarketerController.class);

	@Autowired
	SalesmanService salesmanService;
	@Autowired
	InvSetupService invSetUpService;
	  Gson gson = new Gson();

	private final static RoleService roleService=new RoleService();

	@RequestMapping(value="/searchsalesman",method=RequestMethod.POST)
	public ModelAndView searchSalesman(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,HttpSession session)
	{
		//logger.info("In searchsalesman hit......"+commonResultSetMapper.toString());
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

		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.SALESMAN_SETUP_201S, lang);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		List<SalesmanDTO> allSalesmanMasterlist=salesmanService.getAllSalesmanmasterlist(commonResultSetMapper);
		//System.out.println("res:"+allSalesmanMasterlist.toString());
		MenuSelection menuselect=new MenuSelection();

		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.SALESMAN);

		mav.addObject("menuselect",menuselect);
		mav.addObject("countryMasters",countryMasters);
		mav.addObject("allSalesmanMasterlist",allSalesmanMasterlist);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_SALESMAN_PAGE);
		return mav;
	}

	@RequestMapping(value="/addSalesman",method=RequestMethod.POST)
	public void addManufacturer(@RequestBody String SalesmanMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addSalesman. hit.....{},{},{}",SalesmanMasterObj.toString());
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
			 SalesmanMaster salesmanMasterObject = gson.fromJson(SalesmanMasterObj, new TypeToken<SalesmanMaster>() {}.getType());
			 salesmanMasterObject.setCompanyId(userInfo.getCompanyId());
			 salesmanMasterObject.setStoreId(userInfo.getStoreId());
			 salesmanMasterObject.setFinyrID(userInfo.getFinyrId());
			 salesmanMasterObject.setCreatedBy(userInfo.getId());
			//System.out.println(salesmanMasterObject.toString());

			salesmanMasterObject.setLang(lang);
			String res=salesmanService.addSalesman(salesmanMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}


	@RequestMapping(value="/editSalesman",method=RequestMethod.POST)
	public void editMarketer(@RequestBody String SalesmanMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editSalesman......{}",SalesmanMasterObj.toString());
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
			SalesmanMaster salesmanMasterObject = gson.fromJson(SalesmanMasterObj, new TypeToken<SalesmanMaster>() {}.getType());
			 salesmanMasterObject.setCompanyId(userInfo.getCompanyId());
			 salesmanMasterObject.setStoreId(userInfo.getStoreId());
			 salesmanMasterObject.setFinyrID(userInfo.getFinyrId());
			 salesmanMasterObject.setUpdatedBy(userInfo.getId());
			salesmanMasterObject.setLang(lang);
			String res=salesmanService.editSalesman(salesmanMasterObject);
			 //System.out.println("res:"+res);
			 out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteSalesman/{id}",method=RequestMethod.GET)
	public void deleteMarketer(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteSalesman......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper =new CommonResultSetMapper();
			commonResultSetMapper.setId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			String res = salesmanService.deleteSalesman(commonResultSetMapper);
		 	//System.out.println("res:"+res);
		  	out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getSalesmanbyId/{id}",method=RequestMethod.GET)
	public void getDoctorbyId(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getSalesmanbyId......{}",id);
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
			commonResultSetMapper.setId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			String salesmanMasterObject = salesmanService.getSalesmanbyId(commonResultSetMapper);
//			System.out.println("res:"+CustomerMasterObject.toString());
			out.print(salesmanMasterObject);
			out.flush();
		}
	}
	@RequestMapping(value="/getSalesmanlistautocomplete",method=RequestMethod.GET)
	public void getSalesmanListAtoCom(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getSalesmanlistautocomplete......{}",tagName.toString());
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

			String res=salesmanService.getSalesmanListAutocomplete(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
}
