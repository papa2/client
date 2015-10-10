package com.papa2.client.api.trade.bo;

import java.math.BigDecimal;
import java.util.List;

import com.papa2.client.framework.bo.SearchInfo;

/**
 * 交易信息.
 * 
 * @author xujiakun
 * 
 */
public class Trade extends SearchInfo {

	private static final long serialVersionUID = 5843447955374057287L;

	/**
	 * 交易ID.
	 */
	private Long tradeId;

	/**
	 * 用户ID.
	 */
	private Long userId;

	/**
	 * 交易订单号.
	 */
	private String tradeNo;

	/**
	 * 交易价格（不含折扣）.
	 */
	private BigDecimal tradePrice;

	/**
	 * 优惠券使用价格.
	 */
	private BigDecimal couponPrice;

	/**
	 * 交易积分（积分兑换）.
	 */
	private int points;

	/**
	 * topay: 待付款; pay: 已付款; cancel: 已关闭; feedback: 维权订单; feedbacked: 已处理维权订单.
	 */
	private String type;

	/**
	 * 状态 D:删除 U:正常.
	 */
	private String state;

	/**
	 * 下单时间.
	 */
	private String createDate;

	/**
	 * 订单最后修改时间.
	 */
	private String modifyDate;

	/**
	 * 付款时间.
	 */
	private String payDate;

	/**
	 * 操作人ID.
	 */
	private String modifyUser;

	/**
	 * 支付方式(alipay wxpay).
	 */
	private String payType;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	private List<Order> orderList;

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	/**
	 * 实付金额 tradePrice - couponPrice + postage + (change).
	 * 
	 * @return
	 */
	public BigDecimal getPrice() {
		if (this.tradePrice != null && this.couponPrice != null) {
			return this.tradePrice.add(this.couponPrice.negate());
		}

		return BigDecimal.ZERO;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
