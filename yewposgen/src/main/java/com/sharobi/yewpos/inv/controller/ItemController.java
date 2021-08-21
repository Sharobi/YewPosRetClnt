package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.MarketerMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.RetailTypeControlDTO;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.TaxTypeDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sharobi.yewpos.util.CommonResource;

/**
 * @author Manodip
 *
 */

@Controller
@RequestMapping("/item")
public class ItemController {
	private final static Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	 ItemService itemService;
	@Autowired
	 RoleService roleService;
	@Autowired
	 InvSetupService invSetUpService;
	 Gson gson = new Gson();

	@RequestMapping(value = "/loaditem",
					method = RequestMethod.GET)
	public ModelAndView loadItem(	Model model,
									HttpSession session) {
		//logger.info("In itemsetup......");
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
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		return mav;
	}


	/*
	 *
	 * add new item
	 * @param itemid
	*/
	@RequestMapping(value = "/loaditemmst/{itemid}",
					method = RequestMethod.GET)
	public ModelAndView loadItemMaster(	@PathVariable("itemid") Integer itemid,Model model,HttpSession session) {
		//logger.info("In itemsetup......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		/*
		 * for page redirect
		 */
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}



		if (itemid != 0) {// get item details for edit
			ItemMaster itemMaster = itemService.getItemDetailsById(itemid, lang);
			mav.addObject("itemMaster", itemMaster);

		}

		/*
		 *  add on 3_11_2017
		 *
		 * */
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
//		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		List<TaxTypeDTO> alltaxtypeexclusive =itemService.getalltaxtype_is_exclusive(commonResultSetMapper);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<CategoryMaster> allCat=invSetUpService.getAllCat(userInfo.getCompanyId(),lang);

