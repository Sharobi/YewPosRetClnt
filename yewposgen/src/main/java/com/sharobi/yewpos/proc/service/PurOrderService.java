/**
 *
 */
package com.sharobi.yewpos.proc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.proc.model.PurchaseOrder;
import com.sharobi.yewpos.proc.model.PurchaseOrderDTO;
import com.sharobi.yewpos.proc.model.PurchaseOrderDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class PurOrderService {
	private final static Logger logger=LoggerFactory.getLogger(PurInvoiceService.class);
	  Gson gson = new Gson();

	public String createOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_CREATE_OR_UPDATE_PURCHASEORDER);
			
			logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchaseOrder));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdatePurchaseOrder ", ex);
			return null;
		}
	}

	public String createTempPOFromSale(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_TEMP_CREATE_FROMSALE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createTempPOFromSale", ex);
			return null;
		}
	}

	public PurchaseOrderDTO getPurchaseOrderHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
			purchaseOrder = gson.fromJson(responseString, new TypeToken<PurchaseOrderDTO>() {}.getType());
			return purchaseOrder;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseOrderHeader ", ex);
			return null;
		}
	}

	public List<PurchaseOrderDetailsDTO> getPurchaseOrderDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<PurchaseOrderDetailsDTO> purchaseOrderDetailsList = new ArrayList<PurchaseOrderDetailsDTO>();
			purchaseOrderDetailsList = gson.fromJson(responseString, new TypeToken<List<PurchaseOrderDetailsDTO>>() {}.getType());
			return purchaseOrderDetailsList;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseOrderDetails ", ex);
			return null;
		}
	}

	public String getPurOrderDetailsByOrderNoForPI(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERDETAILS_BYINV);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurOrderDetailsByOrderNoForPI ", ex);
			return null;
		}
	}

	public List<PurchaseOrderDTO> getAllPurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		System.err.println("From Service------------------ "+commonResultSetMapper);
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_ALL_PURCHASEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<PurchaseOrderDTO> purchaseOrderList = new ArrayList<PurchaseOrderDTO>();
			purchaseOrderList = gson.fromJson(responseString, new TypeToken<List<PurchaseOrderDTO>>() {}.getType());
			return purchaseOrderList;
		} catch (Exception ex) {
			logger.info("Exception in getAllPurchaseOrder ", ex);
			return null;
		}
	}

	public String getPurOrderByType(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDER_BY_TYPE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurOrderByType ", ex);
			return null;
		}
	}

	public String deletePurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_DELETE_PURCHASEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deletePurchaseOrder ", ex);
			return null;
		}
	}

	public String postPurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_POST_PURCHASEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postPurchaseOrder ", ex);
			return null;
		}
	}

	public String closePurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_CLOSE_PURCHASEORDER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in closePurchaseOrder ", ex);
			return null;
		}
	}

	public String calPurOrdrQty(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_CAL_PURCHASEORDERQTY);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in calPurOrdrQty ", ex);
			return null;
		}
	}
}
