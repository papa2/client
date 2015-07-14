package com.papa2.client.api.record.bo;

/**
 * 停车记录.
 * 
 * @author xujiakun
 * 
 */
public class Record {

	private Long id;

	private String parkId;

	/**
	 * 停车开始时间.
	 */
	private String startTime;

	/**
	 * 停车结束时间.
	 */
	private String endTime;

	/**
	 * 停车卡信息.
	 */
	private String parkCardId;

	/**
	 * 车牌信息.
	 */
	private String carNo;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	/**
	 * 停车卡信息.
	 */
	private String parkCardNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
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

	public String getParkCardId() {
		return parkCardId;
	}

	public void setParkCardId(String parkCardId) {
		this.parkCardId = parkCardId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getParkCardNo() {
		return parkCardNo;
	}

	public void setParkCardNo(String parkCardNo) {
		this.parkCardNo = parkCardNo;
	}

}
