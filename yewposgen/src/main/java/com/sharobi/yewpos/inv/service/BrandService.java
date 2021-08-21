package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.BrandMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,SIDDIK
 *
 */

@Service
public class BrandService {
	private final static Logger logger = LoggerFactory.getLogger(BrandService.class);
    Gson gson = new Gson();

	public List<BrandMaster> getAllBrand(int compId,String lang) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETBRANDSBYCOMPID).replace("{1}", String.valueOf(compId)).replace("{2}", lang);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get all brand: {}", responseString);
			List<BrandMaster> brandMasterList = new ArrayList<BrandMaster>();
			brandMasterList = gson.fromJson(responseString, new TypeToken<List<BrandMaster>>() {}.getType());
			return brandMasterList;
		} catch (Exception ex) {
			logger.info("Exception in getAllBrand ", ex);
			return null;
		}

	}

	public BrandMaster getBrandById(int brandId,String lang) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETBRANDBYID).replace("{1}", String.valueOf(brandId)).replace("{2}",lang);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in get brand by Id: {}", responseString);
			BrandMaster brandMaster = gson.fromJson(responseString, new TypeToken<BrandMaster>() {}.getType());
			return brandMaster;
		} catch (Exception ex) {
			logger.info("Exception in getBrandById ", ex);
			return null;
		}

	}

	public String addBrand(BrandMaster brandMaster) {
		
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_BRAND);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(brandMaster));
			String responseString = response.getEntity(String.class);
			///logger.info("response string...{}", responseString);
			
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in addBrand ", ex);
			return null;
		}
		
	}

	public String editBrand(BrandMaster brandMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_BRAND);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(brandMaster));
			String responseString = response.getEntity(String.class);
			//logger.info("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in editBrand ", ex);
			return null;
		}
	}

	public String deleteBrand(int brandId) {
		
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_BRAND).replace("{1}", String.valueOf(brandId));
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in delete brand: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteBrand ", ex);
			return null;
		}

	}

	public List<BrandMaster> searchBrand(BrandMaster brandMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_BRAND);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(brandMaster));
			String responseString = response.getEntity(String.class);
			//System.err.println("responseString:" +responseString);
			List<BrandMaster> brandMasterList = new ArrayList<BrandMaster>();
			brandMasterList = gson.fromJson(responseString, new TypeToken<List<BrandMaster>>() {}.getType());
			return brandMasterList;
			} catch (Exception ex) {
			logger.info("Exception in searchBrand ", ex);
			return null;
		}
	}

	public List<CommonResultSetMapper> searchBrandAutoComplete(BrandMaster brandMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_BRAND_AUTOCOMPLETE);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(brandMaster));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" +responseString);
			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
			} catch (Exception ex) {
			logger.info("Exception in searchBrandAutoComplete ", ex);
			return null;
		}
	}
}
