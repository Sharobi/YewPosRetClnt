/**
 *
 */
package com.sharobi.yewpos.acc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Sk A siddik
 *
 *         Nov 14, 2017
 */
@Service
public class AccSetupService {
	private final static Logger logger = LoggerFactory.getLogger(AccSetupService.class);
	Gson gson = new Gson();

	public String addAccountSetup(AccountDTO accountMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SETUP_NEWACCOUNT);
			//logger.info("service addAccountSetup url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(accountMaster));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in addAccountSetup ", ex);
			return null;
		}
	}

	/*
	 * update account
	 */
	public String updateAccountSetup(AccountDTO accountMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SETUP_UPDATEACCOUNT);
			//logger.info("service updateAccountSetup url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(accountMaster));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception updateAccountSetup ", ex);
			return null;
		}
	}

	/*
	 * for delete account
	 */
	public String deleteAccount(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SETUP_DELETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in delete Account Group: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteAccount ", ex);
			return null;
		}

	}
	/*
	 * for searche accounte
	 */

	public ResponseObj searchaccount(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SETUP_ALREAD_EXIST);
			//logger.info("service searchaccount url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);

			ResponseObj responseObj = new ResponseObj();
			responseObj = gson.fromJson(responseString, ResponseObj.class);
			//
			return responseObj;
		} catch (Exception ex) {
			logger.info("Exception in searchaccount ", ex);
			return null;
		}
	}

	public List<AccountDTO> getAllAccountSetup(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SETUP_GETALLACCOUNTSETUP);
			//logger.info("service getAllAccountSetup url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);

			List<AccountDTO> accountMasterslist = new ArrayList<AccountDTO>();
			accountMasterslist = gson.fromJson(responseString, new TypeToken<List<AccountDTO>>() {
			}.getType());
			//
			return accountMasterslist;
		} catch (Exception ex) {
			logger.info("Exception in getAllAccountSetup ", ex);
			return null;
		}
	}

}
