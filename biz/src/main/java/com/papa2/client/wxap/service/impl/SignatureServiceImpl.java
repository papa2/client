package com.papa2.client.wxap.service.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.wxap.IAccessTokenService;
import com.papa2.client.api.wxap.IJsapiTicketService;
import com.papa2.client.api.wxap.ISignatureService;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.EncryptUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class SignatureServiceImpl implements ISignatureService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SignatureServiceImpl.class);

	private IAccessTokenService accessTokenService;

	private IJsapiTicketService jsapiTicketService;

	private String appId;

	private String appSecret;

	@Override
	public String signature(String nonceStr, String timestamp, String url) {
		if (StringUtils.isBlank(nonceStr) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(url)) {
			return null;
		}

		String accessToken;

		try {
			accessToken = accessTokenService.getAccessToken(appId, appSecret);
		} catch (ServiceException e) {
			logger.error(e);
			return null;
		}

		String ticket;
		try {
			ticket = jsapiTicketService.getTicket(accessToken);
		} catch (ServiceException e) {
			logger.error(e);
			return null;
		}

		StringBuilder string1 = new StringBuilder();
		string1.append("jsapi_ticket=").append(ticket).append("&noncestr=").append(nonceStr).append("&timestamp=")
			.append(timestamp).append("&url=").append(url);

		try {
			return EncryptUtil.encryptSHA(string1.toString());
		} catch (IOException e) {
			logger.error(e);
			return null;
		}
	}

	public IAccessTokenService getAccessTokenService() {
		return accessTokenService;
	}

	public void setAccessTokenService(IAccessTokenService accessTokenService) {
		this.accessTokenService = accessTokenService;
	}

	public IJsapiTicketService getJsapiTicketService() {
		return jsapiTicketService;
	}

	public void setJsapiTicketService(IJsapiTicketService jsapiTicketService) {
		this.jsapiTicketService = jsapiTicketService;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}
