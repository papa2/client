package com.papa2.client.api.wxap.bo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author xujiakun
 * 
 */
public class AccessToken extends Error {

	/**
	 * 基础支持.
	 */
	@JSONField(name = "access_token")
	private String accessToken;

	/**
	 * 基础支持.
	 */
	@JSONField(name = "expires_in")
	private int expiresIn;

	/**
	 * 网页授权.
	 */
	@JSONField(name = "refresh_token")
	private String refreshToken;

	/**
	 * 网页授权.
	 */
	@JSONField(name = "openid")
	private String openId;

	/**
	 * 网页授权.
	 */
	@JSONField(name = "scope")
	private String scope;

	/**
	 * 网页授权.
	 */
	@JSONField(name = "unionid")
	private String unionId;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
