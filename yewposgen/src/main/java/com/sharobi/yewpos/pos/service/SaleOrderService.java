package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.SaleOrder;
import com.sharobi.yewpos.pos.model.SaleOrderDTO;
import com.sharobi.yewpos.pos.model.SaleOrderDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class SaleOrderService {
	private final static Logger logger=LoggerFactory.getLogger(SaleOrderService.class);
	  Gson gson = new Gson();

	public String createOrUpdateSaleOrder(SaleOrder saleOrder) {
		try {
			//System.out.println("createOrUpdateSaleOrder request = "+saleOrder);
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_CREATE_OR_UPDATE_SALEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(saleOrder));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdateSaleOrder ", ex);
			return null;
		}
	}
	
	public List<SaleOrderDTO> getAllSaleOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_GET_ALL_SALEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleOrderDTO> saleOrderList = new ArrayList<SaleOrderDTO>();
			saleOrderList = gson.fromJson(responseString, new TypeToken<List<SaleOrderDTO>>() {}.getType());
			return saleOrderList;
		} catch (Exception ex) {
			logger.info("Exception in getAllSaleOrder ", ex);
			return null;
		}
	}

	public SaleOrderDTO getSaleOrderHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_GET_SALEORDERHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			SaleOrderDTO saleOrder = new SaleOrderDTO();
			saleOrder = gson.fromJson(responseString, new TypeToken<SaleOrderDTO>() {}.getType());
			return saleOrder;
		} catch (Exception ex) {
			logger.info("Exception in getSaleOrderHeader ", ex);
			return null;
		}
	}

	public List<SaleOrderDetailsDTO> getSaleOrderDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_GET_SALEORDERDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleOrderDetailsDTO> saleOrderDetailsList = new ArrayList<SaleOrderDetailsDTO>();
			saleOrderDetailsList = gson.fromJson(responseString, new TypeToken<List<SaleOrderDetailsDTO>>() {}.getType());
			return saleOrderDetailsList;
		} catch (Exception ex) {
			logger.info("Exception in getSaleOrderDetails ", ex);
			return null;
		}
	}

	public String postSaleOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_POST_SALEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postSaleOrder ", ex);
			return null;
		}
	}

	public String deleteSaleOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_DELETE_SALEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteSaleOrder ", ex);
			return null;
		}
	}

	public String closeSaleOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_CLOSE_SALEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in closeSaleOrder ", ex);
			return null;
		}
	}

	public String getSaleOrderDetailsByOrderNoForPI(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_GET_SALEORDERDETAILS_BYINV);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleOrderDetailsByOrderNoForPI ", ex);
			return null;
		}
	}

	public String getSaleOrderDetailsByInvoiceNo(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_GET_SALEORDERDETAILS_BYINVOICENO);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleOrderDetailsByInvoiceNo ", ex);
			return null;
		}
	}
}
