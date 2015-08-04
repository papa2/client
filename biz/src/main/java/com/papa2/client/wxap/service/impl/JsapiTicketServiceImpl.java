package com.papa2.client.wxap.service.impl;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.wxap.IJsapiTicketService;
import com.papa2.client.api.wxap.bo.JsapiTicket;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.HttpUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class JsapiTicketServiceImpl implements IJsapiTicketService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(JsapiTicketServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	@Override
	public String getTicket(String accessToken) throws ServiceException {
		if (StringUtils.isBlank(accessToken)) {
			throw new ServiceException("accessToken is null.");
		}

		String ticket = null;
		String key = accessToken.trim();

		try {
			ticket = (String) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_WX_ACCESS_TOKEN + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_WX_ACCESS_TOKEN + key, e);
		}

		if (StringUtils.isNotBlank(ticket)) {
			return ticket;
		}

		StringBuilder sb = new StringBuilder(HTTPS_TICKET_URL);
		sb.append(accessToken);

		JsapiTicket jsapiTicket = null;

		try {
			jsapiTicket = JSON.parseObject(HttpUtil.get(sb.toString()), JsapiTicket.class);
		} catch (Exception e) {
			logger.error(sb, e);

			throw new ServiceException("HttpUtil error.");
		}

		if (jsapiTicket == null) {
			throw new ServiceException("jsapiTicket is null.");
		}

		if (!"0".equals(jsapiTicket.getErrCode())) {
			throw new ServiceException(jsapiTicket.getErrMsg());
		}

		ticket = jsapiTicket.getTicket();

		if (StringUtils.isNotBlank(ticket)) {
			try {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_WX_ACCESS_TOKEN + key, ticket,
					jsapiTicket.getExpiresIn());
			} catch (ServiceException e) {
				logger.error(IMemcachedCacheService.CACHE_KEY_WX_ACCESS_TOKEN + key, e);
			}
		}

		return ticket;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}
