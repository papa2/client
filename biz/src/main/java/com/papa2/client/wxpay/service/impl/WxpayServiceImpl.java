package com.papa2.client.wxpay.service.impl;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.papa2.client.api.wxpay.IOAuth2Service;
import com.papa2.client.api.wxpay.IUnifiedOrderService;
import com.papa2.client.api.wxpay.IWxpayService;
import com.papa2.client.api.wxpay.bo.AccessToken;
import com.papa2.client.api.wxpay.bo.UnifiedOrder;
import com.papa2.client.api.wxpay.bo.Wxpay;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.util.EncryptUtil;
import com.papa2.client.framework.util.UUIDUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class WxpayServiceImpl implements IWxpayService {

	private IOAuth2Service oauth2Service;

	private IUnifiedOrderService unifiedOrderService;

	private String appId;

	private String appSecret;

	private String mchId;

	private String notifyUrl;

	private String key;

	@Override
	public String authorize(String redirectUrl, String scope, String state) {
		return oauth2Service.authorize(appId, redirectUrl, scope, state);
	}

	@Override
	public String getOpenId(String code) {
		AccessToken accessToken = oauth2Service.accessToken(appId, appSecret, code);

		if (accessToken == null) {
			return null;
		}

		return accessToken.getOpenId();
	}

	@Override
	public String getBrandWCPayRequest(String tradeNo, String body, String detail, String attach, int totalFee,
		String ip, String timeStart, String timeExpire, String openId, String modifyUser) {
		Wxpay wxpay = new Wxpay();

		wxpay.setAppId(appId);
		wxpay.setTimeStamp(Long.toString(System.currentTimeMillis() / 1000));
		wxpay.setNonceStr(UUIDUtil.generate());
		wxpay.setPackageValue("prepay_id="
			+ getPrePayId(appId, mchId, body, detail, attach, tradeNo, totalFee, ip, timeStart, timeExpire, notifyUrl,
				openId, modifyUser));
		wxpay.setSignType("MD5");

		StringBuilder sign = new StringBuilder();
		sign.append("appId=").append(wxpay.getAppId());
		sign.append("&nonceStr=").append(wxpay.getNonceStr());
		sign.append("&package=").append(wxpay.getPackageValue());
		sign.append("&signType=").append(wxpay.getSignType());
		sign.append("&timeStamp=").append(wxpay.getTimeStamp());

		sign.append("&key=").append(key);

		try {
			wxpay.setPaySign(EncryptUtil.encryptMD5(sign.toString()).toUpperCase());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}

		return JSON.toJSONString(wxpay);
	}

	private String getPrePayId(String appId, String mchId, String body, String detail, String attach, String tradeNo,
		int totalFee, String ip, String timeStart, String timeExpire, String notifyUrl, String openId, String modifyUser) {
		UnifiedOrder unifiedOrder = new UnifiedOrder();

		unifiedOrder.setAppId(appId);
		unifiedOrder.setMchId(mchId);
		unifiedOrder.setDeviceInfo("WEB");
		// unifiedOrder.setNonceStr(UUIDUtil.generate());
		// unifiedOrder.setSign("");
		unifiedOrder.setBody(body);
		unifiedOrder.setDetail(detail);
		unifiedOrder.setAttach(attach);
		unifiedOrder.setOutTradeNo(tradeNo);
		unifiedOrder.setFeeType("CNY");
		unifiedOrder.setTotalFee(totalFee);
		unifiedOrder.setSpbillCreateIp(ip);
		unifiedOrder.setTimeStart(timeStart);
		unifiedOrder.setTimeExpire(timeExpire);
		unifiedOrder.setGoodsTag("");
		unifiedOrder.setNotifyUrl(notifyUrl);
		unifiedOrder.setTradeType("JSAPI");
		unifiedOrder.setProductId("");
		unifiedOrder.setLimitPay("");
		unifiedOrder.setOpenId(openId);

		return unifiedOrderService.unifiedOrder(unifiedOrder, modifyUser);
	}

	public IOAuth2Service getOauth2Service() {
		return oauth2Service;
	}

	public void setOauth2Service(IOAuth2Service oauth2Service) {
		this.oauth2Service = oauth2Service;
	}

	public IUnifiedOrderService getUnifiedOrderService() {
		return unifiedOrderService;
	}

	public void setUnifiedOrderService(IUnifiedOrderService unifiedOrderService) {
		this.unifiedOrderService = unifiedOrderService;
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

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
