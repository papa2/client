package com.papa2.client.api.alipay.bo;

/**
 * 支付回调.
 * 
 * @author xujiakun
 * 
 */
public class AliCallback {

	private String id;

	/**
	 * 签名(对请求或响应中参数签名后的值).
	 */
	private String sign;

	/**
	 * 支付宝交易号(该交易在支付宝系统中的交易流水号。最短16位,最长64位).
	 */
	private String tradeNo;

	/**
	 * 支付结果(判断支付结果及交易状态。只有支付成功时(即result=success),才会跳转到支付成功页面,result有且只有success一个交易状态).
	 */
	private String result;

	/**
	 * 商户网站唯一订单号(支付宝合作商户网站唯一订单号).
	 */
	private String outTradeNo;

	/**
	 * 授权令牌(授权令牌,调用“手机网页即时到账授权接口(alipay.wap.trade.create.direct)”成功后返回的值).
	 */
	private String requestToken;

	private String modifyUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
