package com.papa2.client.wxap.service.impl;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.wxap.IAccessTokenService;
import com.papa2.client.api.wxap.bo.AccessToken;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.HttpUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class AccessTokenServiceImpl implements IAccessTokenService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AccessTokenServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	@Override
	public String getAccessToken(String appId, String appSecret) {
		if (StringUtils.isBlank(appId) || StringUtils.isBlank(appSecret)) {
			throw new ServiceException("appId and appSecret is null.");
		}

		String token = null;
		String key = appId.trim() + "&" + appSecret.trim();

		try {
			token = (String) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_WX_APP + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_APP + key, e);
		}

		if (StringUtils.isNotBlank(token)) {
			return token;
		}

		StringBuilder sb = new StringBuilder(HTTPS_TOKEN_URL);
		sb.append("&appid=").append(appId).append("&secret=").append(appSecret);

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
		if (StringUtils.isNotBlank(errCode) && !"0".equals(errCode)) {
			throw new ServiceException(accessToken.getErrMsg());
		}

		token = accessToken.getAccessToken();

		try {
			memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_WX_APP + key, token, accessToken.getExpiresIn());
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_APP + key, e);
		}

		return token;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}
