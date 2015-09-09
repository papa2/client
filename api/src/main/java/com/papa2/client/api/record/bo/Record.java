package com.papa2.client.api.record.bo;

import java.math.BigDecimal;

import com.papa2.client.api.reserve.bo.Reserve;

/**
 * 停车记录.
 * 
 * @author xujiakun
 * 
 */
public class Record {

	private Long recordId;

	/**
	 * 保安 or 租车人.
	 */
	private Long userId;

	private String carNo;

	/**
	 * 预约.
	 */
	private Long reserveId;

	private String state;

	private String modifyUser;

	// >>>>>>>>>>以下是增强属性<<<<<<<<<<

	private String recordDate;

	/**
	 * 进场 or 出场.
	 */
	private String type;

	//

	/**
	 * 停车开始时间.
	 */
	private String startTime;

	/**
	 * 停车结束时间.
	 */
	private String endTime;

	/**
	 * 标准租金.
	 */
	private BigDecimal cost;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	/**
	 * 出租方式(getRecordList).
	 */
	private String costType;

	/**
	 * 停车场(getRecordList).
	 */
	private String parkName;

	/**
	 * 预约.
	 */
	private Reserve reserve;

	/**
	 * 停车记录关联交易.
	 */
	private Long tradeId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Long getReserveId() {
		return reserveId;
	}

	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

}
