package com.papa2.client.api.pay;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.papa2.client.framework.bo.BooleanResult;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IPayService {

	String PAY_TYPE_ALIPAY = "alipay";

	String PAY_TYPE_WXPAY = "wxpay";

	/**
	 * 
	 * @param redirectUrl
	 * @param state
	 * @return
	 */
	BooleanResult authorize(String redirectUrl, String state);

	/**
	 * 
	 * @param code
	 * @return
	 */
	String getOpenId(String code);

	/**
	 * 支付.
	 * 
	 * @param userId
	 * @param openId
	 * @param tradeNo
	 * @param payType
	 * @param ip
	 * @return
	 */
	BooleanResult pay(Long userId, String openId, String tradeNo, String payType, String ip);

	// >>>>>>>>>>以下是第三方交易平台<<<<<<<<<<

	/**
	 * 支付回调(alipay).
	 * 
	 * @param request
	 * @return
	 */
	BooleanResult callback(HttpServletRequest request);

	/**
	 * 支付通知(alipay).
	 * 
	 * @param tradeNo
	 * @param params
	 * @return
	 */
	boolean notify(String tradeNo, Map<String, String> params);

	/**
	 * 支付通知.
	 * 
	 * @param wxNotify
	 * @return
	 */
	BooleanResult notify(String wxNotify);

}
