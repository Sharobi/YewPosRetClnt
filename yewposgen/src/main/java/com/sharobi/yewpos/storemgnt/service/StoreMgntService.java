/**
 *
 */
package com.sharobi.yewpos.storemgnt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.FinYrMaster;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.EmailBean;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib,SIDDIK
 *
 */
@Service
public class StoreMgntService {

	private final static Logger logger=LoggerFactory.getLogger(StoreMgntService.class);
	  Gson gson=new Gson();

	public StoreMaster getStoreDetailsById(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_STORE_GETSTOREDETAILSBYID).replace("{1}", String.valueOf(id));
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get store by id: {}", responseString);
			StoreMaster store=gson.fromJson(responseString, StoreMaster.class);
			return store;
		}catch (Exception e) {
			logger.error("Exception in getStoreDetailsById ", e);
			return null;
		}
	}

	public String sendMail(EmailBean emailBean) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_MAIL_SENDMAIL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(emailBean));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in sendMail(StoreMgntService) ", ex);
			return null;
		}
	}

	public String sendHTMLMail(EmailBean emailBeanObj) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_MAIL_SENDHTMLMAIL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(emailBeanObj));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in sendHTMLMail(StoreMgntService) ", ex);
			return null;
		}
	}	
	public List<FinYrMaster> getAllFinancilaYears(int companyId, int storeId) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_STORE_GETALLFINANCILAYEARS);
			CommonResultSetMapper commonobj = new CommonResultSetMapper();
			commonobj.setStoreId(storeId);
			commonobj.setCompanyId(companyId);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonobj));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in getgetAllFinancilaYears: {}", responseString);
			List<FinYrMaster> allFinYears=gson.fromJson(responseString, new TypeToken<List<FinYrMaster>>() {}.getType());
			return allFinYears;
		}catch (Exception e) {
			logger.error("Exception in getAllFinancilaYears ", e);
			return null;
		}
	}

}
