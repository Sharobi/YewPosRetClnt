package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.Expiry;
import com.sharobi.yewpos.inv.model.ExpiryDTO;
import com.sharobi.yewpos.inv.model.ExpiryDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class ExpiryService {
	private final static Logger logger = LoggerFactory.getLogger(ExpiryService.class);
	  Gson gson = new Gson();

	public List<ExpiryDTO> getAllExpiryInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETALLEXPIRYDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDTO> expiryList = new ArrayList<ExpiryDTO>();
			expiryList = gson.fromJson(responseString, new TypeToken<List<ExpiryDTO>>() {}.getType());
			return expiryList;
		} catch (Exception ex) {
			logger.info("Exception in getAllExpiryInvoice ", ex);
			return null;
		}
	}

	public List<ExpiryDetailsDTO> getAllExpInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETALLPENDINGEXPIRYITEMS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDetailsDTO> expiryDetailsDTOs = new ArrayList<ExpiryDetailsDTO>();
			expiryDetailsDTOs = gson.fromJson(responseString, new TypeToken<List<ExpiryDetailsDTO>>() {}.getType());
			return expiryDetailsDTOs;
		} catch (Exception ex) {
			logger.info("Exception in getAllExpInvoice ", ex);
			return null;
		}
	}

	public String createOrUpdateExpiryInvoice(Expiry expiry) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_OR_UPDATE_EXPIRYINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(expiry));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception createOrUpdateExpiryInvoice ", ex);
			return null;
		}
	}

	public ExpiryDTO getExpiryInvoiceHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_EXPIRYHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			ExpiryDTO expiryDTO = new ExpiryDTO();
			expiryDTO = gson.fromJson(responseString, new TypeToken<ExpiryDTO>() {}.getType());
			return expiryDTO;
		} catch (Exception ex) {
			logger.info("Exception in getExpiryInvoiceHeader ", ex);
			return null;
		}
	}

	public List<ExpiryDetailsDTO> geExpiryInvoiceDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_EXPIRYDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDetailsDTO> expiryDetailsList = new ArrayList<ExpiryDetailsDTO>();
			expiryDetailsList = gson.fromJson(responseString, new TypeToken<List<ExpiryDetailsDTO>>() {}.getType());
			return expiryDetailsList;
		} catch (Exception ex) {
			logger.info("Exception in geExpiryInvoiceDetails ", ex);
			return null;
		}
	}

	public String deleteExpInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_EXPIRYINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in  deleteExpInvoice", ex);
			return null;
		}
	}

	public String postExpiryInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_POST_EXPIRYINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postExpiryInvoice ", ex);
			return null;
		}
	}
}
