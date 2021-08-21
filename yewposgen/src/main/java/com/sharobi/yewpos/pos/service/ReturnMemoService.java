/**
 *
 */
package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.SaleReturn;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib,Manodip,SIDDIK
 *
 */
@Service
public class ReturnMemoService {

	private final static Logger logger = LoggerFactory.getLogger(ReturnMemoService.class);
	 Gson gson = new Gson();

	public List<SaleReturnDTO> getAllReturnMemoInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_ALLRETURNDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleReturnDTO> saleReturnDTOs = new ArrayList<SaleReturnDTO>();
			saleReturnDTOs = gson.fromJson(responseString, new TypeToken<List<SaleReturnDTO>>() {}.getType());
			return saleReturnDTOs;
		} catch (Exception ex) {
			logger.info("Exception in getAllReturnMemoInvoice ", ex);
			return null;
		}
	}

	public String getReturnMemoSaleDetailsByInvNo(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_RETURNSALEDET_BYINVNO);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoSaleDetailsByInvNo", ex);
			return null;
		}
	}

	public String getReturnMemoSaleHeaderByInvNo(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_RETURNHEADER_BYINVNO);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoSaleHeaderByInvNo ", ex);
			return null;
		}
	}

	public String getReturnMemoSaleALLDetailsByInvNo(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_RETURNSALEALLDET_BYINVNO);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoSaleALLDetailsByInvNo ", ex);
			return null;
		}
	}

	public String getReturnMemoSaleALLDetailsByItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_RETURNSALEALLDET_BYITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoSaleALLDetailsByItem", ex);
			return null;
		}
	}

	public String createOrUpdateSalesReturnInvoice(SaleReturn saleReturn) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_CREATEORUPDATE_SALESRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(saleReturn));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdateSalesReturnInvoice ", ex);
			return null;
		}
	}

	public String getReturnMemoSaleDetailsByRetInvId(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_SALESALERETURNDETAILS_BYRETURNID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoSaleDetailsByRetInvId ", ex);
			return null;
		}
	}

	public String getReturnMemoSaleHeaderByRetInvId(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_SALESALERETURNHEADER_BYRETURNID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoSaleHeaderByRetInvId ", ex);
			return null;
		}
	}

	public String deleteRetSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_DELETE_SALESRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteRetSalesInvoice ", ex);
			return null;
		}
	}

	public String postRetSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_POST_SALESRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postRetSalesInvoice ", ex);
			return null;
		}
	}

	public String getReturnMemoDetailsForAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_ADJSALESRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postRetSalesInvoice ", ex);
			return null;
		}
	}

	public String getReturnMemoDetailsForAdjBySaleId(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_ADJSALESRETURN_BYSALEID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getReturnMemoDetailsForAdjBySaleId ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param saleReturn
	 * @return
	 */
	public String postAllSelectedReturnSalesInv(SaleReturn saleReturn) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_POST_ALL_SALESRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(saleReturn));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postAllSelectedReturnSalesInv ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 *
	 */
	public String getAllSlNoByItemidAndInvno(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_SLNO_SALESRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getAllSlNoByItemidAndInvno ", ex);
			return null;
		}
	}

}
