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
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.inv.model.AreaDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.proc.model.DistributorDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.DistributorPayment;
import com.sharobi.yewpos.proc.model.PaymentDetailsAllDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip, habib,SIDDIK
 *
 */
@Service
public class VendorService {

	private final static Logger logger=LoggerFactory.getLogger(VendorService.class);
	  Gson gson = new Gson();

	/*public List<DistributorMaster> getAllVendor(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_VENDOR).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			logger.info("Response string in get all vendors: {}", responseString);
			List<DistributorMaster> DistributorMasterList = new ArrayList<DistributorMaster>();
			DistributorMasterList = gson.fromJson(responseString, new TypeToken<List<DistributorMaster>>(){}.getType());
			return DistributorMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception",ex);
			return null;
		}

	}*/

	public List<DistributorMaster> getAllVendorPost(LoginInfoByUserDTO userInfo,String lang)
	{
		try{
			String df="yyyy-MM-dd";
			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			commonResultSetMapper.setAsOnDate(DateUtil.getCurrentDateString(df));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_VENDOR);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			List<DistributorMaster> DistributorMasterList = new ArrayList<DistributorMaster>();
			DistributorMasterList = gson.fromJson(responseString, new TypeToken<List<DistributorMaster>>(){}.getType());
			return DistributorMasterList;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getAllVendorPost ",ex);
			return null;
		}
	}

	public List<DistributorDTO> getAllVendorOutStanding(LoginInfoByUserDTO userInfo,String lang)
	{
		try{
			String df="yyyy-MM-dd";
			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			commonResultSetMapper.setAsOnDate(DateUtil.getCurrentDateString(df));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLVENDOR_OUTSTANDING);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			List<DistributorDTO> DistributorDTOList = new ArrayList<DistributorDTO>();
			DistributorDTOList = gson.fromJson(responseString, new TypeToken<List<DistributorDTO>>(){}.getType());
			return DistributorDTOList;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getAllVendorOutStanding ",ex);
			return null;
		}
	}

	public String addVendor(DistributorMaster DistributorMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_VENDOR);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(DistributorMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addVendor ",ex);
			return null;
		}
	}

	public String editVendor(DistributorMaster DistributorMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_VENDOR);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(DistributorMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editVendor ",ex);
			return null;
		}
	}

	public String deleteVendor(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_VENDOR);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in deleteVendor ",ex);
			return null;
		}
	}

	public DistributorMaster getDistributorbyId(int id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_VENDOR_BY_ID).replace("{1}", String.valueOf(id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get distributor by Id: {}", responseString);
			DistributorMaster DistributorMasterobj = gson.fromJson(responseString, new TypeToken<DistributorMaster>() {}.getType());
			return DistributorMasterobj;
		}catch(Exception ex)
		{
			logger.info("Exception in getDistributorbyId ",ex);
			return null;
		}

	}

	public String deleteVendor(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_VENDOR).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete vendor: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteVendor ",ex);
			return null;
		}

	}

	public String getVendorAllPaymentDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<PaymentDetailsAllDTO> paymentDetailsAllDTOs = new ArrayList<PaymentDetailsAllDTO>();
//			paymentDetailsAllDTOs = gson.fromJson(responseString, new TypeToken<List<PaymentDetailsAllDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getVendorAllPaymentDetails ", ex);
			return null;
		}
	}

	public String getVendorSinglePaymentDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_VENDOR_PAYMENTDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<PaymentDetailsAllDTO> paymentDetailsAllDTOs = new ArrayList<PaymentDetailsAllDTO>();
//			paymentDetailsAllDTOs = gson.fromJson(responseString, new TypeToken<List<PaymentDetailsAllDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getVendorAllPaymentDetails ", ex);
			return null;
		}
	}

	public String getVendorPaymentHeaderById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception", ex);
			return null;
		}
	}

	public String getVendorPaymentDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTDETAILS_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getVendorPaymentDetailsById", ex);
			return null;
		}
	}

	public String getVendorPendingPayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_VENDOR_PENDINGPAYMENT_BYSUPPLIER);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getVendorPendingPayment ", ex);
			return null;
		}
	}

	public String getPaymentModes(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PAYMENTMODES);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getPaymentModes ", ex);
			return null;
		}
	}

	public String createOrUpdateVendorPayment(DistributorPayment distributorPayment)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_UPDATE_DISTRIBUTORPAYMENT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(distributorPayment));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in createOrUpdateVendorPayment ",ex);
			return null;
		}
	}

	public String postvendorpayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_DISTRIBUTORPAYMENT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postvendorpayment ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapperObject
	 * @return String
	 *
	 */
	public String getVendorDet(CommonResultSetMapper commonResultSetMapperObject) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_VENDOR_BY_ID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapperObject));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception", ex);
			return null;
		}
	}

	/*
	 * add on 5_ 3_2018
	 */
	public List<AccountDTO> searchallvendelledger(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLVENDOR_LEGER);
			//logger.info("url...searchallvendelledger.{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get all area: {}", responseString);
			List<AccountDTO> ledgerlist = new ArrayList<AccountDTO>();
			ledgerlist = gson.fromJson(responseString, new TypeToken<List<AccountDTO>>() {}.getType());
			return ledgerlist;
		} catch (Exception ex) {
			logger.info("Exception in searchallvendelledger ", ex);
			return null;
		}

	}

	/**
	 *  for vendor payment print
	 */
	public PaymentDetailsAllDTO getVendorPayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in getVendorPayment print: {}", responseString);

			PaymentDetailsAllDTO vd = new PaymentDetailsAllDTO();
			vd = gson.fromJson(responseString, PaymentDetailsAllDTO.class);
			//
			return vd;

		} catch (Exception ex) {
			logger.info("Exception in getVendorPayment ", ex);
			return null;
		}

	}
}
