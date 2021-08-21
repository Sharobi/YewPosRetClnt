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
import com.sharobi.yewpos.proc.model.PurchaseReturn;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,habib,SIDDIK
 *
 */

@Service
public class PurReturnService {
	private final static Logger logger = LoggerFactory.getLogger(PurReturnService.class);
	 Gson gson = new Gson();

	public List<PurchaseReturnDTO> getAllPurInvReturnMemoInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_ALL_PURCHASE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<PurchaseReturnDTO> purchaseReturnDTOs = new ArrayList<PurchaseReturnDTO>();
			purchaseReturnDTOs = gson.fromJson(responseString, new TypeToken<List<PurchaseReturnDTO>>() {}.getType());
			return purchaseReturnDTOs;
		} catch (Exception ex) {
			logger.info("Exception in getAllPurInvReturnMemoInvoice ", ex);
			return null;
		}
	}

	public String getPurInvReturnMemoHeaderById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurInvReturnMemoHeaderById ", ex);
			return null;
		}
	}

	public String getPurInvReturnMemoDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurInvReturnMemoDetailsById ", ex);
			return null;
		}
	}

	public String getPurInvDetailsByInvNoForRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYINVNO);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurInvDetailsByInvNoForRet ", ex);
			return null;
		}
	}

	public String getPurInvDetailsByItemIdForRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYITEMID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurInvDetailsByItemIdForRet ", ex);
			return null;
		}
	}

	public String getPurInvDetailsBySkuForRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYISKU);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurInvDetailsBySkuForRet ", ex);
			return null;
		}
	}

	public String createOrUpdatePurReturnInvoice(PurchaseReturn purchaseReturn) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_UPDATE_PURRETURNINV);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchaseReturn));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdatePurReturnInvoice ", ex);
			return null;
		}
	}

	public String deletePurRetInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_DELETE_PURRETURNINV);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deletePurRetInvoice ", ex);
			return null;
		}
	}

	public String postPurRetInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_PURRETURNINV);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postPurRetInvoice ", ex);
			return null;
		}
	}

	public String getPrevRetAdjDetailsByPid(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJPURRETURNBYPURID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPrevRetAdjDetailsByPid ", ex);
			return null;
		}
	}

	public String getReturnMemoDetailsForAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJPURRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoDetailsForAdj ", ex);
			return null;
		}
	}

	public String getReturnExpDetailsForAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJEXPRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnExpDetailsForAdj ", ex);
			return null;
		}
	}

	public String postAllSelectedPurchaseReturn(PurchaseReturn purchaseReturn) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASERETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchaseReturn));
			String responseString = response.getEntity(String.class);
		//	System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postAllSelectedPurchaseReturn ", ex);
			return null;
		}
	}

}
