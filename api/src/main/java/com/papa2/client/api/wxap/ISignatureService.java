package com.papa2.client.api.wxap;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISignatureService {

	/**
	 * 
	 * @param nonceStr
	 * @param timestamp
	 * @param url
	 * @return
	 */
	String signature(String nonceStr, String timestamp, String url);

}
