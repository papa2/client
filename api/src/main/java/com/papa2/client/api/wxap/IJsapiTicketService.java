package com.papa2.client.api.wxap;

import com.papa2.client.framework.exception.ServiceException;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IJsapiTicketService {

	String HTTPS_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

	/**
	 * 
	 * @param accessToken
	 * @return
	 * @throws ServiceException
	 */
	String getTicket(String accessToken) throws ServiceException;

}
