package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.EsiCodeMaster;
import com.sharobi.yewpos.pos.model.GenderMaster;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.Sales;
import com.sharobi.yewpos.pos.model.TypeMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,SIDDIK
 *
 */
@Service
public class CashMemoService {
	private final static Logger logger = LoggerFactory.getLogger(CashMemoService.class);
	 Gson gson = new Gson();

	public String getSalesHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_HEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
			//			saleHeaderDTO = gson.fromJson(responseString, new TypeToken<SaleHeaderDTO>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSalesHeader ", ex);
			return null;
		}
	}

	public String getSalesHeaderForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_HEADER_BYID_FORBILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
			//			saleHeaderDTO = gson.fromJson(responseString, new TypeToken<SaleHeaderDTO>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSalesHeaderForBill ", ex);
			return null;
		}
	}

	public String getSaleDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_DETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleDetails ", ex);
			return null;
		}
	}

	public String getSaleDetailsForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_DETAILS_BYID_FORBILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleDetailsForBill ", ex);
			return null;
		}
	}

	public String getTaxDetailsForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_TAXDETAILS_FORBILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getTaxDetailsForBill ", ex);
			return null;
		}
	}

	public List<SaleDetailsAllDTO> getAllSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_ALLSALEDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleDetailsAllDTO> saleDetailsAllDTOs = new ArrayList<SaleDetailsAllDTO>();
			saleDetailsAllDTOs = gson.fromJson(responseString, new TypeToken<List<SaleDetailsAllDTO>>() {}.getType());
			return saleDetailsAllDTOs;
		} catch (Exception ex) {
			logger.info("Exception in getAllSalesInvoice", ex);
			return null;
		}
	}

	public String createOrUpdateSalesInvoice(Sales sales) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_CREATEORUPDATE_SALEDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(sales));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdateSalesInvoice", ex);
			return null;
		}
	}

	public String postAllSelSalesInvoice(Sales sales) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALES_POSTALLSALESINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(sales));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postAllSelSalesInvoice", ex);
			return null;
		}
	}

	public String deleteSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALES_DELETESALESINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteSalesInvoice", ex);
			return null;
		}
	}

	public String postSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALES_POSTSALESINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postSalesInvoice", ex);
			return null;
		}
	}

	public String getCustomerLastSalesHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GETCUSTLASTSALEHEADER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerLastSalesHeader ", ex);
			return null;
		}
	}

	public String saleBillPrint(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_PRINT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in saleBillPrint", ex);
			return null;
		}
	}

	public List<TypeMaster> getTypes(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_GETTYPES);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<TypeMaster> typeMasterList = new ArrayList<TypeMaster>();
			typeMasterList = gson.fromJson(responseString, new TypeToken<List<TypeMaster>>() {}.getType());
			return typeMasterList;
		} catch (Exception ex) {
			logger.info("Exception in getTypes ", ex);
			return null;
		}
	}

	public List<EsiCodeMaster> getEsiCode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_GETESICODES);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<EsiCodeMaster> esiCodeList = new ArrayList<EsiCodeMaster>();
			esiCodeList = gson.fromJson(responseString, new TypeToken<List<EsiCodeMaster>>() {}.getType());
			return esiCodeList;
		} catch (Exception ex) {
			logger.info("Exception in getEsiCode ", ex);
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
	public String getPlaceOfTreatmentListAutocom(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_GETPLACEOFTREATMENT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			placeOfTreatmentList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return  responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPlaceOfTreatmentListAutocom ", ex);
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
	public List<GenderMaster> getGenderList(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_GENDERLIST);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<GenderMaster> genderList = new ArrayList<GenderMaster>();
			genderList = gson.fromJson(responseString, new TypeToken<List<GenderMaster>>() {}.getType());
			return genderList;
		} catch (Exception ex) {
			logger.info("Exception in getGenderList ", ex);
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
	public String getSaleItemDetailsforRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALEBILL_GETSALEITEMDETAILSFORRETURN);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return  responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleItemDetailsforRet ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 *
	 */
	public String getInvoicePrefixByqs(
			CommonResultSetMapper commonResultSetMapper) {
		// TODO Auto-generated method stub
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_STORE_GET_INVOICEPREFIX);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return  responseString;
		} catch (Exception ex) {
			logger.info("Exception in getInvoicePrefixByqs ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 *
	 */
	public String updateInvoicePrefixbyQS(
			CommonResultSetMapper commonResultSetMapper) {
		// TODO Auto-generated method stub
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_STORE_UPDATE_INVOICEPREFIX);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return  responseString;
		} catch (Exception ex) {
			logger.info("Exception in updateInvoicePrefixbyQS ", ex);
			return null;
		}
	}
	
	
	public String getSalesOrderHeaderForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALE_ORDER_HEADER_BYID_FORBILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
			//			saleHeaderDTO = gson.fromJson(responseString, new TypeToken<SaleHeaderDTO>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSalesOrderHeaderForBill", ex);
			return null;
		}
	}
	
	public String getSaleOrderDetailsForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALE_ORDER_DETAILS_BYID_FORBILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSaleOrderDetailsForBill", ex);
			return null;
		}
	}

	public String getTaxDetailsForSaleOrderBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALE_ORDER_TAXDETAILS_FORBILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getTaxDetailsForSaleOrderBill ", ex);
			return null;
		}
	}
	

}
