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
import com.sharobi.yewpos.pos.model.CustPaymentDetailsAllDTO;
import com.sharobi.yewpos.pos.model.CustomerDTO;
import com.sharobi.yewpos.pos.model.CustomerMaster;
import com.sharobi.yewpos.pos.model.CustomerPayment;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,SIDDIK
 *
 */
@Service
public class CustomerService {

	private final static Logger logger=LoggerFactory.getLogger(CustomerService.class);
	 Gson gson = new Gson();

	public List<CustomerMaster> getAllCustomers(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_ALL_CUSTOMERS).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all doctors: {}", responseString);
			List<CustomerMaster> customerMasterList = new ArrayList<CustomerMaster>();
			customerMasterList = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>(){}.getType());
			//System.out.println("return result: "+customerMasterList.toString());
			return customerMasterList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllCustomers ",ex);
			return null;
		}

	}

	public List<CustomerDTO> getAllCustomersPost(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_ALL_CUSTOMERS);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
			customerDTOList = gson.fromJson(responseString, new TypeToken<List<CustomerDTO>>(){}.getType());
			//System.out.println("return result: "+customerDTOList.toString());
			return customerDTOList;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getAllCustomersPost ",ex);
			return null;
		}
	}

	public String addcustomer(CustomerMaster customerMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_ADD_CUSTOMER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(customerMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addcustomer ",ex);
			return null;
		}
	}

	public String editcustomer(CustomerMaster CustomerMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_EDIT_CUSTOMER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CustomerMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editcustomer ",ex);
			return null;
		}
	}

	public String deletecustomer(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_DELETE_CUSTOMER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editcustomer ",ex);
			return null;
		}
	}


	public String getCustomerbyId(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_BY_ID);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getCustomerbyId ",ex);
			return null;
		}
	}

	public String deleteCustomer(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_DELETE_CUSTOMER).replace("{1}", String.valueOf(id));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete Customer: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteCustomer ",ex);
			return null;
		}

	}

	public String getCustomerListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_BYNAMEORPH);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
//			customerMasters = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerListAutocomplete ", ex);
			return null;
		}
	}

	public String getCustomerWithCreditListAutocom(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_WITHCREDIT_BYNAMEORPH);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
//			customerMasters = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerWithCreditListAutocom ", ex);
			return null;
		}
	}

	public String getCustomerAllPaymentDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTDETAILS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerAllPaymentDetails ", ex);
			return null;
		}
	}

	public String postcustomerpayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_POST_CUSTOMERPAYMENT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in postcustomerpayment ", ex);
			return null;
		}
	}

	public String getCustomerPendingPayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_PENDINGPAYMENT_BYCUST);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerPendingPayment ", ex);
			return null;
		}
	}

	public String createOrUpdateCustomerPayment(CustomerPayment CustomerPayment)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CREATE_UPDATE_CUSTOMERPAYMENT);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CustomerPayment));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in createOrUpdateCustomerPayment",ex);
			return null;
		}
	}

	public String getCustomerPaymentHeaderById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerPaymentHeaderById ", ex);
			return null;
		}
	}

	public String getCustomerPaymentDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_PAYMENTDETAILS_BYCUSTOMERID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getCustomerPaymentDetailsById ", ex);
			return null;
		}
	}

	public String deleteCustPaymentInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_DELETE_CUSTPAYINV);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteCustPaymentInvoice ", ex);
			return null;
		}
	}

	public String getAllCustomerType(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_ALL_CUSTOMER_TYPE);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in getAllCustomerType ",ex);
			return null;
		}
	}

	 /**
	 * for customer payment print
	 *   May 9, 2018
	 */
	public CustPaymentDetailsAllDTO getcustomerPayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTHEADER_BYID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in customerpayment print: {}", responseString);

			CustPaymentDetailsAllDTO cd = new CustPaymentDetailsAllDTO();
			cd = gson.fromJson(responseString, CustPaymentDetailsAllDTO.class);
			//
			return cd;

		} catch (Exception ex) {
			logger.info("Exception in getcustomerPayment ", ex);
			return null;
		}

	}
}
