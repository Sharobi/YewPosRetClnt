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
import com.sharobi.yewpos.inv.model.AreaDTO;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.ChargeMaster;
import com.sharobi.yewpos.inv.model.CityDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.model.DoctorMaster;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.SubCategoryMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.model.VariantDTO;
import com.sharobi.yewpos.inv.model.VariantMaster;
import com.sharobi.yewpos.inv.model.VariantTypeDTO;
import com.sharobi.yewpos.inv.model.ZoneDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;



/**
 * @author habib, Manodip,Arunima
 *
 */

@Controller
@RequestMapping("/invsetup")
public class InvSetupController {
	private final static Logger logger=LoggerFactory.getLogger(InvSetupController.class);

	 @Autowired
	  InvSetupService invSetUpService;
	 @Autowired
	  RoleService roleService;
	  Gson gson = new Gson();

	@RequestMapping(value="/unit",method=RequestMethod.GET)
	public ModelAndView loadUnit(Model model,HttpSession session)
	{
		//logger.info("In unitsetup......");
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
		List<UnitMaster> allUnit=invSetUpService.getAllUnit(userInfo.getCompanyId(),lang);
		//System.out.println("res:"+allUnit.toString());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.UNIT);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_UNIT_PAGE);
		mav.addObject("allUnit",allUnit);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/addunit",method=RequestMethod.POST)
	public void addUnit(@RequestBody String UnitMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addunit......{},{},{}",UnitMasterObj.toString());
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
			UnitMaster unitMasterObject = gson.fromJson(UnitMasterObj, new TypeToken<UnitMaster>() {}.getType());
			unitMasterObject.setCompanyId(userInfo.getCompanyId());
			unitMasterObject.setCreatedBy(userInfo.getId());
			unitMasterObject.setLang(lang);
			String res=invSetUpService.addUnit(unitMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getUnitbyId/{id}",method=RequestMethod.GET)
	public void getUnitById(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getUnitById......{}",id);
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
			UnitMaster UnitMasterObject = invSetUpService.getUnitById(id,lang);
			//System.out.println("res:"+UnitMasterObject.toString());
			out.print(UnitMasterObject.toString());
			out.flush();
		}
	}

	@RequestMapping(value="/editunit",method=RequestMethod.POST)
	public void editUnit(@RequestBody String UnitMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editunit......{}",UnitMasterObj.toString());
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
			UnitMaster unitMasterObject = gson.fromJson(UnitMasterObj, new TypeToken<UnitMaster>() {}.getType());
			unitMasterObject.setCompanyId(userInfo.getCompanyId());
			unitMasterObject.setUpdatedBy(userInfo.getId());
			unitMasterObject.setLang(lang);
			String res=invSetUpService.editUnit(unitMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteUnit/{id}",method=RequestMethod.GET)
	public void deleteUnit(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteUnit......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteUnit(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/autocompleteitemlooseunit", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemLooseUnitAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In getItemLooseUnitAutocompleteList......{}", tagName);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
		//	mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return null;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		UnitMaster unitMaster=new UnitMaster();
		unitMaster.setCode(tagName.trim());
		unitMaster.setCompanyId(userInfo.getCompanyId());
		unitMaster.setTypeId(Constant.LOOSE_UNIT_ID);
		List<UnitMaster> unitMasters=invSetUpService.searchUnitAutoComplete(unitMaster);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(unitMasters));
		return null;

	}

	@RequestMapping(value = "/autocompleteitempackingunit", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemPackingUnitAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In getItemPackingUnitAutocompleteList......{}", tagName);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
		//	mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return null;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		UnitMaster unitMaster=new UnitMaster();
		unitMaster.setCode(tagName.trim());
		unitMaster.setCompanyId(userInfo.getCompanyId());
		unitMaster.setTypeId(Constant.PACKING_UNIT_ID);
		List<UnitMaster> unitMasters=invSetUpService.searchUnitAutoComplete(unitMaster);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(unitMasters));
		return null;

	}

	@RequestMapping(value="/category",method=RequestMethod.GET)
	public ModelAndView loadCategory(Model model,HttpSession session)
	{
		//logger.info("In categorysetup......");
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
		List<CategoryMaster> allCat=invSetUpService.getAllCat(userInfo.getCompanyId(),lang);
		//System.out.println("res:"+allCat.toString());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CATEGORY_SETUP_201C, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CAT);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allCat",allCat);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_CATEGORY_PAGE);
		return mav;
	}

	@RequestMapping(value="/getallcatjson",method=RequestMethod.POST)
	public void getallcatjson(@RequestBody String CategoryMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
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

			String res=invSetUpService.getAllCatJson(userInfo.getCompanyId(),lang);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editcat",method=RequestMethod.POST)
	public void editcat(@RequestBody String CategoryMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editcat......{}",CategoryMasterObj.toString());
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
			CategoryMaster CategoryMaster = gson.fromJson(CategoryMasterObj, new TypeToken<CategoryMaster>() {}.getType());
			CategoryMaster.setUpdatedDate(DateUtil.getCurrentDate());
			CategoryMaster.setCompanyId(userInfo.getCompanyId());
			CategoryMaster.setUpdatedBy(userInfo.getId());
			CategoryMaster.setLang(lang);
			//System.out.println("Json format: "+CategoryMaster.toString());
			String res=invSetUpService.editCat(CategoryMaster);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteCat/{id}",method=RequestMethod.GET)
	public void deleteCat(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteCat......{}",id);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteCat(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/addcat",method=RequestMethod.POST)
	public void addcat(@RequestBody String CategoryMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addcategory......{}",CategoryMasterObj.toString());
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
			CategoryMaster CategoryMasterObject = gson.fromJson(CategoryMasterObj, new TypeToken<CategoryMaster>() {}.getType());
			CategoryMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			CategoryMasterObject.setCompanyId(userInfo.getCompanyId());
			CategoryMasterObject.setCreatedBy(userInfo.getId());
			CategoryMasterObject.setLang(lang);
			//System.out.println("Json format: "+CategoryMasterObject.toString());
			String res=invSetUpService.addCat(CategoryMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/subcategory",method=RequestMethod.GET)
	public ModelAndView loadSubcategory(Model model,HttpSession session)
	{
		//logger.info("In subcategorysetup......");
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		List<CategoryMaster> allCat=invSetUpService.getAllCat(userInfo.getCompanyId(),lang);
		List<SubCategoryMaster> allSubCat=invSetUpService.getAllSubCat(userInfo.getCompanyId(),lang);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.SUB_CATEGORY_SETUP_201D, lang);
		//List<String> catName = new ArrayList();
		for(int i=0;i<allSubCat.size();i++)
		{
				SubCategoryMaster subCatDetails= allSubCat.get(i);
		       // System.out.println("category Name-->" +subCatDetails.getCategoryId());
		        CategoryMaster CategoryMaster = invSetUpService.getCatDetailsById(subCatDetails.getCategoryId(),lang);
		        subCatDetails.setCategoryName(CategoryMaster.getName());
		        allSubCat.set(i, subCatDetails);
		}
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.SUBCAT);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allSubCat",allSubCat);
		mav.addObject("allCat",allCat);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_SUBCATEGORY_PAGE);
		return mav;
	}

	@RequestMapping(value="/addsubcat",method=RequestMethod.POST)
	public void addsubcat(@RequestBody String SubCategoryMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addsubcategory......{}",SubCategoryMasterObj.toString());
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
			SubCategoryMaster SubCategoryMasterObject = gson.fromJson(SubCategoryMasterObj, new TypeToken<SubCategoryMaster>() {}.getType());
			SubCategoryMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			SubCategoryMasterObject.setCompanyId(userInfo.getCompanyId());
			SubCategoryMasterObject.setCreatedBy(userInfo.getId());
			SubCategoryMasterObject.setLang(lang);
			//System.out.println("Json format: "+SubCategoryMasterObject.toString());
			String res=invSetUpService.addsubcat(SubCategoryMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editsubcat",method=RequestMethod.POST)
	public void editsubcat(@RequestBody String SubCategoryMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editsubcat......{}",SubCategoryMasterObj.toString());
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
			SubCategoryMaster SubCategoryMasterObject = gson.fromJson(SubCategoryMasterObj, new TypeToken<SubCategoryMaster>() {}.getType());
			SubCategoryMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			SubCategoryMasterObject.setCompanyId(userInfo.getCompanyId());
			SubCategoryMasterObject.setUpdatedBy(userInfo.getId());
			SubCategoryMasterObject.setLang(lang);
			//System.out.println("Json format: "+SubCategoryMasterObject.toString());
			String res=invSetUpService.editsubcat(SubCategoryMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteSubCat/{id}",method=RequestMethod.GET)
	public void deleteSubCat(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteSubCat......{}",id);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteSubCat(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/doctor",method=RequestMethod.GET)
	public ModelAndView loadDoctor(Model model,HttpSession session)
	{
		//logger.info("In doctorsetup......");
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
		List<DoctorMaster> allDctrs=invSetUpService.getAllDctrs(userInfo.getCompanyId(),lang);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.DOCTOR_SETUP_201F, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.DOCTOR);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allDctrs",allDctrs);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_DOCTOR_PAGE);
		return mav;
	}

	@RequestMapping(value="/adddocctr",method=RequestMethod.POST)
	public void adddocctr(@RequestBody String DoctorMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In adddocctr......{}",DoctorMasterObj.toString());
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
			DoctorMaster doctorMasterObject = gson.fromJson(DoctorMasterObj, new TypeToken<DoctorMaster>() {}.getType());
			doctorMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			doctorMasterObject.setCompanyId(userInfo.getCompanyId());
			doctorMasterObject.setStoreId(userInfo.getStoreId());
			doctorMasterObject.setCreatedBy(userInfo.getId());
			doctorMasterObject.setLang(lang);
			String res=invSetUpService.adddocctr(doctorMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editdoctor",method=RequestMethod.POST)
	public void editdoctor(@RequestBody String DoctorMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editdoctor......{}",DoctorMasterObj.toString());
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
			DoctorMaster doctorMasterObject = gson.fromJson(DoctorMasterObj, new TypeToken<DoctorMaster>() {}.getType());
			doctorMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			doctorMasterObject.setCompanyId(userInfo.getCompanyId());
			doctorMasterObject.setStoreId(userInfo.getStoreId());
			doctorMasterObject.setUpdatedBy(userInfo.getId());
			doctorMasterObject.setLang(lang);
			String res=invSetUpService.editdoctor(doctorMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getDoctorbyId/{id}",method=RequestMethod.GET)
	public void getDoctorbyId(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getDoctorbyId......{}",id);
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
			DoctorMaster DoctorMasterObject = invSetUpService.getDoctorbyId(id,lang);
			//System.out.println("res:"+DoctorMasterObject.toString());
			out.print(new Gson().toJson(DoctorMasterObject));
			out.flush();
		}
	}

	@RequestMapping(value="/deleteDoctor/{id}",method=RequestMethod.GET)
	public void deleteDoctor(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteDoctor......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteDoctor(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getdoctorlistautocompletebyname",method=RequestMethod.GET)
	public void getDoctorListAtoComByName(@RequestParam String tagName,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getDoctorListAtoComByName......{}",tagName.toString());
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
			commonResultSetMapper.setDoctorName(tagName.toString());
//			gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());

			String res=invSetUpService.getDoctorListAutocomplete(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/rack",method=RequestMethod.GET)
	public ModelAndView loadRack(Model model,HttpSession session)
	{
		//logger.info("In racksetup......");
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
		List<RackMaster> allRacks=invSetUpService.getAllRack(userInfo.getCompanyId(),lang);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.RACK_SETUP_201G, lang);
		//System.out.println("res:"+allRacks.toString());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.RACK);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allRacks",allRacks);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_RACK_PAGE);
		return mav;
	}

	@RequestMapping(value="/addrack",method=RequestMethod.POST)
	public void addrack(@RequestBody String RackMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addrack......{}",RackMasterObj.toString());
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
			RackMaster rackMasterObject = gson.fromJson(RackMasterObj, new TypeToken<RackMaster>() {}.getType());
			rackMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			rackMasterObject.setCompanyId(userInfo.getCompanyId());
			rackMasterObject.setStoreId(userInfo.getStoreId());
			rackMasterObject.setCreatedBy(userInfo.getId());
			rackMasterObject.setLang(lang);
			String res=invSetUpService.addrack(rackMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editrack",method=RequestMethod.POST)
	public void editrack(@RequestBody String RackMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editrack......{}",RackMasterObj.toString());
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
			RackMaster rackMasterObject = gson.fromJson(RackMasterObj, new TypeToken<RackMaster>() {}.getType());
			rackMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			rackMasterObject.setCompanyId(userInfo.getCompanyId());
			rackMasterObject.setStoreId(userInfo.getStoreId());
			rackMasterObject.setUpdatedBy(userInfo.getId());
			rackMasterObject.setLang(lang);
			String res=invSetUpService.editrack(rackMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteRack/{id}",method=RequestMethod.GET)
	public void deleteRack(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteRack......{}",id);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteRack(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/group",method=RequestMethod.GET)
	public ModelAndView loadGroup(Model model,HttpSession session)
	{
		//logger.info("In groupsetup......");
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
		List<GroupMaster> allGroups=invSetUpService.getAllGroup(userInfo.getCompanyId(),lang);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.GROUP_SETUP_201H, lang);
		//System.out.println("res:"+allGroups.toString());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.GROUP);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allGroups",allGroups);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_GROUP_PAGE);
		return mav;
	}

	@RequestMapping(value="/addgroup",method=RequestMethod.POST)
	public void addgroup(@RequestBody String groupMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addgroup......{}",groupMasterObj.toString());
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
			GroupMaster groupMasterObject = gson.fromJson(groupMasterObj, new TypeToken<GroupMaster>() {}.getType());
			groupMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			groupMasterObject.setCompanyId(userInfo.getCompanyId());
			groupMasterObject.setCreatedBy(userInfo.getId());
			groupMasterObject.setLang(lang);
			String res=invSetUpService.addgroup(groupMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editgroup",method=RequestMethod.POST)
	public void editgroup(@RequestBody String groupMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editgroup......{}",groupMasterObj.toString());
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
			GroupMaster groupMasterObject = gson.fromJson(groupMasterObj, new TypeToken<GroupMaster>() {}.getType());
			groupMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			groupMasterObject.setCompanyId(userInfo.getCompanyId());
			groupMasterObject.setUpdatedBy(userInfo.getId());
			groupMasterObject.setLang(lang);
			String res=invSetUpService.editgroup(groupMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteGroup/{id}",method=RequestMethod.GET)
	public void deleteGroup(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteGroup......{}",id);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteGroup(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/schedule",method=RequestMethod.GET)
	public ModelAndView loadSchedule(Model model,HttpSession session)
	{
		//logger.info("In schedulesetup......");
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
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.SCHEDULE_SETUP_201I, lang);
		List<ScheduleMaster> allSchedules=invSetUpService.getAllSchedule(userInfo.getCompanyId(),lang);
		//System.out.println("res:"+allSchedules.toString());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.SCHEDULE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allSchedules",allSchedules);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_SCHEDULE_PAGE);
		return mav;
	}

	@RequestMapping(value="/addSchedule",method=RequestMethod.POST)
	public void addSchedule(@RequestBody String ScheduleMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addschedule......{}",ScheduleMasterObj.toString());
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
			ScheduleMaster scheduleMasterObject = gson.fromJson(ScheduleMasterObj, new TypeToken<ScheduleMaster>() {}.getType());
			scheduleMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			scheduleMasterObject.setCompanyId(userInfo.getCompanyId());
			scheduleMasterObject.setCreatedBy(userInfo.getId());
			scheduleMasterObject.setLang(lang);
			String res=invSetUpService.addSchedule(scheduleMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editSchedule",method=RequestMethod.POST)
	public void editSchedule(@RequestBody String ScheduleMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editSchedule......{}",ScheduleMasterObj.toString());
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
			ScheduleMaster scheduleMasterObject = gson.fromJson(ScheduleMasterObj, new TypeToken<ScheduleMaster>() {}.getType());
			scheduleMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			scheduleMasterObject.setCompanyId(userInfo.getCompanyId());
			scheduleMasterObject.setUpdatedBy(userInfo.getId());
			scheduleMasterObject.setLang(lang);
			String res=invSetUpService.editSchedule(scheduleMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteSchedule/{id}",method=RequestMethod.GET)
	public void deleteSchedule(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteGroup......{}",id);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = invSetUpService.deleteSchedule(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/itemsearching",method=RequestMethod.GET)
	public ModelAndView loadItemsearching(Model model,HttpSession session)
	{
		//logger.info("In itemsearching......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.ITEM_SEARCH);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_ITEMSEARCHING_PAGE);
		return mav;
	}

	@RequestMapping(value="/getcountrylist",method=RequestMethod.POST)
	public void getCountryList(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getAllCountryList(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getStateByCountry",method=RequestMethod.POST)
	public void getStateByCountry(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getAllStateByCountry(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getCityByState",method=RequestMethod.POST)
	public void getCityByState(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getAllCityByState(commonResultSetMapper);
			///System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/city",method=RequestMethod.GET)
	public ModelAndView openCity(Model model,HttpSession session)
	{
		//logger.info("In city......");
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
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		//System.out.println("countryMasters="+countryMasters.toString());
		mav.addObject("countryMasters",countryMasters);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CITY_SETUP_201P, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CITY);
		mav.addObject("menuselect",menuselect);
		mav.addObject("userinformation", userInfo);
		mav.setViewName(ForwardConstants.VIEW_CITY_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/addcity",method=RequestMethod.POST)
	public void addCity(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addCity......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setCreatedBy(userInfo.getId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.addCity(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editcity",method=RequestMethod.POST)
	public void editCity(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In ecity......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.editCity(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteCity",method=RequestMethod.POST)
	public void deleteCity(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.deleteCity(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/searchcity",method=RequestMethod.POST)
	public ModelAndView searchCity(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchCity......{},{},{}",commonResultSetMapper.toString());
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
		List<CityDTO> cityDTOs=invSetUpService.searchCity(commonResultSetMapper);
		//System.out.println("cityDTOs="+cityDTOs.size());
		mav.addObject("allcity",cityDTOs);
		CommonResultSetMapper commonResultSetMapper1 = new CommonResultSetMapper();
		commonResultSetMapper1.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper1.setStoreId(userInfo.getStoreId());
		commonResultSetMapper1.setLang(lang);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper1), new TypeToken<List<CountryMaster>>() {}.getType());
		//System.out.println("countryMasters="+countryMasters.toString());
		mav.addObject("countryMasters",countryMasters);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CITY_SETUP_201P, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CITY);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_CITY_PAGE);
		return mav;
	}

	@RequestMapping(value="/getcitybyid",method=RequestMethod.POST)
	public void getCityById(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getCityById(commonResultSetMapper);
		//	System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/zone",method=RequestMethod.GET)
	public ModelAndView openZone(Model model,HttpSession session)
	{
		//logger.info("In zone......");
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
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		//System.out.println("countryMasters="+countryMasters.toString());
		mav.addObject("countryMasters",countryMasters);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ZONE_SETUP_201Q, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ZONE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("userinformation", userInfo);/*Sayantan Mahanty added date-19/02/2020*/
		mav.setViewName(ForwardConstants.VIEW_ZONE_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/searchzone",method=RequestMethod.POST)
	public ModelAndView searchZone(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchzone......{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setLang(lang);
		List<ZoneDTO> allZones=invSetUpService.getAllZoneList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ZONE_SETUP_201Q, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ZONE);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allZones",allZones);
		mav.addObject("countryMasters",countryMasters);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ZONE_PAGE);
		return mav;
	}

	@RequestMapping(value="/addzone",method=RequestMethod.POST)
	public void addZone(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addZone......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setCreatedBy(userInfo.getId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.addZone(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editzone",method=RequestMethod.POST)
	public void editZone(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In edit zone......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.editZone(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deletezone",method=RequestMethod.POST)
	public void deleteZone(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.deleteZone(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getzonebyid",method=RequestMethod.POST)
	public void getZoneById(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getZoneById......{}",commonresultsetmapperObj);
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			ZoneDTO zoneMasterObject = invSetUpService.getZoneById(commonResultSetMapper);
			//System.out.println("res:"+zoneMasterObject.toString());
			out.print(zoneMasterObject.toString());
			out.flush();
		}
	}

	@RequestMapping(value="/getzonebycity",method=RequestMethod.POST)
	public void getZoneByCity(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getAllZoneByCity(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/area",method=RequestMethod.GET)
	public ModelAndView openArea(Model model,HttpSession session)
	{
		//logger.info("In area......");
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
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		//System.out.println("countryMasters="+countryMasters.toString());
		mav.addObject("countryMasters",countryMasters);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.AREA_SETUP_201R, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.AREA);
		mav.addObject("menuselect",menuselect);
		mav.addObject("userinformation", userInfo);/*Sayantan Mahanty added date-19/02/2020*/
		mav.setViewName(ForwardConstants.VIEW_AREA_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/searcharea",method=RequestMethod.POST)
	public ModelAndView searchArea(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchArea......{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setLang(lang);
		List<AreaDTO> allAreas=invSetUpService.getAllAreaList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ZONE_SETUP_201Q, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.AREA);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allAreas",allAreas);
		mav.addObject("countryMasters",countryMasters);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_AREA_PAGE);
		return mav;
	}

	@RequestMapping(value="/addarea",method=RequestMethod.POST)
	public void addArea(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addArea......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setCreatedBy(userInfo.getId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.addArea(commonResultSetMapper);
		//	System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editarea",method=RequestMethod.POST)
	public void editArea(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In edit area......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.editArea(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deletearea",method=RequestMethod.POST)
	public void deleteArea(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.deleteArea(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getAreabyId",method=RequestMethod.POST)
	public void getAreaById(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getZoneById......{}",commonresultsetmapperObj);
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			AreaDTO areaMasterObject = invSetUpService.getAreaById(commonResultSetMapper);
			//System.out.println("res:"+areaMasterObject.toString());
			out.print(areaMasterObject.toString());
			out.flush();
		}
	}

	@RequestMapping(value="/salesman",method=RequestMethod.GET)
	public ModelAndView loadSalesMan(Model model,HttpSession session)
	{
		//logger.info("In loadSalesMan......");
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
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getId());
		commonResultSetMapper.setLang(lang);
		List<CountryMaster> countryMasters = gson.fromJson(invSetUpService.getAllCountryList(commonResultSetMapper), new TypeToken<List<CountryMaster>>() {}.getType());
		//System.out.println("countryMasters="+countryMasters.toString());
		mav.addObject("countryMasters",countryMasters);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.SALESMAN_SETUP_201S, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.SALESMAN);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALESMAN_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/loadchargemaster",method=RequestMethod.GET)
	public ModelAndView loadChargeMaster(Model model,HttpSession session)
	{
		//logger.info("In loadchargemaster......");
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

		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CHARGEMASTER_SETUP_201T, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CHARGEMASTER);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_CHARGEMASTER_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/createChargeMaster",method=RequestMethod.POST)
	public void createChargeMaster(@RequestBody String ChargeMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In ChargeMaster......{}",ChargeMasterObj.toString());
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
			ChargeMaster chargeMasterObject = gson.fromJson(ChargeMasterObj, new TypeToken<ChargeMaster>() {}.getType());
			chargeMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			chargeMasterObject.setCompanyId(userInfo.getCompanyId());
			chargeMasterObject.setStoreId(userInfo.getStoreId());
			chargeMasterObject.setCreatedBy(userInfo.getId());
			chargeMasterObject.setLang(lang);
			String res=invSetUpService.createChargeMaster(chargeMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editChargeMaster",method=RequestMethod.POST)
	public void editChargeMaster(@RequestBody String ChargeMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editChargeMaster......{}",ChargeMasterObj.toString());
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
			ChargeMaster chargeMasterObject = gson.fromJson(ChargeMasterObj, new TypeToken<ChargeMaster>() {}.getType());
			chargeMasterObject.setUpdatedDate(DateUtil.getCurrentDate());
			chargeMasterObject.setCompanyId(userInfo.getCompanyId());
			chargeMasterObject.setStoreId(userInfo.getStoreId());
			chargeMasterObject.setUpdatedBy(userInfo.getId());
			chargeMasterObject.setLang(lang);
			String res=invSetUpService.editChargeMaster(chargeMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getChargeMasterId",method=RequestMethod.POST)
	public void getChargeMasterId(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getChargeMasterId......{}",commonresultsetmapperObj);
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String areaMasterObject = invSetUpService.getChargeMasterId(commonResultSetMapper);
			//System.out.println("res:"+areaMasterObject.toString());
			out.print(areaMasterObject.toString());
			out.flush();
		}
	}

	@RequestMapping(value="/deletechargemaster",method=RequestMethod.POST)
	public void deleteChargeMaster(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteChargeMaster......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.deleteChargeMaster(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getAllChargeMaster",method=RequestMethod.POST)
	public void getAllChargeMaster(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getAllChargeMaster(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/searchchargemaster",method=RequestMethod.POST)
	public ModelAndView searchChargeMaster(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchchargemaster......{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setLang(lang);
		List<ChargeMaster> chargeMasters = gson.fromJson(invSetUpService.getAllChargeMaster(commonResultSetMapper), new TypeToken<List<ChargeMaster>>() {}.getType());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ZONE_SETUP_201Q, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CHARGEMASTER);
		mav.addObject("menuselect",menuselect);
		mav.addObject("chargeMasters",chargeMasters);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.addObject("commonResultSetMapper",commonResultSetMapper);
		mav.setViewName(ForwardConstants.VIEW_CHARGEMASTER_PAGE);
		return mav;
	}

	@RequestMapping(value="/loadvariantmaster",method=RequestMethod.GET)
	public ModelAndView loadSizeMaster(Model model,HttpSession session)
	{
		//logger.info("In loadvariantmaster......");
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
		CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<VariantTypeDTO> variantTypeDTOs = gson.fromJson(invSetUpService.getVariantType(commonResultSetMapper), new TypeToken<List<VariantTypeDTO>>() {}.getType());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.SIZEMASTER_SETUP_201U, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.SIZEMASTER);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SIZEMASTER_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.addObject("variantTypeDTOs",variantTypeDTOs);
		return mav;
	}

	@RequestMapping(value="/createVarientMaster",method=RequestMethod.POST)
	public void createVarientMaster(@RequestBody String VarientMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In createVarientMaster......{}",VarientMasterObj.toString());
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
			VariantMaster variantMaster = gson.fromJson(VarientMasterObj, new TypeToken<VariantMaster>() {}.getType());
			variantMaster.setCompanyId(userInfo.getCompanyId());
			variantMaster.setStoreId(userInfo.getStoreId());
			variantMaster.setCreatedBy(userInfo.getId());
			variantMaster.setLang(lang);
			String res=invSetUpService.createVarientMaster(variantMaster);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editVariantMaster",method=RequestMethod.POST)
	public void editVariantMaster(@RequestBody String VariantMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In editVariantMaster......{}",VariantMasterObj.toString());
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
			VariantMaster variantMasterObject = gson.fromJson(VariantMasterObj, new TypeToken<VariantMaster>() {}.getType());
			variantMasterObject.setCompanyId(userInfo.getCompanyId());
			variantMasterObject.setStoreId(userInfo.getStoreId());
			variantMasterObject.setUpdatedBy(userInfo.getId());
			variantMasterObject.setLang(lang);
			String res=invSetUpService.editVariantMaster(variantMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/searchvariantmaster",method=RequestMethod.POST)
	public ModelAndView searchVariantMaster(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchvariantmaster......{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setLang(lang);
		List<VariantDTO> variantDTOs = gson.fromJson(invSetUpService.getAllVariantMaster(commonResultSetMapper), new TypeToken<List<VariantDTO>>() {}.getType());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.SIZEMASTER_SETUP_201U, lang);
		CommonResultSetMapper commonResultSetMapper1=new CommonResultSetMapper();
		commonResultSetMapper1.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper1.setStoreId(userInfo.getStoreId());
		commonResultSetMapper1.setLang(lang);
		List<VariantTypeDTO> variantTypeDTOs = gson.fromJson(invSetUpService.getVariantType(commonResultSetMapper1), new TypeToken<List<VariantTypeDTO>>() {}.getType());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.SIZEMASTER);
		mav.addObject("menuselect",menuselect);
		mav.addObject("variantDTOs",variantDTOs);
		mav.addObject("variantTypeDTOs",variantTypeDTOs);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.addObject("commonResultSetMapper",commonResultSetMapper);
		mav.setViewName(ForwardConstants.VIEW_SIZEMASTER_PAGE);
		return mav;
	}

	@RequestMapping(value="/getVariantMasterId",method=RequestMethod.POST)
	public void getVariantMasterId(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getVariantMasterId......{}",commonresultsetmapperObj);
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String areaMasterObject = invSetUpService.getVariantMasterId(commonResultSetMapper);
			//System.out.println("res:"+areaMasterObject.toString());
			out.print(areaMasterObject.toString());
			out.flush();
		}
	}

	@RequestMapping(value="/deletevariantmaster",method=RequestMethod.POST)
	public void deleteVariantMaster(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deletevariantmaster......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.deleteVariantMaster(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getallretailtypestorewise",method=RequestMethod.POST)
	public void getAllRetailTypeStorewise(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getAllRetailTypeStorewise(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getallselectedmenubyretailtype",method=RequestMethod.POST)
	public void getAllSelectedMenuByRetailType(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapperMenu = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapperMenu.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapperMenu.setStoreId(userInfo.getStoreId());
			commonResultSetMapperMenu.setRetailTypeID(userInfo.getRetailTypeId());
			commonResultSetMapperMenu.setMenuID(RoleBasedCostants.ITEM_SETUP_201J);
			commonResultSetMapperMenu.setLang(lang);
			String res=invSetUpService.getAllSelectedMenuByRetailType(commonResultSetMapperMenu);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getCatBySubcat",method=RequestMethod.POST)
	public void getCatBySubcat(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj......{}",commonresultsetmapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getId());
			commonResultSetMapper.setLang(lang);
			String res=invSetUpService.getCatBySubCat(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

}
