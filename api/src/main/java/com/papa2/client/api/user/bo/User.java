package com.papa2.client.api.user.bo;

import com.papa2.client.framework.bo.SearchInfo;

/**
 * 用户.
 * 
 * @author xujiakun
 * 
 */
public class User extends SearchInfo {

	private static final long serialVersionUID = 5804990636185340805L;

	private Long userId;

	/**
	 * 用户名.
	 */
	private String userName;

	/**
	 * 登录帐号.
	 */
	private String passport;

	/**
	 * 登录密码.
	 */
	private String password;

	/**
	 * mobile.
	 */
	private String mobile;

	/**
	 * 状态(D:删除 U:正常 F:禁用).
	 */
	private String state;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	/**
	 * client or boss.
	 */
	private String type;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
