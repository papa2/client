package com.papa2.client.api.wxpay.bo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author xujiakun
 * 
 */
public class JsapiTicket extends Error {

	private String ticket;

	@JSONField(name = "expires_in")
	private int expiresIn;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}
