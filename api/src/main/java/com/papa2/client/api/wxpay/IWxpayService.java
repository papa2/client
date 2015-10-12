package com.papa2.client.api.wxpay;

import com.papa2.client.api.wxpay.bo.WxNotify;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;

/**
 * 微信支付.
 * 
 * @author xujiakun
 * 
 */
public interface IWxpayService {

	String ERROR_MESSAGE = "微信支付接口调用失败！";

	String RETURN_CODE_SUCCESS = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";

	String RETURN_CODE_FAIL = "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";

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
	 * @param openId
	 * @param modifyUser
	 * @return
	 * @throws ServiceException
	 */
	String getBrandWCPayRequest(String tradeNo, String body, String detail, String attach, int totalFee, String ip,
		String timeStart, String timeExpire, String openId, String modifyUser) throws ServiceException;

	/**
	 * 支付通知.
	 * 
	 * @param wxNotify
	 * @return
	 */
	BooleanResult notify(WxNotify wxNotify);

}
