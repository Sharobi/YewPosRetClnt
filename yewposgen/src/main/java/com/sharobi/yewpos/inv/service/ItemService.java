package com.sharobi.yewpos.inv.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.MarketerMaster;
import com.sharobi.yewpos.inv.model.TaxTypeDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,SIDDIK
 *
 */
@Service
public class ItemService {
	private final static Logger logger = LoggerFactory.getLogger(ItemService.class);
  Gson gson = new Gson();

	public String checkSameItemExist(	String name,int itemid,
										String lang) {
		try {
			name = URLEncoder.encode(name, "UTF-8");
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEM_CHECK_DUPLICATE_NAME).replace("{1}", name.trim()).replace("{2}", String.valueOf(itemid));
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseVal = response.getEntity(String.class);
			return responseVal;
		} catch (Exception ex) {
			logger.info("Exception in checkSameItemExist ", ex);
			return null;
		}

	}

	public ResponseObj addItem(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_ITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.info("Exception in addItem ", ex);
			return null;
		}
	}

	public String addItemViaAjax(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_ITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in addItemViaAjax ", ex);
			return null;
		}
	}

	public ItemMaster getItemDetailsById(	int id,
											String lang) {
		try {

			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEMDETAILS_BY_ID).replace("{1}", String.valueOf(id)).replace("{2}", lang);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get item by Id: {}", responseString);
			ItemMaster itemMaster = gson.fromJson(responseString, new TypeToken<ItemMaster>() {}.getType());
			return itemMaster;
		} catch (Exception ex) {
			logger.info("Exception in getItemDetailsById ", ex);
			return null;
		}

	}

	public String getItemDetailsByIdForEdit(int id,	String lang) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEMDETAILS_BY_ID).replace("{1}", String.valueOf(id)).replace("{2}", lang);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get item by Id for edit in modal: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getItemDetailsByIdForEdit ", ex);
			return null;
		}
	}

	public ResponseObj updateItem(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_ITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.info("Exception in updateItem ", ex);
			return null;
		}

	}

	public String updateItemViaAjax(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_ITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			///logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in updateItemViaAjax ", ex);
			return null;
		}

	}

	public List<CommonResultSetMapper> searchItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
		} catch (Exception ex) {
			logger.info("Exception in searchItem ", ex);
			return null;
		}
	}

	public List<CommonResultSetMapper> searchItemAutoComplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);

			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
		} catch (Exception ex) {
			logger.info("Exception in searchItemAutoComplete ", ex);
			return null;
		}
	}

	public String deleteItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_ITEM);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in delete brand: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteItem ", ex);
			return null;
		}

	}

	public String getAlternateMedicine(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEM_GET_SAMECONTENT);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getAlternateMedicine ", ex);
			return null;
		}
	}

	public String getItemsByContent(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_CONTENT_STOCK_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getItemsByContent ", ex);
			return null;
		}
	}

	public String getItemHistory(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_HISTORY_BY_ITEMID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString getItemHistory:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getItemHistory ", ex);
			return null;
		}
	}

	public String getItemByBarcode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_BY_BARCODE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString getItemByBarcode:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getItemByBarcode", ex);
			return null;
		}
	}

	/*
	 *  add on 11_02_2017
	 *
	 *
	 * */
	public 	List<TaxTypeDTO>  getalltaxtype_is_exclusive(CommonResultSetMapper commonResultSetMapper) {
		try {

			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETTAXALLTYPEISEXCLUSIVE);
			//logger.info("url....{} getalltaxtype_is_exclusive service ", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString getalltaxtype_is_exclusive:" + responseString);

			List<TaxTypeDTO> alltaxtypeexclusive = new ArrayList<TaxTypeDTO>();
			alltaxtypeexclusive = gson.fromJson(responseString, new TypeToken<List<TaxTypeDTO>>(){}.getType());
			//

			return alltaxtypeexclusive;
		} catch (Exception ex) {
			logger.info("Exception in getalltaxtype_is_exclusive ", ex);
			return null;
		}
	}


	/*
	 *
	 * ADD ON 11_03_2017 autoCompleteSearchMarketerName
	 * */

	public 	List<CommonResultSetMapper>  autoCompleteSearchMarketerName(MarketerMaster marketerMaster) {
		try {


			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_MARKETER_AUTOCOMPLETE);
			//logger.info("url....{} autoCompleteSearchMarketerName service ", url);

			ClientResponse response = WebServiceClient.callPost(url.trim(), gson.toJson(marketerMaster));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString autoCompleteSearchMarketerName:" + responseString);

			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;

		} catch (Exception ex) {
			logger.info("Exception in autoCompleteSearchMarketerName ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * 12-Dec-2017
	 */
	public String getItemControlByItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ITEMCONTROL_BY_ITEMID);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString getItemControlByItem:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getItemControlByItem", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * 14-Dec-2017
	 */
	public String getAllLocation(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_LOCATIONS);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getAllLocation ", ex);
			return null;
		}
	}
	/*
	 *
	 */
	public String getItemsByManufacturer(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_MANUFACTURE_STOCK_AUTOCOMPLETE);
			//logger.info("service url..call ..{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in getItemsByManufacturer ", ex);
			return null;
		}
	}

	public List<CommonResultSetMapper> searchItemAutoCompleteByCode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_BYCODE_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);

			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
		} catch (Exception ex) {
			logger.info("Exception in searchItemAutoCompleteByCode ", ex);
			return null;
		}
	}
}
