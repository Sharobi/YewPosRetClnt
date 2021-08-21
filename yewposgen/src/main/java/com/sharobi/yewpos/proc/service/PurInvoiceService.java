/**
 *
 */
package com.sharobi.yewpos.proc.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.proc.model.BarcodePrintParam;
import com.sharobi.yewpos.proc.model.BarcodePrintParamList;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.model.PurchaseInv;
import com.sharobi.yewpos.proc.model.PurchaseInvDetailsDTO;
import com.sharobi.yewpos.proc.model.PurchaseInvHeaderDTO;
import com.sharobi.yewpos.proc.model.PurchaseInvoiceExcelDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.MultiPart;

/**
 * @author habib,Manodip,SIDDIK
 *
 */
@Service
public class PurInvoiceService {
	private final static Logger logger=LoggerFactory.getLogger(PurInvoiceService.class);
	  Gson gson = new Gson();

	public Purchase getPurchaseInvoiceHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			Purchase purchase = new Purchase();
			purchase = gson.fromJson(responseString, new TypeToken<Purchase>() {}.getType());
			return purchase;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseInvoiceHeader ", ex);
			return null;
		}
	}

	public List<PurchaseDetails> getPurchaseInvoiceDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<PurchaseDetails> purchaseDetailsList = new ArrayList<PurchaseDetails>();
			purchaseDetailsList = gson.fromJson(responseString, new TypeToken<List<PurchaseDetails>>() {}.getType());
			return purchaseDetailsList;
		} catch (Exception ex) {
			logger.info("Exception getPurchaseInvoiceDetails ", ex);
			return null;
		}
	}

	public List<PurchaseReturnDTO> getPurchaseReturnHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJPURRETURNBYPURID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<PurchaseReturnDTO> purchaseReturnHeader = new ArrayList<PurchaseReturnDTO>();
			purchaseReturnHeader = gson.fromJson(responseString, new TypeToken<List<PurchaseReturnDTO>>() {}.getType());
			return purchaseReturnHeader;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseReturnHeader ", ex);
			return null;
		}
	}

	public String getPurInvDetailsBySku(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEDETAILS_BYSKU);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurInvDetailsBySku ", ex);
			return null;
		}
	}

	/**
	 *
	 * @author Manodip Jana
	 * @param commonResultSetMapper, itemid,distributorid
	 * @return List<PurchaseDetails>
	 * @used for Purchase Invoice item search
	 *
	 */
	public String getPurchaseInvoiceDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseInvoiceDetailsById ", ex);
			return null;
		}
	}
	//For purchase challan register
	public List<Purchase> getAllPurchaseInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ALL_PURCHASEDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<Purchase> purchaseList = new ArrayList<Purchase>();
			purchaseList = gson.fromJson(responseString, new TypeToken<List<Purchase>>() {}.getType());
			return purchaseList;
		} catch (Exception ex) {
			logger.info("Exception in getAllPurchaseInvoice ", ex);
			return null;
		}
	}
	//For purchase invoice register
	public List<Purchase> getAllPurchaseInvoiceRegisters(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ALL_PURCHASEINVOICEDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<Purchase> purchaseList = new ArrayList<Purchase>();
			purchaseList = gson.fromJson(responseString, new TypeToken<List<Purchase>>() {}.getType());
			return purchaseList;
		} catch (Exception ex) {
			logger.info("Exception in getAllPurchaseInvoiceRegisters ", ex);
			return null;
		}
	}


	public String createOrUpdatePurchaseInvoice(Purchase purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_OR_UPDATE_PURCHASEINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdatePurchaseInvoice ", ex);
			return null;
		}
	}
	//For purchase challan
	public String deletePurchaseInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_DELETE_PURCHASEINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deletePurchaseInvoice ", ex);
			return null;
		}
	}

	//For purchase Invoice
	public String deletePurchaseInvoiceService(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_DELETE_PURCHASEINVOICE_URL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deletePurchaseInvoiceService ", ex);
			return null;
		}
	}
	public String postPurchaseInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_PURCHASEINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postPurchaseInvoice ", ex);
			return null;
		}
	}

	public String postPurInvDirIndir(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_PURCHASEINVOICE_DIRINDIR);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in ", ex);
			return null;
		}
	}

	/**
	 *
	 * @author Manodip Jana
	 * @param commonResultSetMapper, itemid,distributorid
	 * @return List<PurchaseHistoryDTO>
	 * @used for Purchase Invoice History during item search
	 *
	 */
	public String getPurchaseInvoiceHistoryDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEHISTORY);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseInvoiceHistoryDetailsById ", ex);
			return null;
		}
	}
	
	
	public String getPurchaseInvoiceHistoryDetailsByIdBatch(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEHISTORY_ID_BATCH);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseInvoiceHistoryDetailsByIdBatch ", ex);
			return null;
		}
	}

	/*
	 *
	 */

	public String uploadPurInvFromExcel(InputStream inputFile,MultiPart multipart,PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_PURINV_FROMEXCEL);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPostDataPurInvExc(url,inputFile,multipart,purchaseInvoiceExcelDTO);
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in uploadPurInvFromExcel ",ex);
			return null;
		}
	}
	//for purchase challan
	public String postAllSelectedPurchaseInv(Purchase purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASEINVOICE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postAllSelectedPurchaseInv ", ex);
			return null;
		}
	}

	//for purchase invoice
	public String postAllSelectedPurchaseInvoiceDetails(Purchase purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASEINVOICE_DETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postAllSelectedPurchaseInvoiceDetails ", ex);
			return null;
		}
	}

	public String checksamebillonvendor(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CHECK_DUPLICATE_BILL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in checksamebillonvendor ", ex);
			return null;
		}
	}

	public String printByBarcode(BarcodePrintParam barcodePrintParamObj) {
		try {
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_PRINT_BY_BARCODE);

			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(barcodePrintParamObj));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string get cashmemo print count status: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in printByBarcode ", ex);
			return null;
		}
	}

	public String printByBarcodeAll(BarcodePrintParamList barcodePrintParamObj) {
		try {
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_PRINT_BY_BARCODE_ALL);

			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(barcodePrintParamObj));
			String responseString=response.getEntity(String.class);
			//logger.info("Response string get cashmemo print count status: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in printByBarcodeAll ", ex);
			return null;
		}
	}


	public String createOrUpdatePurchaseInvoiceDirect(PurchaseInv purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_OR_UPDATE_PURCHASEINVOICEDIRECT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdatePurchaseInvoiceDirect ", ex);
			return null;
		}
	}

	public String createOrUpdatePurchaseInvoiceIndirect(PurchaseInv purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_OR_UPDATE_PURCHASEINVOICEINDIRECT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createOrUpdatePurchaseInvoiceIndirect ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * Jun 8, 2018
	 */
	public String getAllPendingPurchaseChallan(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ALL_PENDINGPURCHASECHALLANS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getAllPendingPurchaseChallan ", ex);
			return null;
		}
	}

	/**
	 * @author
	 *
	 * @param commonResultSetMapper
	 * @return
	 * Jun 11, 2018
	 */
	public List<PurchaseInvDetailsDTO> getPurchaseInvoiceDirectDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEDETAILS_DIRECT_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<PurchaseInvDetailsDTO> purchaseDetailsList = new ArrayList<PurchaseInvDetailsDTO>();
			purchaseDetailsList = gson.fromJson(responseString, new TypeToken<List<PurchaseInvDetailsDTO>>() {}.getType());
			return purchaseDetailsList;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseInvoiceDirectDetails ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * Jun 11, 2018
	 */
	public PurchaseInvHeaderDTO getPurchaseInvoiceDirectHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEHEADER_DETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			PurchaseInvHeaderDTO purchaseHeader = new PurchaseInvHeaderDTO();
			purchaseHeader = gson.fromJson(responseString, new TypeToken<PurchaseInvHeaderDTO>() {}.getType());
			return purchaseHeader;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseInvoiceDirectHeader ", ex);
			return null;
		}
	}

	public String getPurchaseDetailsbyPurchaseIDs(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEDETAILS_BYPURIDS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseDetailsbyPurchaseIDs ", ex);
			return null;
		}
	}

	/**
	 *
	 * @param commonResultSetMapper
	 * @return
	 * Jun 15, 2018
	 */
	public String getPurchaseChallanListByInvID(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ALL_PENDINGPURCHASECHALLANS_BY_INVID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPurchaseChallanListByInvID ", ex);
			return null;
		}
	}
}
