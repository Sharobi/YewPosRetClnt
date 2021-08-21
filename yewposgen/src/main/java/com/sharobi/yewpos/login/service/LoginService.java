/**
 *
 */
package com.sharobi.yewpos.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,habib
 *
 */
@Service
public class LoginService {

	private final static Logger logger=LoggerFactory.getLogger(LoginService.class);
	Gson gson = new Gson();

	public LoginInfoByUserDTO login(LoginInfoByUserDTO user)
	{
		try{
			ClientResponse response=WebServiceClient.callPost(CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_LOGIN_DOLOGIN), gson.toJson(user));

		String responseString=response.getEntity(String.class);
		//logger.info("Response string: {}", responseString);
		LoginInfoByUserDTO loginInfo = gson.fromJson(responseString, LoginInfoByUserDTO.class);
		return loginInfo;
		}catch(Exception e){
			LoginInfoByUserDTO loginInfo=new LoginInfoByUserDTO();
			loginInfo.setMessage("Server Closed");
			return loginInfo;
		}
	}

}
