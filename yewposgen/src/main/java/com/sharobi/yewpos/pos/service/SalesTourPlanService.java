package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.SaleTourPlanDTO;
import com.sharobi.yewpos.pos.model.SaleTourPlanDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class SalesTourPlanService {
	private final static Logger logger=LoggerFactory.getLogger(SalesTourPlanService.class);
	Gson gson = new Gson();
	public List<SaleTourPlanDTO> getAllSaleTourPlanDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_TOURPLAN_SEARCH_ALLTOURPLANBYSALESMANID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleTourPlanDTO> allSaleTourPlanDetails = new ArrayList<SaleTourPlanDTO>();
			allSaleTourPlanDetails = gson.fromJson(responseString, new TypeToken<List<SaleTourPlanDTO>>() {}.getType());
			return allSaleTourPlanDetails;
		} catch (Exception ex) {
			logger.info("Exception in getAllSaleTourPlanDetails", ex);
			return null;
		}
	}
	
	public String getSaleTourPlanDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_TOURPLAN_SEARCH_TOURPLANDETAILSBYSALESMANID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			/*List<SaleTourPlanDetailsDTO> allSaleTourPlanDetails = new ArrayList<SaleTourPlanDetailsDTO>();
			allSaleTourPlanDetails = gson.fromJson(responseString, new TypeToken<List<SaleTourPlanDetailsDTO>>() {}.getType());
			*/return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleTourPlanDetailsById ", ex);
			return null;
		}
	}
	
	public String getSaleTourHeaderById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_TOURPLAN_SEARCH_TOURPLANBYSALESMANID);
			logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			/*SaleTourPlanDTO saleTourPlan = new SaleTourPlanDTO();
			saleTourPlan = gson.fromJson(responseString, new TypeToken<SaleTourPlanDTO>() {}.getType());*/
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception", ex);
			return null;
		}
	}

	public String getSaleTourPlanLocationDetailsByDateAndSalesmanId(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_TOURPLAN_GET_LOCATIONSBYDATEANDSALESMANID);
			logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			/*SaleTourPlanDTO saleTourPlan = new SaleTourPlanDTO();
			saleTourPlan = gson.fromJson(responseString, new TypeToken<SaleTourPlanDTO>() {}.getType());*/
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception", ex);
			return null;
		}
	}
}
