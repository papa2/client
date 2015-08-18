package com.papa2.client.api.space.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author xujiakun
 * 
 */
public class Space implements Serializable {

	private static final long serialVersionUID = -4969634334319968987L;

	private Long spaceId;

	private String spaceCode;

	private Long caseId;

	/**
	 * 车位归属:物业;用户.
	 */
	private String type;

	/**
	 * 当车位归属用户.
	 */
	private Long userId;

	private String mon;

	private String tue;

	private String wed;

	private String thu;

	private String fri;

	private String sat;

	private String sun;

	/**
	 * 出租周期开始时间.
	 */
	private String startTime;

	/**
	 * 出租周期结束时间.
	 */
	private String endTime;

	/**
	 * 出租周期开始日期.
	 */
	private String startDate;

	/**
	 * 出租周期结束日期.
	 */
	private String endDate;

	/**
	 * 出租方式.
	 */
	private String costType;

	private BigDecimal cost;

	private String modifyUser;

	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
