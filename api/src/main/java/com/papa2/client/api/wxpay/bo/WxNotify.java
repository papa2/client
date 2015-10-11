package com.papa2.client.api.wxpay.bo;

/**
 * 支付通知.
 * 
 * @author JiakunXu
 * 
 */
public class WxNotify extends Result {

	/**
	 * 微信分配的公众账号ID（企业号corpid即为此appId）.
	 */
	private String appId;

	/**
	 * 微信支付分配的商户号.
	 */
	private String mchId;

	/**
	 * 微信支付分配的终端设备号.
	 */
	private String deviceInfo;

	/**
	 * 随机字符串，不长于32位.
	 */
	private String nonceStr;

	private String sign;

	/**
	 * 用户在商户appid下的唯一标识.
	 */
	private String openId;

	/**
	 * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效.
	 */
	private String isSubscribe;

	/**
	 * JSAPI、NATIVE、APP.
	 */
	private String tradeType;

	/**
	 * 银行类型，采用字符串类型的银行标识.
	 */
	private String bankType;

	/**
	 * 订单总金额，单位为分.
	 */
	private int totalFee;

	/**
	 * 货币类型，符合ISO4217标准的三位字母代码，默认人民币.
	 */
	private String feeType;

	/**
	 * 现金支付金额订单现金支付金额.
	 */
	private int cashFee;

	/**
	 * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY.
	 */
	private String cashFeeType;

	/**
	 * 代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额.
	 */
	private int couponFee;

	private int couponCount;

	/**
	 * 代金券或立减优惠ID,$n为下标，从0开始编号.
	 */
	private String[] couponIds;

	/**
	 * 单个代金券或立减优惠支付金额,$n为下标，从0开始编号.
	 */
	private int[] couponFees;

	/**
	 * 微信支付订单号.
	 */
	private String transactionId;

	/**
	 * 商户系统的订单号，与请求一致.
	 */
	private String outTradeNo;

	/**
	 * 商家数据包，原样返回.
	 */
	private String attach;

	/**
	 * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010.
	 */
	private String timeEnd;

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public int getCashFee() {
		return cashFee;
	}

	public void setCashFee(int cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public int getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(int couponFee) {
		this.couponFee = couponFee;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public String[] getCouponIds() {
		return couponIds;
	}

	public void setCouponIds(String[] couponIds) {
		this.couponIds = couponIds;
	}

	public int[] getCouponFees() {
		return couponFees;
	}

	public void setCouponFees(int[] couponFees) {
		this.couponFees = couponFees;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

}
