package com.papa2.client.api.wxap.bo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author xujiakun
 * 
 */
public class AccessToken extends Error {

	private static final long serialVersionUID = 5377284826332080520L;

	@JSONField(name = "access_token")
	private String accessToken;

	@JSONField(name = "expires_in")
	private int expiresIn;

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

}
