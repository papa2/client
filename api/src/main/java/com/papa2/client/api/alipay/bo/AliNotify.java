package com.papa2.client.api.alipay.bo;

/**
 * 支付通知.
 * 
 * @author xujiakun
 * 
 */
public class AliNotify {

	private String id;

	/**
	 * 接口名称.
	 */
	private String service;

	/**
	 * 接口版本号.
	 */
	private String v;

	/**
	 * 签名方式.
	 */
	private String secId;

	/**
	 * 签名.
	 */
	private String sign;

	/**
	 * 支付方式.
	 */
	private String paymentType;

	/**
	 * 商品名称.
	 */
	private String subject;

	/**
	 * 支付宝交易号.
	 */
	private String tradeNo;

	/**
	 * 买家支付宝账号.
	 */
	private String buyerEmail;

	/**
	 * 交易创建时间.
	 */
	private String gmtCreate;

	/**
	 * 通知类型.
	 */
	private String notifyType;

	/**
	 * 购买数量.
	 */
	private String quantity;

	/**
	 * 商户网站唯一订单号.
	 */
	private String outTradeNo;

	/**
	 * 通知时间.
	 */
	private String notifyTime;

	/**
	 * 卖家支付宝用户号.
	 */
	private String sellerId;

	/**
	 * 交易状态.
	 */
	private String tradeStatus;

	/**
	 * 是否调整总价.
	 */
	private String isTotalFeeAdjust;

	/**
	 * 交易金额.
	 */
	private String totalFee;

	/**
	 * 交易付款时间.
	 */
	private String gmtPayment;

	/**
	 * 卖家支付宝账号.
	 */
	private String sellerEmail;

	/**
	 * 交易关闭时间.
	 */
	private String gmtClose;

	/**
	 * 商品单价.
	 */
	private String price;

	/**
	 * 买家支付宝用户号.
	 */
	private String buyerId;

	/**
	 * 通知校验ID.
	 */
	private String notifyId;

	/**
	 * 是否使用红包买家.
	 */
	private String useCoupon;

	private String modifyUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}

	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getGmtPayment() {
		return gmtPayment;
	}

	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getGmtClose() {
		return gmtClose;
	}

	public void setGmtClose(String gmtClose) {
		this.gmtClose = gmtClose;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
