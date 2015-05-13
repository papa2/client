package com.papa2.client.api.user.bo;

import javax.xml.bind.annotation.XmlElement;

import com.papa2.client.framework.bo.SearchInfo;

/**
 * user.
 * 
 * @author xujiakun
 * 
 */
public class User extends SearchInfo {

	private static final long serialVersionUID = 5804990636185340805L;

	@XmlElement
	private String userId;

	/**
	 * 用户名.
	 */
	@XmlElement
	private String userName;

	/**
	 * 登录帐号.
	 */
	@XmlElement
	private String passport;

	/**
	 * 登录密码.
	 */
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

}
