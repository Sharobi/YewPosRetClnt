/**
 *
 */
package com.sharobi.yewpos.inv.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.OpeningStock;
import com.sharobi.yewpos.inv.model.StockDetailsAdjustmentDTO;
import com.sharobi.yewpos.inv.model.StockTransfer;
import com.sharobi.yewpos.inv.model.StockTransferDTO;
import com.sharobi.yewpos.inv.model.StockTransferDetails;
import com.sharobi.yewpos.inv.model.StockTransferDetailsDTO;
import com.sharobi.yewpos.proc.model.PurchaseOrderDTO;
import com.sharobi.yewpos.proc.model.PurchaseOrderDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.MultiPart;

/**
 * @author habib,Manodip,SIDDIK
 *
 */
@Service
public class StockService {

	private final static Logger logger=LoggerFactory.getLogger(StockService.class);
	  Gson gson = new Gson();

	public String getCurrentStockOfItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETCURRSTOCK_OFITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCurrentStockOfItem ", ex);
			return null;
		}
	}

	public String getCurrentStockOfItemByBarcode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETCURRSTOCK_OFITEM_BYBARCODE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCurrentStockOfItemByBarcode ", ex);
			return null;
		}
	}

	public String createorupdateStock(OpeningStock stockStringObj) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_CREATE_OR_UPDATE_STOCKMANUAL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(stockStringObj));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in createorupdateStock ", ex);
			return null;
		}
	}

	public String uploadstockexcel(InputStream inputFile,MultiPart multipart,CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_UPLOADFILE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPostData(url,inputFile,multipart,commonResultSetMapper);
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in uploadstockexcel ",ex);
			return null;
		}
	}

	public String getCurrentStockOfItemByBatExpMrp(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETCURRENTSTOCK_OFITEM_BYBATCHEXPMRP);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCurrentStockOfItemByBatExpMrp ", ex);
			return null;
		}
	}

	public List<StockDetailsAdjustmentDTO> getStockDetailsForAdjustment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETSTOCKDETAILS_FORADJUSTMENT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<StockDetailsAdjustmentDTO> stockDetailsAdjustmentDTOs = new ArrayList<StockDetailsAdjustmentDTO>();
			stockDetailsAdjustmentDTOs = gson.fromJson(responseString, new TypeToken<List<StockDetailsAdjustmentDTO>>() {}.getType());
			return stockDetailsAdjustmentDTOs;
		} catch (Exception ex) {
			logger.info("Exception in getStockDetailsForAdjustment ", ex);
			return null;
		}
	}

	public ResponseObj updateStkAdj(StockDetailsAdjustmentDTO stockDetailsAdjustmentDTO) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_STOCKDETAILS_UPDATE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(stockDetailsAdjustmentDTO));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.info("Exception in updateStkAdj", ex);
			return null;
		}

	}
	public ResponseObj deleteStkAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_STOCKDETAILS_DELETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.info("Exception in deleteStkAdj ", ex);
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
	public String getSerialNoByItemid(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETSERIALNUMBER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:   >>>>>>>>>> " + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getSerialNoByItemid ", ex);
			return null;
		}
	}

	public String createOrUpdateStockTransferService(StockTransfer stkTransfer) {
		// TODO Auto-generated method stub   
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_TRANSFER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(stkTransfer));
			String responseString=response.getEntity(String.class);
			
		//	logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in createOrUpdateStockTransferService ",ex);
			return null;
		}
	}
	


	public List<StockTransferDTO> getAllStockTransfer(CommonResultSetMapper commonResultSetMapper) {
		// TODO Auto-generated method stub
		//System.err.println("From Service------------------ "+commonResultSetMapper);
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_TRANSFERREG);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<StockTransferDTO> stockTransferDTO = new ArrayList<StockTransferDTO>();
			stockTransferDTO = gson.fromJson(responseString, new TypeToken<List<StockTransferDTO>>() {}.getType());
			return stockTransferDTO;
		} catch (Exception ex) {
			logger.info("Exception in getAllStockTransfer", ex);
			return null;
		}
	}

	public StockTransferDTO getStockTransferHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_VIEW_INV_STOCK_TRANSFER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			StockTransferDTO stockTransfer = new StockTransferDTO();
			stockTransfer = gson.fromJson(responseString, new TypeToken<StockTransferDTO>() {}.getType());
			return stockTransfer;
		} catch (Exception ex) {
			logger.info("Exception in getStockTransferHeader ", ex);
			return null;
		}
	}

	public List<StockTransferDetailsDTO> getStockTransferDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_VIEW_INV_STOCK_TRANSFER_DETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<StockTransferDetailsDTO> stockTransferDetails = new ArrayList<StockTransferDetailsDTO>();
			stockTransferDetails = gson.fromJson(responseString, new TypeToken<List<StockTransferDetailsDTO>>() {}.getType());
			return stockTransferDetails;
			
		} catch (Exception ex) {
			logger.info("Exception in getStockTransferDetails ", ex);
			return null;
		}
	}

	public String deleteStockTransfer(CommonResultSetMapper commonResultSetMapper) {
		
			try {
				String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_TRANSFER_DELETE);
				//logger.info("url....{}", url);
				ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
				String responseString = response.getEntity(String.class);
				//System.out.println("responseString:" + responseString);
				return responseString;
			} catch (Exception ex) {
				logger.info("Exception in deleteStockTransfer ", ex);
				return null;
			}
		}

	public String doConfirmDispatchStockTransfer(CommonResultSetMapper commonResultSetMapper) {
		// TODO Auto-generated method stub
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_TRANSFER_DISPATCH);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in doConfirmDispatchStockTransfer ", ex);
			return null;
		}
	}

	public List<StockTransferDTO> getAllStockTransferReceive(CommonResultSetMapper commonResultSetMapper) {
		// TODO Auto-generated method stub
		//System.err.println("From Stock receive Service------------------ "+commonResultSetMapper);
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_RECEIVEREG);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<StockTransferDTO> stockTransferDTO = new ArrayList<StockTransferDTO>();
			stockTransferDTO = gson.fromJson(responseString, new TypeToken<List<StockTransferDTO>>() {}.getType());
			return stockTransferDTO;
		} catch (Exception ex) {
			logger.info("Exception in getAllStockTransferReceive ", ex);
			return null;
		}
	}

	public String createOrUpdateStockTransferReceive(StockTransfer stkTransfer) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_RECEIVE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(stkTransfer));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in createOrUpdateStockTransferReceive ",ex);
			return null;
		}
	}

	public String closeStockTransferReceiveService(CommonResultSetMapper commonResultSetMapper) {
		
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_TRANSFER__RECEIVE_CLOSE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString from service:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in closeStockTransferReceiveService ", ex);
			return null;
		}
	}
	
	
	public String reStockTransitQtyService(StockTransferDetails stkTransferdtl) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_RE_STOCK_TRANSIT_QTY);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(stkTransferdtl));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in reStockTransitQtyService",ex);
			return null;
		}
	}
	
	
public String closeStockTransferSend(CommonResultSetMapper commonResultSetMapper) {
		
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_TRANSFER__SEND_CLOSE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString from service:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in closeStockTransferSend", ex);
			return null;
		}
	}
	

	}


