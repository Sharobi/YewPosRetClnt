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
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.MarketerMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class MarketerService {

	private final static Logger logger=LoggerFactory.getLogger(MarketerService.class);
	  Gson gson=new Gson();

	public List<MarketerMaster> searchMarketer(MarketerMaster marketerMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_MARKETER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(marketerMaster));
			String responseString = response.getEntity(String.class);
			List<MarketerMaster> marketerMasterList = new ArrayList<MarketerMaster>();
			marketerMasterList = gson.fromJson(responseString, new TypeToken<List<MarketerMaster>>() {}.getType());
			//System.out.println(marketerMasterList);
			return marketerMasterList;
			} catch (Exception ex) {
			logger.info("Exception in searchMarketer", ex);
			return null;
		}
	}

	public List<MarketerMaster> getAllMarkertermasterlist(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETMARKETERSBYCOMPID).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{} getAllMarkertermasterlist call==",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all marketermaster: {}", responseString);
			List<MarketerMaster> marketerMasters = new ArrayList<MarketerMaster>();
			marketerMasters = gson.fromJson(responseString, new TypeToken<List<MarketerMaster>>(){}.getType());
			return marketerMasters;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllMarkertermasterlist ",ex);
			return null;
		}

	}

	public String addMarketer(MarketerMaster marketerMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_MARKETER);
			//logger.info("url....{}  marketer service call",url);

			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(marketerMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addMarketer ",ex);
			return null;
		}
	}
// for edit

	public String editMarketer(MarketerMaster marketerMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_MARKETER);
			//logger.info("url....{}  call merketer edit",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(marketerMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editMarketer ",ex);
			return null;
		}
	}

	public String deleteMaketer(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_MARKETER).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete marketer: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteMaketer",ex);
			return null;
		}
	}

	public String getMarketerbyId(int id, String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETMARKETERBYID).replace("{1}", String.valueOf(id)).replace("{2}",lang);
			//logger.info("url....{}",url);
			//ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getMarketerbyId ",ex);
			return null;
		}
	}

	public String getMarketerListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_MARKETER_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getMarketerListAutocomplete ", ex);
			return null;
		}
	}
}
