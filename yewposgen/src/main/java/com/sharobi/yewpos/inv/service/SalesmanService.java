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
import com.sharobi.yewpos.inv.model.SalesmanDTO;
import com.sharobi.yewpos.inv.model.SalesmanMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class SalesmanService {

	private final static Logger logger=LoggerFactory.getLogger(MarketerService.class);
	  Gson gson=new Gson();

	public List<SalesmanMaster> searchSalesman(SalesmanMaster salesmanMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_SALESMAN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(salesmanMaster));
			String responseString = response.getEntity(String.class);
			List<SalesmanMaster> salesmanMasterList = new ArrayList<SalesmanMaster>();
			salesmanMasterList = gson.fromJson(responseString, new TypeToken<List<SalesmanMaster>>() {}.getType());
			//System.out.println(salesmanMasterList);
			return salesmanMasterList;
			} catch (Exception ex) {
			logger.info("Exception in searchSalesman ", ex);
			return null;
		}
	}

	public List<SalesmanDTO> getAllSalesmanmasterlist(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETSALESMAN);
			//logger.info("url....{} getAllMarkertermasterlist call==",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all marketermaster: {}", responseString);
			List<SalesmanDTO> salesmanMasters = new ArrayList<SalesmanDTO>();
			salesmanMasters = gson.fromJson(responseString, new TypeToken<List<SalesmanDTO>>(){}.getType());
			return salesmanMasters;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllSalesmanmasterlist",ex);
			return null;
		}

	}

	public String addSalesman(SalesmanMaster salesmanMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_SALESMAN);
			//logger.info("url....{}  salesman service call",url);

			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(salesmanMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addSalesman",ex);
			return null;
		}
	}
// for edit

	public String editSalesman(SalesmanMaster salesmanMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_SALESMAN);
			//logger.info("url....{}  call merketer edit",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(salesmanMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editSalesman",ex);
			return null;
		}
	}

	public String deleteSalesman(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_SALESMAN);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Salesman: {} ", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteSalesman",ex);
			return null;
		}
	}

	public String getSalesmanbyId(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETSALESMANBYID);
			//logger.info("url....{}",url);
			//ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getSalesmanbyId",ex);
			return null;
		}
	}

	public String getSalesmanListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_SALESMAN_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSalesmanListAutocomplete ", ex);
			return null;
		}
	}
}
