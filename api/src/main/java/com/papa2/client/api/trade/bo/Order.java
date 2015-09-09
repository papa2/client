package com.papa2.client.api.trade.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息.
 * 
 * @author xujiaku
 * 
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 662058126197132644L;

	private Long orderId;

	/**
	 * 交易ID.
	 */
	private Long tradeId;

	private Long parkId;

	private String parkName;

	private Long recordId;

	/**
	 * 停车开始时间.
	 */
	private String startTime;

	/**
	 * 停车结束时间.
	 */
	private String endTime;

	private BigDecimal price;

	private String modifyUser;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
