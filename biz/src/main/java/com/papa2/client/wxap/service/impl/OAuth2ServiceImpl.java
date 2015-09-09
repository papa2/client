package com.papa2.client.wxap.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.papa2.client.api.wxap.IOAuth2Service;
import com.papa2.client.api.wxap.bo.AccessToken;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.HttpUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class OAuth2ServiceImpl implements IOAuth2Service {

	private Logger4jExtend logger = Logger4jCollection.getLogger(OAuth2ServiceImpl.class);

	@Override
	public String authorize(String appId, String redirectUrl, String scope, String state) {
		if (StringUtils.isBlank(appId)) {
			throw new ServiceException("appid 公众号的唯一标识 不能为空.");
		}

		if (StringUtils.isBlank(redirectUrl)) {
			throw new ServiceException("redirect_url 授权后重定向的回调链接地址 不能为空.");
		}

		StringBuilder sb = new StringBuilder(IOAuth2Service.HTTPS_AUTHORIZE_URL);
		sb.append("?appid=").append(appId).append("&redirect_uri=").append(redirectUrl)
			.append("&response_type=code&scope=").append(scope).append("&state=").append(state)
			.append("#wechat_redirect");

		return sb.toString();
	}

	@Override
	public AccessToken accessToken(String appId, String appSecret, String code) {
		if (StringUtils.isBlank(appId)) {
			throw new ServiceException("appid 公众号的唯一标识 不能为空.");
		}

		if (StringUtils.isBlank(appSecret)) {
			throw new ServiceException("secret 公众号的appsecret 不能为空.");
		}

		if (StringUtils.isBlank(code)) {
			throw new ServiceException("code code参数 不能为空.");
		}

		StringBuilder sb = new StringBuilder(IOAuth2Service.HTTPS_ACCESS_TOKEN_URL);
		sb.append("&appid=").append(appId).append("&secret=").append(appSecret).append("&code=").append(code);

		AccessToken accessToken = null;

		try {
			accessToken = JSON.parseObject(HttpUtil.get(sb.toString()), AccessToken.class);
		} catch (Exception e) {
			logger.error(sb, e);

			throw new ServiceException("HttpUtil error.");
		}

		if (accessToken == null) {
			throw new ServiceException("accessToken is null.");
		}

		String errCode = accessToken.getErrCode();
		if (StringUtils.isNotBlank(errCode)) {
			throw new ServiceException(accessToken.getErrMsg());
		}

		return accessToken;
	}

}
