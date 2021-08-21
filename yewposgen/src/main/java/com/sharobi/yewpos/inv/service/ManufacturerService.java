package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Manodip
 *
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ManufacturerMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;


@Service
public class ManufacturerService {
	private final static Logger logger=LoggerFactory.getLogger(ManufacturerService.class);
	  Gson gson=new Gson();

	public List<ManufacturerMaster> searchManufacturer(ManufacturerMaster manufacturerMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_MANUFACTURER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(manufacturerMaster));
			String responseString = response.getEntity(String.class);
			List<ManufacturerMaster> manufacturerMasterList = new ArrayList<ManufacturerMaster>();
			manufacturerMasterList = gson.fromJson(responseString, new TypeToken<List<ManufacturerMaster>>() {}.getType());
			return manufacturerMasterList;
			} catch (Exception ex) {
			logger.info("Exception in searchManufacturer ", ex);
			return null;
		}
	}

	public String addManufacturer(ManufacturerMaster ManufacturerMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_MANUFACTURER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(ManufacturerMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addManufacturer ",ex);
			return null;
		}
	}

	public String editManufacturer(ManufacturerMaster ManufacturerMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_MANUFACTURER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(ManufacturerMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editManufacturer",ex);
			return null;
		}
	}

	public ManufacturerMaster getManufacturerbyId(int id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETMANUFACTURERBYID).replace("{1}", String.valueOf(id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get manufacturer by Id: {}", responseString);
			ManufacturerMaster ManufacturerMasterobj = gson.fromJson(responseString, new TypeToken<ManufacturerMaster>() {}.getType());
			return ManufacturerMasterobj;
		}catch(Exception ex)
		{
			logger.info("Exception in getManufacturerbyId",ex);
			return null;
		}

	}

	public String deleteManufacturer(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_MANUFACTURER).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Manufacturer: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteManufacturer ",ex);
			return null;
		}

	}

	public List<CommonResultSetMapper> searchManufacturerAutoComplete(ManufacturerMaster manufacturerMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_MANUFACTURER_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(manufacturerMaster));
			String responseString = response.getEntity(String.class);
			List<CommonResultSetMapper> commonResultSetMapper = new ArrayList<CommonResultSetMapper>();
			commonResultSetMapper = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetMapper;
			} catch (Exception ex) {
			logger.info("Exception in searchManufacturerAutoComplete ", ex);
			return null;
		}
	}


}
