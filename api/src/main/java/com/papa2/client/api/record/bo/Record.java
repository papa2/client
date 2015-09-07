package com.papa2.client.api.record.bo;

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

	/**
	 * 预约.
	 */
	private Long reserveId;

	private String modifyUser;

	// >>>>>>>>>>以下是增强属性<<<<<<<<<<

	private String recordDate;

	/**
	 * 进场 or 出场.
	 */
	private String type;

	/**
	 * 停车开始时间.
	 */
	private String startTime;

	/**
	 * 停车结束时间.
	 */
	private String endTime;

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

	public Long getReserveId() {
		return reserveId;
	}

	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
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

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

}
