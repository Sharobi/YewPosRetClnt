/**
 *
 */
package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.AreaDTO;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.ChargeMaster;
import com.sharobi.yewpos.inv.model.CityDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.DoctorMaster;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ReturnReasonTypeMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.SubCategoryMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.model.VariantMaster;
import com.sharobi.yewpos.inv.model.ZoneDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib,Manodip,SIDDIK
 *
 */

@Service
public class InvSetupService {

	private final static Logger logger=LoggerFactory.getLogger(InvSetupService.class);
	  Gson gson = new Gson();


	public String addUnit(UnitMaster UnitMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_UNIT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(UnitMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addUnit ",ex);
			return null;
		}
	}


	public List<UnitMaster> getAllUnit(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_UNIT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all units: {}", responseString);
			List<UnitMaster> UnitMasterList = new ArrayList<UnitMaster>();
			UnitMasterList = gson.fromJson(responseString, new TypeToken<List<UnitMaster>>(){}.getType());
			return UnitMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllUnit",ex);
			return null;
		}

	}

	public UnitMaster getUnitById(int id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET__UNIT_BY_ID).replace("{1}", String.valueOf(id).replace("{2}", lang));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get unit by Id: {}", responseString);
			UnitMaster UnitMasterobj = gson.fromJson(responseString, new TypeToken<UnitMaster>() {}.getType());
			return UnitMasterobj;
		}catch(Exception ex)
		{
			logger.info("Exception in getUnitById ",ex);
			return null;
		}

	}

	public String editUnit(UnitMaster UnitMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_UNIT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(UnitMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editUnit ",ex);
			return null;
		}
	}

	public String deleteUnit(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_UNIT).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete unit: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteUnit ",ex);
			return null;
		}

	}

	public List<UnitMaster> searchUnitAutoComplete(UnitMaster unitMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_UNIT_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(unitMaster));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" +responseString);
			List<UnitMaster> unitMasters = new ArrayList<UnitMaster>();
			unitMasters = gson.fromJson(responseString, new TypeToken<List<UnitMaster>>() {}.getType());
			return unitMasters;
			} catch (Exception ex) {
			logger.info("Exception in searchUnitAutoComplete ", ex);
			return null;
		}
	}

	public List<CategoryMaster> getAllCat(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_CAT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all categories: {}", responseString);
			List<CategoryMaster> CatMasterList = new ArrayList<CategoryMaster>();
			CatMasterList = gson.fromJson(responseString, new TypeToken<List<CategoryMaster>>(){}.getType());
			//System.out.println("return result: "+CatMasterList.toString());
			return CatMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllCat ",ex);
			return null;
		}

	}

	public String getAllCatJson(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_CAT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all categories: {}", responseString);
			/*List<CategoryMaster> CatMasterList = new ArrayList<CategoryMaster>();
			CatMasterList = gson.fromJson(responseString, new TypeToken<List<CategoryMaster>>(){}.getType());
			System.out.println("return result: "+CatMasterList.toString());*/
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllCatJson ",ex);
			return null;
		}

	}


	public String editCat(CategoryMaster CategoryMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_CAT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CategoryMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editCat ",ex);
			return null;
		}
	}

	public String deleteCat(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_CAT).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete category: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteCat",ex);
			return null;
		}

	}

	public String addCat(CategoryMaster CategoryMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_CAT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CategoryMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addCat ",ex);
			return null;
		}
	}

	public CategoryMaster getCatDetailsById(int cat_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CATDETAILS_BY_ID).replace("{1}", String.valueOf(cat_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get category details by id: {}", responseString);
			CategoryMaster CategoryMasterObj = gson.fromJson(responseString, new TypeToken<CategoryMaster>() {}.getType());
			return CategoryMasterObj;
		}catch(Exception ex)
		{
			logger.info("Exception in getCatDetailsById ",ex);
			return null;
		}

	}

	public List<SubCategoryMaster> getAllSubCat(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_SUBCAT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all subcategories: {}", responseString);
			List<SubCategoryMaster> SubCatMasterList = new ArrayList<SubCategoryMaster>();
			SubCatMasterList = gson.fromJson(responseString, new TypeToken<List<SubCategoryMaster>>(){}.getType());
			return SubCatMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllSubCat ",ex);
			return null;
		}

	}

	public String addsubcat(SubCategoryMaster SubCategoryMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_SUBCAT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(SubCategoryMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addsubcat ",ex);
			return null;
		}
	}

	public String editsubcat(SubCategoryMaster SubCategoryMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_SUBCAT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(SubCategoryMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editsubcat ",ex);
			return null;
		}
	}

	public String deleteSubCat(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_SUBCAT).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete subcategory: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteSubCat ",ex);
			return null;
		}

	}

	public List<DoctorMaster> getAllDctrs(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_DOCTOR).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all doctors: {}", responseString);
			List<DoctorMaster> doctorMasterList = new ArrayList<DoctorMaster>();
			doctorMasterList = gson.fromJson(responseString, new TypeToken<List<DoctorMaster>>(){}.getType());
			//System.out.println("return result: "+doctorMasterList.toString());
			return doctorMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllDctrs ",ex);
			return null;
		}

	}

	public String adddocctr(DoctorMaster doctorMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_DOCTOR);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(doctorMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in adddocctr ",ex);
			return null;
		}
	}

	public String editdoctor(DoctorMaster DoctorMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_DOCTOR);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(DoctorMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editdoctor ",ex);
			return null;
		}
	}

	public DoctorMaster getDoctorbyId(int id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_DOCTOR_BY_ID).replace("{1}", String.valueOf(id).replace("{2}", lang));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get doctor by Id: {}", responseString);
			DoctorMaster DoctorMasterobj = gson.fromJson(responseString, new TypeToken<DoctorMaster>() {}.getType());
			return DoctorMasterobj;
		}catch(Exception ex)
		{
			logger.info("Exception in getDoctorbyId ",ex);
			return null;
		}

	}

	public String deleteDoctor(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_DOCTOR).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Doctor: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteDoctor ",ex);
			return null;
		}

	}

	public String getDoctorListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_DOCTOR_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
