package com.papa2.client.api.wxpay;

import com.papa2.client.framework.exception.ServiceException;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IAccessTokenService {

	String HTTPS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	/**
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 * @throws ServiceException
	 */
	String getAccessToken(String appId, String appSecret) throws ServiceException;

}
