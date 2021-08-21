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
import com.sharobi.yewpos.acc.model.Journal;
import com.sharobi.yewpos.acc.model.JournalList;
import com.sharobi.yewpos.acc.model.ParaJournalTypeMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Sk A siddik
 *
 *         Nov 14, 2017
 */

@Service
public class AccJournalService {
	private final static Logger logger = LoggerFactory.getLogger(AccJournalService.class);
	Gson gson = new Gson();

	/*
	 * for list of all journal dto
	 */
	public List<Journal> getAlljournallist(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_GET_JOURNAL_LIST);
			//logger.info("service getAlljournallist url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);

			List<Journal> journallist = new ArrayList<Journal>();
			journallist = gson.fromJson(responseString, new TypeToken<List<Journal>>() {
			}.getType());
			//
			return journallist;
		} catch (Exception ex) {
			logger.info("Exception in getAlljournallist ", ex);
			return null;
		}
	}

	/*
	 * list for para journal type
	 *
	 */

	public List<ParaJournalTypeMaster> getEntrytype(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_ENTRY_TYPE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get all entry type : {}", responseString);
			List<ParaJournalTypeMaster> entrytypelist = new ArrayList<ParaJournalTypeMaster>();
			entrytypelist = gson.fromJson(responseString, new TypeToken<List<ParaJournalTypeMaster>>() {
			}.getType());
			//System.out.println(entrytypelist);
			return entrytypelist;
		} catch (Exception ex) {
			logger.info("Exception in getEntrytype ", ex);
			return null;
		}

	}

	/*
	 * for searche ledger
	 */

	public List<AccountDTO> searchledger(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SEARCH_LEDGER);
			//logger.info("service searchledger url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);

			List<AccountDTO> listAccountDTO = new ArrayList<AccountDTO>();
			listAccountDTO = gson.fromJson(responseString, new TypeToken<List<AccountDTO>>() {
			}.getType());
			//
			return listAccountDTO;

		} catch (Exception ex) {
			logger.info("Exception in searchledger", ex);
			return null;
		}
	}
	/*
	 * for add journal
	 */

	public String addjournal(JournalList journalList) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_ADD_JOURNAL);
			//logger.info("service addjournal url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(journalList));
			String responseString = response.getEntity(String.class);
			//logger.info("response string. addjournal..{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in addjournal ", ex);
			return null;
		}
	}

	/*
	 * for update journal
	 */

	public String updatejournal(JournalList journalList) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_UPDATE_JOURNAL);
			//logger.info("service updatejournal url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(journalList));
			String responseString = response.getEntity(String.class);
			//logger.info("response string. updatejournal..{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in updatejournal ", ex);
			return null;
		}
	}

	/*
	 * for delete jouranl
	 */
	public String deleteJournal(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_DEL_JOURNAL);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in delete journal: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteJournal ", ex);
			return null;
		}

	}

	/*
	 * for edit jouranl by id
	 */
	public JournalList editjournalbyid(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_GET_JOURNAL_BY_ID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in edit journal: {}", responseString);

			JournalList journalList = new JournalList();
			journalList = gson.fromJson(responseString, JournalList.class);
			//
			return journalList;

		} catch (Exception ex) {
			logger.info("Exception in editjournalbyid ", ex);
			return null;
		}

	}

	/*
	 * public List<AccountDTO> getAllAccountSetup(CommonResultSetMapper
	 * commonResultSetMapper) { try { String url =
	 * CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) +
	 * CommonResource.getProperty(CommonResource.
	 * WEBSERVICE_ACC_SETUP_GETALLACCOUNTSETUP);
	 * logger.info("service getAllAccountSetup url....{}", url); ClientResponse
	 * response = WebServiceClient.callPost(url,
	 * gson.toJson(commonResultSetMapper)); String responseString =
	 * response.getEntity(String.class); logger.info("response string...{}",
	 * responseString);
	 *
	 *
	 * List<AccountDTO> accountMasterslist = new ArrayList<AccountDTO>();
	 * accountMasterslist = gson.fromJson(responseString, new
	 * TypeToken<List<AccountDTO>>(){}.getType()); // return accountMasterslist;
	 * } catch (Exception ex) { logger.info("Exception", ex); return null; } }
	 */
}