//			customerMasters = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getDoctorListAutocomplete ", ex);
			return null;
		}
	}

	public List<RackMaster> getAllRack(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_RACK).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all racks: {}", responseString);
			List<RackMaster> rackMasterList = new ArrayList<RackMaster>();
			rackMasterList = gson.fromJson(responseString, new TypeToken<List<RackMaster>>(){}.getType());
			//System.out.println("return result: "+rackMasterList.toString());
			return rackMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllRack ",ex);
			return null;
		}

	}

	public String addrack(RackMaster RackMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_RACK);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(RackMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addrack ",ex);
			return null;
		}
	}

	public String editrack(RackMaster RackMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_RACK);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(RackMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editrack ",ex);
			return null;
		}
	}

	public String deleteRack(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_RACK).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Rack: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteRack ",ex);
			return null;
		}

	}

	public List<GroupMaster> getAllGroup(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_GROUPS).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all groups: {}", responseString);
			List<GroupMaster> groupMasterList = new ArrayList<GroupMaster>();
			groupMasterList = gson.fromJson(responseString, new TypeToken<List<GroupMaster>>(){}.getType());
			//System.out.println("return result: "+groupMasterList.toString());
			return groupMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllGroup ",ex);
			return null;
		}

	}

	public String addgroup(GroupMaster groupMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_GROUP);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(groupMasterObject));
			String responseString=response.getEntity(String.class);
			///logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addgroup ",ex);
			return null;
		}
	}

	public String editgroup(GroupMaster groupMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_GROUP);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(groupMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editgroup ",ex);
			return null;
		}
	}

	public String deleteGroup(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_GROUP).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Group: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteGroup ",ex);
			return null;
		}

	}

	public List<ScheduleMaster> getAllSchedule(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_SCHEDULES).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all Schedules: {}", responseString);
			List<ScheduleMaster> ScheduleMasterList = new ArrayList<ScheduleMaster>();
			ScheduleMasterList = gson.fromJson(responseString, new TypeToken<List<ScheduleMaster>>(){}.getType());
			//System.out.println("return result: "+ScheduleMasterList.toString());
			return ScheduleMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllSchedule ",ex);
			return null;
		}

	}

	public String addSchedule(ScheduleMaster scheduleMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_SCHEDULE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(scheduleMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addSchedule ",ex);
			return null;
		}
	}

	public String editSchedule(ScheduleMaster scheduleMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_SCHEDULE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(scheduleMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editSchedule ",ex);
			return null;
		}
	}

	public String deleteSchedule(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_SCHEDULE).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Schedule: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteSchedule ",ex);
			return null;
		}

	}


	public List<ReturnReasonTypeMaster> getAllReturnReasonType(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_RETURNREASONTYPE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all ReturnReasonType: {}", responseString);
			List<ReturnReasonTypeMaster> ReturnReasonTypeMasterList = new ArrayList<ReturnReasonTypeMaster>();
			ReturnReasonTypeMasterList = gson.fromJson(responseString, new TypeToken<List<ReturnReasonTypeMaster>>(){}.getType());
			return ReturnReasonTypeMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllReturnReasonType ",ex);
			return null;
		}

	}

	public String getAllCountryList(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLCOUNTRY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			////logger.info("Response string in getAllCountryList: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllCountryList ",ex);
			return null;
		}

	}

	public String getAllStateByCountry(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLSTATE_BYCOUNTRY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			////logger.info("Response string in getAllStateByCountry: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllStateByCountry ",ex);
			return null;
		}

	}

	public String getAllCityByState(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLCITY_BYSTATE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			///logger.info("Response string in getAllCityByState: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllCityByState ",ex);
			return null;
		}

	}

	public String addCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_CITY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addCity",ex);
			return null;
		}
	}

	public String editCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_CITY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editCity ",ex);
			return null;
		}
	}

	public String deleteCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_CITY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in deleteCity ",ex);
			return null;
		}
	}

	public List<CityDTO> searchCity(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_CITYLIST_BYNAME);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
			cityDTOs = gson.fromJson(responseString, new TypeToken<List<CityDTO>>() {}.getType());
			return cityDTOs;
		} catch (Exception ex) {
			logger.info("Exception in searchCity ", ex);
			return null;
		}
	}

	public String getCityById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_CITY_BYID);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in getCityById: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getCityById ",ex);
			return null;
		}

	}

	public List<ZoneDTO> getAllZoneList(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_ZONE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get all zone: {}", responseString);
			List<ZoneDTO> zoneMasterList = new ArrayList<ZoneDTO>();
			zoneMasterList = gson.fromJson(responseString, new TypeToken<List<ZoneDTO>>() {}.getType());
			return zoneMasterList;
		} catch (Exception ex) {
			logger.info("Exception in getAllZoneList ", ex);
			return null;
		}

	}

	public String addZone(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_ZONE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addZone ",ex);
			return null;
		}
	}

	public String editZone(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_ZONE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editZone ",ex);
			return null;
		}
	}

	public String deleteZone(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_ZONE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in deleteZone ",ex);
			return null;
		}
	}

	public ZoneDTO getZoneById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ZONE_BYID);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get zone by Id: {}", responseString);
			ZoneDTO zoneMasterobj = gson.fromJson(responseString, new TypeToken<ZoneDTO>() {}.getType());
			return zoneMasterobj;
		}catch(Exception ex)
		{
			logger.info("Exception in getZoneById ",ex);
			return null;
		}

	}

	public String getAllZoneByCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLZONE_BYCITY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in getAllCityByState: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllZoneByCity ",ex);
			return null;
		}

	}

	public List<AreaDTO> getAllAreaList(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_AREA);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get all area: {}", responseString);
			List<AreaDTO> areaMasterList = new ArrayList<AreaDTO>();
			areaMasterList = gson.fromJson(responseString, new TypeToken<List<AreaDTO>>() {}.getType());
			return areaMasterList;
		} catch (Exception ex) {
			logger.info("Exception in getAllAreaList ", ex);
			return null;
		}

	}

	public String addArea(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_AREA);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addArea ",ex);
			return null;
		}
	}

	public String editArea(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_AREA);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editArea ",ex);
			return null;
		}
	}

	public String deleteArea(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_AREA);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editArea ",ex);
			return null;
		}
	}

	public AreaDTO getAreaById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_AREA_BYID);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get area by Id: {}", responseString);
			AreaDTO areaMasterobj = gson.fromJson(responseString, new TypeToken<AreaDTO>() {}.getType());
			return areaMasterobj;
		}catch(Exception ex)
		{
			logger.info("Exception in getAreaById ",ex);
			return null;
		}

	}


	/**
	 * @author Manodip Jana
	 *
	 * @param chargeMasterObject
	 * @return String
	 *
	 */
	public String createChargeMaster(ChargeMaster chargeMasterObject) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_CHARGE_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(chargeMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in createChargeMaster ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param chargeMasterObject
	 * @return String
	 */
	public String editChargeMaster(ChargeMaster chargeMasterObject) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_CHARGE_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(chargeMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editChargeMaster ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getChargeMasterId(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_CHARGE_MASTER_BYID);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getChargeMasterId ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return String
	 *
	 */
	public String deleteChargeMaster(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_CHARGE_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in deleteChargeMaster ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getAllChargeMaster(CommonResultSetMapper commonResultSetMapper) {
		try{
		String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_CHARGE_MASTER);
		//logger.info("url....{}",url);
		ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
		String responseString=response.getEntity(String.class);
		//logger.info("response string...{}",responseString);
		return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getAllChargeMaster ",ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getVariantType(CommonResultSetMapper commonResultSetMapper) {
		try{
		String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETALL_VARIENTTYPE);
		//logger.info("url....{}",url);
		ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
		String responseString=response.getEntity(String.class);
		//logger.info("response string...{}",responseString);
		return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getVariantType ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param variantMaster
	 * @return
	 *
	 */
	public String createVarientMaster(VariantMaster variantMaster) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_VARIENT_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(variantMaster));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in createVarientMaster ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param variantMasterObject
	 * @return
	 *
	 */
	public String editVariantMaster(VariantMaster variantMasterObject) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_VARIENT_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(variantMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editVariantMaster ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getAllVariantMaster(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_VARIANT_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
			}
			catch(Exception ex)
			{
				logger.info("Exception in getAllVariantMaster ",ex);
				return null;
			}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String deleteVariantMaster(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_VARIANT_MASTER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in deleteVariantMaster",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getVariantMasterId(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_VARIANT_MASTER_BYID);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getVariantMasterId ",ex);
			return null;
		}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return String
	 * 30-Nov-2017
	 */
	public String getAllRetailTypeStorewise(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_RETAILTYPE_GETALLRETAILTYPEBYSTORE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
			}
			catch(Exception ex)
			{
				logger.info("Exception in getAllRetailTypeStorewise ",ex);
				return null;
			}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * 30-Nov-2017
	 */
	public String getAllSelectedMenuByRetailType(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_RETAILTYPE_GETSELMENURETAILTYPEWISE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
			}
			catch(Exception ex)
			{
				logger.info("Exception in getAllSelectedMenuByRetailType ",ex);
				return null;
			}
	}


	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getCatBySubCat(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_SUBCAT_BYCATEGORY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
			}
			catch(Exception ex)
			{
				logger.info("Exception in getCatBySubCat ",ex);
				return null;
			}
 }
}
