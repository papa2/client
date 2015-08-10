package com.papa2.client.api.monitor.bo;

import com.papa2.client.framework.bo.SearchInfo;

/**
 * action log.
 * 
 * @author xujiakun
 * 
 */
public class ActionMonitor extends SearchInfo {

	private static final long serialVersionUID = -3083445126172800213L;

	private String id;

	private Long userId;

	private String passport;

	private String userName;

	private String actionName;

	private String createDate;

	private String ip;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
