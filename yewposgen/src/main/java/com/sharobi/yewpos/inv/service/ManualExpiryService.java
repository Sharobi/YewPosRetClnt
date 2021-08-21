package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ExpiryDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class ManualExpiryService {
	private final static Logger logger = LoggerFactory.getLogger(ManualExpiryService.class);
	  Gson gson = new Gson();
	public List<ExpiryDetailsDTO> getAllExpData(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETALLMANUALEXPIRYITEMSBYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDetailsDTO> expiryDetailsDTOs = new ArrayList<ExpiryDetailsDTO>();
			expiryDetailsDTOs = gson.fromJson(responseString, new TypeToken<List<ExpiryDetailsDTO>>() {}.getType());
			return expiryDetailsDTOs;
		} catch (Exception ex) {
			logger.info("Exception in getAllExpData", ex);
			return null;
		}
	}

}
