package com.papa2.client.api.reserve.bo;

import java.io.Serializable;

import com.papa2.client.api.space.bo.Space;

/**
 * 
 * @author xujiakun
 * 
 */
public class Reserve implements Serializable {

	private static final long serialVersionUID = -860137298924094450L;

	private Long id;

	private Long userId;

	private Long carId;

	private Long spaceId;

	/**
	 * 开始预约时间.
	 */
	private String reserveDate;

	/**
	 * 预约有效时间.
	 */
	private String expireDate;

	private String state;

	private String modifyUser;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	/**
	 * 出租车位信息.
	 */
	private Space space;

	private String parkName;

	private String carNo;

	/**
	 * 过期状态 Y 过期 N 未过期.
	 */
	private String expireState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
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

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getExpireState() {
		return expireState;
	}

	public void setExpireState(String expireState) {
		this.expireState = expireState;
	}

}
