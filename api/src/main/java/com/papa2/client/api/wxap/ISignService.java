package com.papa2.client.api.wxap;

/**
 * JS SDK 签名.
 * 
 * @author xujiakun
 * 
 */
public interface ISignService {

	/**
	 * 
	 * @param nonceStr
	 * @param timestamp
	 * @param url
	 * @return
	 */
	String sign(String nonceStr, String timestamp, String url);

}
