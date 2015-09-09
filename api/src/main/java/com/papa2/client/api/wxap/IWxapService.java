package com.papa2.client.api.wxap;

import com.papa2.client.framework.exception.ServiceException;

/**
 * 微信支付.
 * 
 * @author xujiakun
 * 
 */
public interface IWxapService {

	String ERROR_MESSAGE = "微信支付接口调用失败！";

	/**
	 * 
	 * @param redirectUrl
	 * @param scope
	 * @param state
	 * @return
	 * @throws ServiceException
	 */
	String authorize(String redirectUrl, String scope, String state) throws ServiceException;

	/**
	 * 
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	String getOpenId(String code) throws ServiceException;

	/**
	 * 
	 * @param tradeNo
	 * @param body
	 * @param detail
	 * @param attach
	 * @param totalFee
	 * @param ip
	 * @param timeStart
	 * @param timeExpire
	 * @param notifyUrl
	 * @param openId
	 * @param modifyUser
	 * @return
	 * @throws ServiceException
	 */
	String getBrandWCPayRequest(String tradeNo, String body, String detail, String attach, int totalFee, String ip,
		String timeStart, String timeExpire, String notifyUrl, String openId, String modifyUser)
		throws ServiceException;

}
