package com.papa2.client.api.wxpay.bo;

/**
 * 支付通知.
 * 
 * @author xujiakun
 * 
 */
public class WxNotify {

	private String id;

	/**
	 * 签名类型,取值:MD5、RSA,默认:MD5.
	 */
	private String signType;

	/**
	 * 字符编码,取值:GBK、UTF-8,默认:GBK.
	 */
	private String inputCharset;

	/**
	 * 签名.
	 */
	private String sign;

	/**
	 * 1-即时到账 其他保留.
	 */
	private int tradeMode;

	/**
	 * 支付结果: 0—成功 其他保留.
	 */
	private int tradeState;

	/**
	 * 商户号,也即之前步骤的 partnerid,由微信统一分配的 10 位正整数(120XXXXXXX)号.
	 */
	private String partner;

	/**
	 * 银行类型,在微信中使用 WX.
	 */
	private String bankType;

	/**
	 * 银行订单号.
	 */
	private String bankBillno;

	/**
	 * 支付金额,单位为分,如果 discount 有值,通知的 total_fee + discount = 请求的 total_fee.
	 */
	private int totalFee;

	/**
	 * 现金支付币种 ,目前只支持人民币 , 默认值是 1-人民币.
	 */
	private int feeType;

	/**
	 * 支付结果通知 id,对于某些特定商户,只返回通知 id,要求商户据此查询交易结果.
	 */
	private String notifyId;

	/**
	 * 交易号,28 位长的数值,其中前 10 位为商户号,之后 8 位为订单产生的日期,如 20090415,最后 10 位是流水号.
	 */
	private String transactionId;

	/**
	 * 商户系统的订单号,与请求一致.
	 */
	private String outTradeNo;

	/**
	 * 商户数据包,原样返回,空参数不传递.
	 */
	private String attach;

	/**
	 * 支付完成时间,格式为 yyyyMMddhhmmss,如 2009 年 12 月27日9点10分10秒表示为 20091227091010 。时区为 GMT+8 beijing.
	 */
	private String timeEnd;

	/**
	 * 物流费用,单位分,默认 0。如果有值,必须保证 transport_fee + product_fee = total_fee.
	 */
	private int transportFee;

	/**
	 * 物品费用,单位分。如果有值,必证保须 transport_fee + product_fee=total_fee.
	 */
	private int productFee;

	/**
	 * 折扣价格,单位分,如果有值,通知的 total_fee + discount = 请求的 total_fee.
	 */
	private int discount;

	private String modifyUser;

	/**
	 * 字段名称：公众号 id；字段来源：商户注册具有支付权限的公众号成功后即可获得；传入方式：由商户直接传入.
	 */
	private String appId;

	/**
	 * 字段名称：时间戳；字段来源：商户生成从 1970 年 1 月 1 日 00：00：00 至今的秒数，即当前的时间；由商户生成后传入。取值范围：32字符以下.
	 */
	private String timeStamp;

	/**
	 * 字段名称：随机字符串；字段来源：商户生成的随机字符串；取值范围：长度为 32 个字符以下。由商户生成后传入。取值范围：32 字符以下.
	 */
	private String nonceStr;

	/**
	 * 支付该笔订单的用户 ID，商户可通过公众号其他接口为付款用户服务.
	 */
	private String openId;

	/**
	 * 字段名称：签名；字段来源：对前面的其他字段与 appKey 按照字典序排序后，使用 SHA1 算法得到的结果。由商户生成后传入.
	 */
	private String appSignature;

	/**
	 * 用户是否关注了公众号。1 为关注，0 为未关注。
	 */
	private String isSubscribe;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(int tradeMode) {
		this.tradeMode = tradeMode;
	}

	public int getTradeState() {
		return tradeState;
	}

	public void setTradeState(int tradeState) {
		this.tradeState = tradeState;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankBillno() {
		return bankBillno;
	}

	public void setBankBillno(String bankBillno) {
		this.bankBillno = bankBillno;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getFeeType() {
		return feeType;
	}

	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
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

	public int getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(int transportFee) {
		this.transportFee = transportFee;
	}

	public int getProductFee() {
		return productFee;
	}

	public void setProductFee(int productFee) {
		this.productFee = productFee;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppSignature() {
		return appSignature;
	}

	public void setAppSignature(String appSignature) {
		this.appSignature = appSignature;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

}
