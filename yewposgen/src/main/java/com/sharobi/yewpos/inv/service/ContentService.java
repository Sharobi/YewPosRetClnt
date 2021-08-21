
package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ContentMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class ContentService {

	private final static Logger logger=LoggerFactory.getLogger(InvSetupService.class);
	  Gson gson = new Gson();

	public List<ContentMaster> getAllContent(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_CONTENTS).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all contents: {}", responseString);
			List<ContentMaster> contentMasterList = new ArrayList<ContentMaster>();
			contentMasterList = gson.fromJson(responseString, new TypeToken<List<ContentMaster>>(){}.getType());
			return contentMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception getAllContent ",ex);
			return null;
		}

	}

	public String addContent(ContentMaster ContentMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_CONTENT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(ContentMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addContent ",ex);
			return null;
		}
	}

	public String editContent(ContentMaster ContentMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_CONTENT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(ContentMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception editContent ",ex);
			return null;
		}
	}

	public String deleteContent(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_CONTENT).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Content: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteContent ",ex);
			return null;
		}

	}

	public List<ContentMaster> searchContent(ContentMaster contentMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_CONTENT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(contentMaster));
			String responseString = response.getEntity(String.class);
			List<ContentMaster> contentMasterList = new ArrayList<ContentMaster>();
			contentMasterList = gson.fromJson(responseString, new TypeToken<List<ContentMaster>>() {}.getType());
			return contentMasterList;
			} catch (Exception ex) {
			logger.info("Exception searchContent ", ex);
			return null;
		}
	}

	public List<CommonResultSetMapper> searchContentAutoComplete(ContentMaster contentMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_CONTENT_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(contentMaster));
			String responseString = response.getEntity(String.class);
			List<CommonResultSetMapper> commonResultSetMapper = new ArrayList<CommonResultSetMapper>();
			commonResultSetMapper = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetMapper;
			} catch (Exception ex) {
			logger.info("Exception in searchContentAutoComplete ", ex);
			return null;
		}
	}
}
