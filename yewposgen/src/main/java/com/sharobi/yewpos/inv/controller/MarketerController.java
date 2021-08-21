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
import com.sharobi.yewpos.inv.model.MarketerMaster;
import com.sharobi.yewpos.inv.service.MarketerService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping(value="marketer")
public class MarketerController {

	private final static Logger logger=LoggerFactory.getLogger(MarketerController.class);
	@Autowired
	 MarketerService merketerservice;
	@Autowired
	RoleService roleService;

	@RequestMapping(value="/loadmarketer",method=RequestMethod.GET)
	public ModelAndView market(Model model,HttpSession session)
	{
		//logger.info("In loadmarketer hit......");
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
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.MARKETERSETUP_MARKETER_201M, lang);
		List<MarketerMaster> allMarketerMasterlist=merketerservice.getAllMarkertermasterlist(userInfo.getCompanyId(),lang);
		//System.out.println("res:"+allMarketerMasterlist.toString());
		MenuSelection menuselect=new MenuSelection();

		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.MARKETER);

		mav.addObject("menuselect",menuselect);
		mav.addObject("allMarketerMasterlist",allMarketerMasterlist);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_MARKETER_PAGE);
		return mav;
	}

	@RequestMapping(value="/searchMarketter",method=RequestMethod.POST)
	public ModelAndView searchMarketter(@ModelAttribute("marketerMaster") MarketerMaster marketerMaster ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchMarketer......{},{},{}",marketerMaster.toString());
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
		//System.out.println("Test comp id");
		//System.out.println(userInfo.getCompanyId());
		//marketerMaster.setCompanyId(userInfo.getCompanyId());
		List<MarketerMaster> allMarketer=merketerservice.searchMarketer(marketerMaster);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CONTENT_SETUP_201K, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.MARKETER);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allMarketer",allMarketer);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_MARKETER_PAGE);
		return mav;
	}

	@RequestMapping(value="/addMarketer",method=RequestMethod.POST)
	public void addManufacturer(@RequestBody String MarketerMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addMarketer. hit.....{},{},{}",MarketerMasterObj.toString());
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
			 MarketerMaster marketerMasterObject = gson.fromJson(MarketerMasterObj, new TypeToken<MarketerMaster>() {}.getType());
			//System.out.println(marketerMasterObject.toString());

			marketerMasterObject.setLang(lang);
			String res=merketerservice.addMarketer(marketerMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}


	@RequestMapping(value="/editMarketer",method=RequestMethod.POST)
	public void editMarketer(@RequestBody String MarketerMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editMarketer......{}",MarketerMasterObj.toString());
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
			MarketerMaster marketerMasterObject = gson.fromJson(MarketerMasterObj, new TypeToken<MarketerMaster>() {}.getType());

			marketerMasterObject.setLang(lang);
			String res=merketerservice.editMarketer(marketerMasterObject);
			 //System.out.println("res:"+res);
			 out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteMarketer/{id}",method=RequestMethod.GET)
	public void deleteMarketer(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteMarketer......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = merketerservice.deleteMaketer(id);
		 //	System.out.println("res:"+res);
		  	out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getMarketerbyId/{id}",method=RequestMethod.GET)
	public void getDoctorbyId(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getMarketerbyId......{}",id);
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
			commonResultSetMapper.setMarketerCode(String.valueOf(id));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String marketerMasterObject = merketerservice.getMarketerbyId(id,lang);
//			System.out.println("res:"+CustomerMasterObject.toString());
			out.print(marketerMasterObject);
			out.flush();
		}
	}
	@RequestMapping(value="/getmarketerlistautocomplete",method=RequestMethod.GET)
	public void getCustomerListAtoCom(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getmarketerlistautocomplete......{}",tagName.toString());
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

			String res=merketerservice.getMarketerListAutocomplete(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
}
