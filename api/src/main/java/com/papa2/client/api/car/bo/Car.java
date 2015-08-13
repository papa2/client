package com.papa2.client.api.car.bo;

import java.io.Serializable;

/**
 * 
 * @author xujiakun
 * 
 */
public class Car implements Serializable {

	private static final long serialVersionUID = -7788659343968374496L;

	private Long id;

	private Long userId;

	private String carNo;

	private String modifyUser;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

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

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

}
