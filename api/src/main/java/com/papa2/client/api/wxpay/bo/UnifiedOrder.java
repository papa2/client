package com.papa2.client.api.wxpay.bo;

/**
 * 
 * @author JiakunXu
 * 
 */
public class UnifiedOrder {

	/**
	 * 公众账号ID.
	 */
	private String appId;

	/**
	 * 商户号.
	 */
	private String mchId;

	/**
	 * 设备号(终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB").
	 */
	private String deviceInfo;

	private String nonceStr;

	private String sign;

	/**
	 * 商品描述.
	 */
	private String body;

	/**
	 * 商品详情.
	 */
	private String detail;

	/**
	 * 附加数据.
	 */
	private String attach;

	/**
	 * 商户订单号.
	 */
	private String outTradeNo;

	/**
	 * 货币类型.
	 */
	private String feeType;

	private int totalFee;

	/**
	 * 终端IP.
	 */
	private String spbillCreateIp;

	/**
	 * 交易起始时间.
	 */
	private String timeStart;

	/**
	 * 交易结束时间.
	 */
	private String timeExpire;

	/**
	 * 商品标记.
	 */
	private String goodsTag;

	/**
	 * 通知地址.
	 */
	private String notifyUrl;

	/**
	 * 交易类型(取值如下：JSAPI，NATIVE，APP，WAP).
	 */
	private String tradeType;

	/**
	 * 商品ID.
	 */
	private String productId;

	/**
	 * 指定支付方式.
	 */
	private String limitPay;

	/**
	 * 用户标识.
	 */
	private String openId;

	private String modifyUser;

	// >>>>>>>>>>以下是增强属性<<<<<<<<<<

	private String prePayId;

	private String codeUrl;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getPrePayId() {
		return prePayId;
	}

	public void setPrePayId(String prePayId) {
		this.prePayId = prePayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

}