		CommonResultSetMapper commonResultSetMapperMenu = new CommonResultSetMapper();
		commonResultSetMapperMenu.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapperMenu.setStoreId(userInfo.getStoreId());
		commonResultSetMapperMenu.setRetailTypeID(userInfo.getRetailTypeId());
		commonResultSetMapperMenu.setMenuID(RoleBasedCostants.ITEM_SETUP_201J);
		commonResultSetMapperMenu.setLang(lang);
		List<RetailTypeControlDTO> retailTypeControlDTOs=gson.fromJson(invSetUpService.getAllSelectedMenuByRetailType(commonResultSetMapperMenu), new TypeToken<List<RetailTypeControlDTO>>() {}.getType());
		//System.out.println("res:"+retailTypeControlDTOs.toString());

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
//userInfo.getIsExclusive();
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("allCat", allCat);
		mav.addObject("result", "");
		mav.addObject("add_edit_delete", "");
		mav.addObject("item_id",itemid);
		mav.addObject("alltaxtypelist", alltaxtypeexclusive);
		mav.addObject("retailTypeControlDTOs", retailTypeControlDTOs);
		mav.addObject("re", 1);
		mav.addObject("retailerProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("wholesaleProfitPrcnt",
				CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_WHOLESALER));
		mav.setViewName(ForwardConstants.VIEW_ITEM_MASTER_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getitemdetailsmod/{itemid}",
			method = RequestMethod.GET)
	public ModelAndView loadItemMaster(	@PathVariable("itemid") Integer itemid,
									Model model,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.info("In getitemdetails......");
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
		String itemDetails = itemService.getItemDetailsByIdForEdit(itemid, lang);
		//System.out.println("In getitemdetails in edit item modal :" + itemDetails);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.print(itemDetails);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/checkSameItemExists/{name}/{itemid}",
					method = RequestMethod.GET)
	public void checkSameItemExists(@PathVariable("name") String name,@PathVariable("itemid") Integer itemid,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.info("In checkSameItemExists......{},{}", name,itemid);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String val = itemService.checkSameItemExist(name.trim(),itemid, lang);
			//System.out.println("res:" + val);
			out.print(val);
			out.flush();
		}
	}

	@RequestMapping(value = "/addorupdateitem",
					method = RequestMethod.POST)
	public ModelAndView addorUpdateItem(@ModelAttribute("itemMaster") ItemMaster itemMaster,
										BindingResult result,
										HttpSession session,
										HttpServletResponse response) throws IOException {


		//System.out.println(itemMaster.getLaunchDate()+"  "+itemMaster.getDiscontinueDate());
		//logger.info("In addorUpdateItem......{},{},{}", itemMaster.toString());
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String add_edit_delete = "";
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		itemMaster.setRetailTypeId(userInfo.getRetailTypeId());
		itemMaster.setCompanyId(userInfo.getCompanyId());
		String s;
		ResponseObj responseObj = new ResponseObj();
		if (itemMaster.getId() == 0) {
			itemMaster.setCreatedBy(userInfo.getId());
			responseObj = itemService.addItem(itemMaster);
			add_edit_delete = "add";
		} else {
			//System.out.println("inside UpdateItem");
			itemMaster.setUpdatedBy(userInfo.getId());
			responseObj = itemService.updateItem(itemMaster);
			add_edit_delete = "edit";
		}
		//System.out.println("response obj=" + responseObj.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		mav.addObject("result", responseObj.getId());
		mav.addObject("add_edit_delete", add_edit_delete);
		return mav;
	}

	@RequestMapping(value="/searchitem",method=RequestMethod.POST)
	public ModelAndView searchItem(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchItem......{},{},{}",commonResultSetMapper.toString());
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
		List<CommonResultSetMapper> commonResultSetMappers=itemService.searchItem(commonResultSetMapper);
		/*System.out.println("commonResultSetMappers="+commonResultSetMappers.size());*/
		String searchCriteria=commonResultSetMapper.getSearchCriteria();
		mav.addObject("searchCriteria",searchCriteria);
		mav.addObject("allItems",commonResultSetMappers);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		return mav;
	}

	@RequestMapping(value="/deleteItem/{id}",method=RequestMethod.GET)
	public void deleteItem(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteBrand......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setItemId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			String res = itemService.deleteItem(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/autocompleteitem", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemBrandAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In autocompleteitem......{}", tagName);
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
		CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		commonResultSetMapper.setItemName(tagName.trim());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CommonResultSetMapper> commonResultSetMappers=itemService.searchItemAutoComplete(commonResultSetMapper);
		//System.out.println("autocompleteitem List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}
	@RequestMapping(value="/getalternatemedicine",method=RequestMethod.POST)
	public void getAlternateMed(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getAlternateMed......{}",commonResultsetMapperObj.toString());
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
			String res=itemService.getAlternateMedicine(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/addnewitem",method=RequestMethod.POST)
	public void addNewItem(@RequestBody String itemMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addNewItem in itemmaster......{}",itemMasterObj.toString());
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
			ItemMaster itemMaster= gson.fromJson(itemMasterObj, new TypeToken<ItemMaster>() {}.getType());
			String res = "";
			itemMaster.setCompanyId(userInfo.getCompanyId());
			itemMaster.setRetailTypeId(userInfo.getRetailTypeId());
			itemMaster.setLang(lang);
			if (itemMaster.getId() == 0) {
				itemMaster.setCreatedBy(userInfo.getId());
				res = itemService.addItemViaAjax(itemMaster);
			} else {
				//System.out.println("inside UpdateItem");
				itemMaster.setUpdatedBy(userInfo.getId());
				res = itemService.updateItemViaAjax(itemMaster);
			}

			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/*================================= Search Items by Content ======================== */

	@RequestMapping(value="/getitemsbycontent/{contentid}",method=RequestMethod.GET)
	public void getItemsByContent(@PathVariable int contentid,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getcurrstock......{}",contentid);
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

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setContentId(contentid);
			commonResultSetMapper.setLang(lang);
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = df.format(c.getTime());
			//System.out.println("Date ::" + formattedDate);
			commonResultSetMapper.setAsOnDate(formattedDate);
			String res=itemService.getItemsByContent(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	/*========================================== End ====================================*/

	@RequestMapping(value="/getitemhistory",method=RequestMethod.POST)
	public void getItemHistory(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getitemhistory......{}",commonResultsetMapperObj.toString());
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
			String res=itemService.getItemHistory(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getitembybarcode",method=RequestMethod.POST)
	public void getItemByBarcode(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getItemByBarcode......{}",commonResultsetMapperObj.toString());
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
//			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=itemService.getItemByBarcode(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}


	/*
	 *  add on_11_02_2017
	 * */

	@RequestMapping(value = "/autocompletemarketer", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getAutoCompleteMarketer(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In getAutoCompleteMarketer......{}", tagName);
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

		MarketerMaster marketermaster = new MarketerMaster();
		marketermaster.setName(tagName);
		//
		/*CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		commonResultSetMapper.setItemName(tagName.trim());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);*/
		List<CommonResultSetMapper> commonResultSetMappers=itemService.autoCompleteSearchMarketerName(marketermaster);
		//System.out.println("autocompleteitem List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}

	@RequestMapping(value="/getitemcontrolbyitem",method=RequestMethod.POST)
	public void getItemControlByItem(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getItemControlByItem......{}",commonResultsetMapperObj.toString());
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
//			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=itemService.getItemControlByItem(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getalllocation",method=RequestMethod.POST)
	public void getAllLocation(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getAllLocation......{}",commonResultsetMapperObj.toString());
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
//			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=itemService.getAllLocation(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}



	/*================================= Search Items by Content ======================== */

	@RequestMapping(value="/getitemsbymanufacturer/{manuid}",method=RequestMethod.GET)
	public void getItemsByManufacture(@PathVariable int manuid,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getItemsByManufacture......{}",manuid);
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

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setManuId(manuid);
			commonResultSetMapper.setLang(lang);
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = df.format(c.getTime());
			//System.out.println("Date ::" + formattedDate);
			commonResultSetMapper.setAsOnDate(formattedDate);
			String res=itemService.getItemsByManufacturer(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
    /*New added 7.5.2019*/
	
	@RequestMapping(value = "/autocompleteitembycode", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemAutocompleteListByCode(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.info("In autocompleteitem......{}", tagName);
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
		CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		commonResultSetMapper.setItemCode(tagName.trim());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CommonResultSetMapper> commonResultSetMappers=itemService.searchItemAutoCompleteByCode(commonResultSetMapper);
		//System.out.println("autocompleteitem List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}
	/*========================================== End ====================================*/

}
