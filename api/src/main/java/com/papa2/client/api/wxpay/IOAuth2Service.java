package com.papa2.client.api.wxpay;

import com.papa2.client.api.wxpay.bo.AccessToken;
import com.papa2.client.framework.exception.ServiceException;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IOAuth2Service {

	String HTTPS_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

	String HTTPS_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";

	/**
	 * 
	 * @param appId
	 * @param redirectUrl
	 * @param scope
	 * @param state
	 * @return
	 * @throws ServiceException
	 */
	String authorize(String appId, String redirectUrl, String scope, String state) throws ServiceException;

	/**
	 * 
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	AccessToken accessToken(String appId, String appSecret, String code) throws ServiceException;

}
